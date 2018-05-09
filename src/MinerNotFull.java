import processing.core.PImage;

import java.awt.Event;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class MinerNotFull extends AbstractMiner implements AnimatedActor{
    private static final String RAINBOW_NAME = "rainbow_default";
    private static final String EMINER_KEY = "evil_miner";
    private static final String ALON_ID = "alon";
    private static final int ALON_RESOURCE_LIMIT = 2;

    private boolean alonExists = false;
    private static final String RAINBOW_ID = "rainbow";


    public MinerNotFull(String id, Point position,
                        List<PImage> images, int actionPeriod, int animationPeriod, int resourceCount, int resourceLimit) {
        super(id, position, images, actionPeriod, animationPeriod, resourceCount, resourceLimit);
    }

    public void setImageList(List<PImage> newImageList){
        this.images = newImageList;
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> notFullTarget = world.findNearest(this.position,
                Ore.class);


        Background miner_background = new Background(RAINBOW_NAME, imageStore.getImageList(RAINBOW_ID));
        if(world.getBackgroundCell(this.position).equals(miner_background)){
            setImageList(imageStore.getImageList(EMINER_KEY));
            this.actionPeriod = this.actionPeriod / 2;

            if (!alonExists) { //ensures that only one alon spawns per minernotfull crossing the rainbow
                spawnAlon(world, scheduler, imageStore);
            } else {
                alonExists = true;
            }
        }

        if (!notFullTarget.isPresent() ||
                !moveToNotFull(world,notFullTarget.get(), scheduler) ||
                !transformNotFull(world, scheduler, imageStore))
        {
            scheduler.scheduleEvent(this,
                    ActionFactory.createActivityAction(this, world, imageStore),
                    this.actionPeriod);
        }
    }

    public boolean transformNotFull(WorldModel world,
                                    EventScheduler scheduler, ImageStore imageStore)
    {
        if (this.resourceCount >= this.resourceLimit)
        {
            MinerFull miner = Create.createMinerFull(this.id, this.resourceLimit,
                    this.actionPeriod, this.animationPeriod, position,
                    this.images);

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(miner);
            scheduleActions(miner, world, scheduler, imageStore);

            return true;
        }

        return false;
    }

    public void spawnAlon(WorldModel world, EventScheduler scheduler, ImageStore imageStore){
        Alon alon = Create.createAlon(ALON_ID,ALON_RESOURCE_LIMIT,this.actionPeriod,
                this.animationPeriod,position, imageStore.getImageList(ALON_ID));
        alon.resourceCount = 0;
        world.addEntity(alon);
        scheduleActions(alon, world, scheduler, imageStore);
    }

    public boolean moveToNotFull(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (OreBlob.adjacent(this.getPosition(), target.getPosition()))
        {
            this.resourceCount += 1;

            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);

            return true;
        }
        else
        {
            Point nextPos = this.nextPositionMiner(world, target.getPosition());

            if (!this.position.equals(nextPos))
            {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent())
                {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }

    public <R> R accept(EntityVisitor<R> visitor)
    {
        return visitor.visit(this);
    }

}
