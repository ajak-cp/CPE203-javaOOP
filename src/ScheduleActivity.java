import processing.core.PImage;

import java.util.List;

public abstract class ScheduleActivity extends EntityBasic {
    protected int actionPeriod;

    public ScheduleActivity(String id, Point position, List<PImage> images, int actionPeriod){
        super(id,position,images);
        this.actionPeriod = actionPeriod;
    }

    public void scheduleActions(Entity entity, WorldModel world, EventScheduler scheduler, ImageStore imageStore){
        scheduler.scheduleEvent(entity,
                ActionFactory.createActivityAction((Actor)entity, world, imageStore),
                ((Actor)entity).getActionPeriod());
    }

    public int getActionPeriod() {
        return actionPeriod;
    }

    public abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);



}
