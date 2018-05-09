import processing.core.PImage;
import java.util.List;
import java.util.Optional;

public class Alon extends AbstractMiner implements Entity, AnimatedActor{
    public Alon(String id, Point position,
                     List<PImage> images, int resourceLimit, int resourceCount,
                     int actionPeriod, int animationPeriod)
    {
        super(id, position, images, actionPeriod, animationPeriod,resourceCount,resourceLimit);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> notFullTarget = world.findNearest(this.position,
                Vein.class);

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
            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);


            return true;
        }

        return false;
    }

    public boolean moveToNotFull(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (OreBlob.adjacent(this.getPosition(), target.getPosition()))
        {
            resourceCount++;
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
