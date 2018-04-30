import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class OreBlob extends ScheduleAnimation implements Entity, AnimatedActor{
    protected int imageIndex = 0;
    protected Point position;
    protected int animationPeriod;
    protected String id;
    protected int actionPeriod;
    protected List<PImage> images;

    public OreBlob(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod){
        super(id,position,images,actionPeriod,animationPeriod);

    }

    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public void setPosition(Point newPos) {

    }

    public List<PImage> getImages(){return images;}

    @Override
    public PImage getCurrentImage() {
        return null;
    }

    public void executeActivity(WorldModel world,
                                       ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> blobTarget = world.findNearest(
                this.position, this.getClass());
        long nextPeriod = this.actionPeriod;

        if (blobTarget.isPresent())
        {
            Point tgtPos = blobTarget.get().getPosition();

            if (moveToOreBlob(world, blobTarget.get(), scheduler))
            {
                Quake quake = Create.createQuake(imageStore.getImageList(Functions.QUAKE_KEY),tgtPos);
                world.addEntity(quake);
                nextPeriod += this.actionPeriod;
                scheduleActions(quake, world,scheduler, imageStore);
            }
        }

        scheduler.scheduleEvent(this,
                ActionFactory.createActivityAction(this, world, imageStore),
                nextPeriod);
    }

    public int getAnimationPeriod()
    {
        return this.animationPeriod;
    }

    public boolean moveToOreBlob(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (adjacent(this.getPosition(),target.getPosition()))
        {
            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);
            return true;
        }
        else
        {
            Point nextPos = this.nextPositionOreBlob(world, this.position);

            if (!this.position.equals(nextPos))
            {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent())
                {
                    scheduler.unscheduleAllEvents(this);
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }

    public void nextImage()
    {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }
    public int getActionPeriod(){return actionPeriod;}


    public Point nextPositionOreBlob(WorldModel world, Point destPos)
    {
        int horiz = Integer.signum(destPos.getX() - this.position.getX());
        Point newPos = new Point(this.position.getX() + horiz,
                this.position.getY());

        Optional<Entity> occupant = world.getOccupant(newPos);

        if (horiz == 0 ||
                (occupant.isPresent() && !(occupant.get() instanceof Ore)))
        {
            int vert = Integer.signum(destPos.getY() - this.position.getY());
            newPos = new Point(this.position.getX(), this.position.getY() + vert);
            occupant = world.getOccupant(newPos);

            if (vert == 0 ||
                    (occupant.isPresent() && !(occupant.get() instanceof Ore)))
            {
                newPos = this.position;
            }
        }

        return newPos;
    }


    public static boolean adjacent(Point p1, Point p2)
    {
        return (p1.getX() == p2.getX() && Math.abs(p1.getY() - p2.getY()) == 1) ||
                (p1.getY() == p2.getY() && Math.abs(p1.getX() - p2.getX()) == 1);
    }

}
