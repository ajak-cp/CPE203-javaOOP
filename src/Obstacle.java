import processing.core.PImage;

import java.util.List;

public class Obstacle extends EntityBasic{
    public Obstacle(String id, Point position, List<PImage> images) {
        super(id,position,images);
    }

    public <R> R accept(EntityVisitor<R> visitor)
    {
        return visitor.visit(this);
    }

}
