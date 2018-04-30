import processing.core.PImage;

import java.util.List;

public class Obstacle extends EntityBasic{
    public Obstacle(String id, Point position, List<PImage> images) {
        super(id,position,images);
    }

    public List<PImage> getImages(){return images;}

}
