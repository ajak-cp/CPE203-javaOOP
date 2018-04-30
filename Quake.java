import processing.core.PImage;

import java.util.List;

public class Quake extends ScheduleAnimation implements AnimatedActor{

    protected int imageIndex = 0;
    protected Point position;
    protected int animationPeriod;
    protected String id;
    protected int actionPeriod;
    protected List<PImage> images;

    public Quake(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod) {
       super(id,position,images,actionPeriod,animationPeriod);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        scheduler.unscheduleAllEvents(this);
        world.removeEntity(this);
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

    public int getActionPeriod(){return actionPeriod;}
    public int getAnimationPeriod(){ return this.animationPeriod; }

    public void nextImage()
    {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

}
