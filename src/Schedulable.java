public interface Schedulable {
    void scheduleActions(Entity entity, WorldModel world, EventScheduler scheduler, ImageStore imageStore);
}
