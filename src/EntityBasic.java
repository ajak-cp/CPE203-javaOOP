import processing.core.PImage;

import java.util.List;

public abstract class EntityBasic implements Entity{
    protected int imageIndex = 0;
    protected Point position;
    protected String id;
    protected List<PImage> images;


    public EntityBasic(String id, Point position,
                        List<PImage> images){
        this.position = position;
        this.id = id;
        this.images = images;
    }

    public PImage getCurrentImage() {
        return (images.get((this.imageIndex)));
    }

    public Point getPosition(){
        return this.position;
    }

    public void setPosition(Point newPos){this.position = newPos;}

    public List<PImage> getImages(){return images;}

}
