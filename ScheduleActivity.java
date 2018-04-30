import processing.core.PImage;

import java.util.List;

public abstract class ScheduleActivity extends EntityBasic {
    protected int actionPeriod;
    protected Point position;
    protected String id;
    protected List<PImage> images;

    public ScheduleActivity(String id, Point position, List<PImage> images, int actionPeriod){
        super(id,position,images);
        this.actionPeriod = actionPeriod;
    }

    public void scheduleActions(Entity entity, WorldModel world, EventScheduler scheduler, ImageStore imageStore){
        scheduler.scheduleEvent(entity,
                ActionFactory.createActivityAction((Actor)entity, world, imageStore),
                ((AnimatedActor)entity).getActionPeriod());
    }
}
