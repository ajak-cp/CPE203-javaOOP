import processing.core.PImage;

import java.util.List;

public abstract class ScheduleAnimation extends EntityBasic{
        protected int actionPeriod;
        protected int animationPeriod;

        public ScheduleAnimation(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod){
            super(id,position,images);
            this.actionPeriod = actionPeriod;
            this.animationPeriod = animationPeriod;
        }

    @Override
    public List<PImage> getImages() {
        return images;
    }

    public void scheduleActions(Entity entity, WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        scheduler.scheduleEvent(entity,
                ActionFactory.createActivityAction((Actor)entity, world, imageStore),
                ((AnimatedActor)entity).getActionPeriod());
        scheduler.scheduleEvent(entity,
                ActionFactory.createAnimationAction((Animated)entity, ((AnimatedActor)entity).getAnimationPeriod()),
                ((AnimatedActor)entity).getAnimationPeriod());
    }
}
