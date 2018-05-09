import processing.core.PImage;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public abstract class AbstractMiner extends ScheduleAnimation {
        int resourceCount;
        int resourceLimit;

        public AbstractMiner(String id, Point position, List<PImage> images, int actionPeriod,
                             int animationPeriod, int resourceCount, int resourceLimit){
            super(id,position,images,actionPeriod,animationPeriod);
            this.resourceCount = resourceCount;
            this.resourceLimit = resourceLimit;
        }

    Point nextPositionMiner(WorldModel world, Point destPos)
    {
        PathingStrategy SingleStep = new SingleStepPathingStrategy();
        Predicate<Point> passThrough = p ->!world.isOccupied(p) && world.withinBounds(p);
        BiPredicate<Point, Point> withinReach = (Point p1, Point p2) -> OreBlob.adjacent(p1,p2);
        List<Point> path = SingleStep.computePath(getPosition(), destPos, passThrough, withinReach, PathingStrategy.CARDINAL_NEIGHBORS);

        if (path == null) {
            return this.getPosition();
        } else if(path.size() == 0) {
            return this.getPosition();
        }
            return path.get(0);
        }

        /*int horiz = Integer.signum(destPos.getX() - this.position.getX());
        Point newPos = new Point(this.position.getX() + horiz,
                this.position.getY());

        if (horiz == 0 || world.isOccupied(newPos))
        {
            int vert = Integer.signum(destPos.getY() - this.position.getY());
            newPos = new Point(this.position.getX(),
                    this.position.getY() + vert);

            if (vert == 0 || world.isOccupied(newPos))
            {
                newPos = this.position;
            }
        }

        return newPos;
    }*/

    }

