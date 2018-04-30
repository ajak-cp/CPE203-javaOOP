import processing.core.PImage;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Vein extends ScheduleActivity implements Entity, Actor{

    private static final String ORE_ID_PREFIX = "ore -- ";
    private static final int ORE_CORRUPT_MIN = 20000;
    private static final int ORE_CORRUPT_MAX = 30000;
    private static final Random rand = new Random();
    private static final int ORE_REACH = 1;
    protected int actionPeriod;

    public Vein(String id, Point position,
                     List<PImage> images, int actionPeriod)
    {
        super(id,position,images, actionPeriod);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Point> openPt = findOpenAround(world,this.position);

        if (openPt.isPresent())
        {
            Ore ore = Create.createOre(ORE_ID_PREFIX + this.id,
                    ORE_CORRUPT_MIN +
                            rand.nextInt(ORE_CORRUPT_MAX - ORE_CORRUPT_MIN), openPt.get(),
                    imageStore.getImageList(Functions.ORE_KEY));
            world.addEntity(ore);
            scheduleActions(ore, world,scheduler, imageStore);
        }

        scheduler.scheduleEvent(this,
                ActionFactory.createActivityAction(this,world, imageStore),
                this.actionPeriod);
    }

    public static Optional<Point> findOpenAround(WorldModel world, Point pt)
    {
        for (int dy = -ORE_REACH; dy <= ORE_REACH; dy++)
        {
            for (int dx = -ORE_REACH; dx <= ORE_REACH; dx++)
            {
                Point newPt = new Point(pt.getX() + dx, pt.getY() + dy);
                if (world.withinBounds(newPt) &&
                        !world.isOccupied(newPt))
                {
                    return Optional.of(newPt);
                }
            }
        }

        return Optional.empty();
    }

    public List<PImage> getImages(){return images;}

    public int getActionPeriod() {
        return actionPeriod;
    }
    public void nextImage()
    {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

}
