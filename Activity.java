import java.awt.*;
import java.time.chrono.MinguoEra;

public class Activity implements Action{
    private Actor entity;
    private WorldModel world;
    private ImageStore imageStore;
    private int repeatCount;

    public Activity(Actor entity, WorldModel world, ImageStore imageStore, int repeatCount) {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
        this.repeatCount = repeatCount;
    }

    public void executeAction(EventScheduler scheduler)
    {
        if(entity instanceof MinerFull) {
            entity.executeActivity(world, imageStore, scheduler);
        } else if (entity.getClass() == MinerNotFull.class) {
            entity.executeActivity(world,
                    imageStore, scheduler);
        } else if (entity.getClass() == Ore.class) {
            entity.executeActivity(world, imageStore,
                    scheduler);
        } else if (entity.getClass() == OreBlob.class) {
            entity.executeActivity(world,
                    imageStore, scheduler);
        } else if (entity.getClass() == Quake.class) {
            entity.executeActivity(world, imageStore,
                    scheduler);
        } else if(entity.getClass() == Vein.class) {
            entity.executeActivity(world, imageStore,
                    scheduler);
        } else{
                throw new UnsupportedOperationException(
                        String.format("executeActivityAction not supported for %s",
                                entity.getClass()));
        }
    }

}
