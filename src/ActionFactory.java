public class ActionFactory {
    public static Action createAnimationAction(Animated entity, int repeatCount)
    {
        return new Animation(entity, null, null, repeatCount);
    }

    public static Action createActivityAction(Actor entity, WorldModel world,
                                                ImageStore imageStore)
    {
        return new Activity(entity, world, imageStore, 0);
    }
}
