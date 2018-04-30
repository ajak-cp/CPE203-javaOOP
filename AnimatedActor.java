public interface AnimatedActor extends Animated,Actor{
    int getAnimationPeriod();
    void nextImage();
    void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
    int getActionPeriod();
}
