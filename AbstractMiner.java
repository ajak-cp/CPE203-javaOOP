import processing.core.PImage;

import java.util.List;

public abstract class AbstractMiner extends ScheduleAnimation {
        protected int resourceCount;
        protected int resourceLimit;

        public AbstractMiner(String id, Point position, List<PImage> images, int actionPeriod,
                             int animationPeriod, int resourceCount, int resourceLimit){
            super(id,position,images,actionPeriod,animationPeriod);
            this.resourceCount = resourceCount;
            this.resourceLimit = resourceLimit;
        }
}
