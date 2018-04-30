import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class MinerFull extends AbstractMiner implements Entity, AnimatedActor{

    protected int imageIndex = 0;
    protected Point position;
    protected int animationPeriod;
    protected String id;
    protected int resourceLimit;
    protected int actionPeriod;
    protected List<PImage> images;
    protected int resourceCount;

    public MinerFull(String id, Point position,
                     List<PImage> images, int resourceLimit, int resourceCount,
                     int actionPeriod, int animationPeriod)
    {
        super(id, position, images, actionPeriod, animationPeriod,resourceCount,resourceLimit);
    }

    public void executeActivity(WorldModel world,
                                         ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> fullTarget = world.findNearest(this.position,
                Blacksmith.class);

        if (fullTarget.isPresent() &&
                this.moveToFull(world, fullTarget.get(), scheduler))
        {
            transformFull(world, scheduler, imageStore);
        }
        else
        {
            scheduler.scheduleEvent(this,
                    ActionFactory.createActivityAction(this, world, imageStore),
                    this.actionPeriod);
        }
    }

    public int getAnimationPeriod() {
        return this.animationPeriod;
    }

    public boolean moveToFull(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (OreBlob.adjacent(this.getPosition(),target.getPosition()))
        {
            return true;
        }
        else
        {
            Point nextPos = nextPositionMiner(world, target.getPosition());

            if (!this.position.equals(nextPos))
            {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent())
                {
                    scheduler.unscheduleAllEvents(this);
                }

                world.moveEntity(this, world, nextPos);
            }
            return false;
        }
    }

    public void transformFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        Entity miner = Create.createMinerNotFull(this.id, this.resourceLimit,
                this.position, this.actionPeriod,this.animationPeriod,
                this.images);

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(miner);
        scheduleActions(miner, world, scheduler, imageStore);
    }


    public Point nextPositionMiner(WorldModel world, Point destPos)
    {
        int horiz = Integer.signum(destPos.getX() - this.position.getX());
        Point newPos = new Point(this.position.getX() + horiz,
                this.position.getY());

        if (horiz == 0 || world.isOccupied(newPos))
        {
            int vert = Integer.signum(destPos.getY() - this.position.getY());
            newPos = new Point(this.position.getX(),
                    this.position.getY() + vert);

            if (vert == 0 || world.isOccupied(newPos))
            {
                newPos = this.position;
            }
        }

        return newPos;
    }

    public void nextImage()
    {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }
    public int getImageIndex() {
        return imageIndex;
    }
    public int getActionPeriod(){return actionPeriod;}

}
