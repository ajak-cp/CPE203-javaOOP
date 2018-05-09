import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.util.*;

final class WorldModel
{
   private int numRows;
   private int numCols;
   public Background background[][];
   private Entity occupancy[][];
   private Set<Entity> entities;
    public static final String RAINBOW_NAME = "rainbow_default";



    public WorldModel(int numRows, int numCols, Background defaultBackground)
   {
      this.numRows = numRows;
      this.numCols = numCols;
      this.background = new Background[numRows][numCols];
      this.occupancy = new Entity[numRows][numCols];
      this.entities = new HashSet<>();

      for (int row = 0; row < numRows; row++)
      {
         Arrays.fill(this.background[row], defaultBackground);
      }
   }

   public void moveEntity(Entity entity, WorldModel world, Point pos)
   {
      Point oldPos = entity.getPosition();
      if (world.withinBounds(pos) && !pos.equals(oldPos))
      {
         world.setOccupancyCell(oldPos, null);
         world.removeEntityAt(pos);
         world.setOccupancyCell(pos, entity);
         entity.setPosition(pos);
      }
   }

    public void drawRainbowWorld(int x, int y, Viewport view, List<PImage> listImage){
        Point worldPoint = view.viewportToWorld(x, y);

        if(withinBounds(worldPoint)) {
            setBackgroundCell(worldPoint, new Background(RAINBOW_NAME, listImage));
        }
    }

   public void setBackground(Point pos, Background background)
   {
      if (withinBounds(pos))
      {
         setBackgroundCell(pos, background);
      }
   }

   public boolean withinBounds(Point pos)
   {
      return pos.getY() >= 0 && pos.getY() < this.numRows &&
              pos.getX() >= 0 && pos.getX() < this.numCols;
   }

   public boolean isOccupied(Point pos)
   {
      return withinBounds(pos) &&
              getOccupancyCell(pos) != null;
   }

   public void setBackgroundCell(Point pos,
                                        Background background)
   {
      this.background[pos.getY()][pos.getX()] = background;
   }

   public Entity getOccupancyCell(Point pos)
   {
      return this.occupancy[pos.getY()][pos.getX()];
   }

   public void addEntity(Entity entity)
   {
      if (this.withinBounds(entity.getPosition()))
      {
         setOccupancyCell(entity.getPosition(), entity);
         this.entities.add(entity);
      }
   }

   public void setOccupancyCell(Point pos, Entity entity)
   {
      this.occupancy[pos.getY()][pos.getX()] = entity;
   }

   public void moveEntity(Entity entity, Point pos)
   {
      Point oldPos = entity.getPosition();
      if (this.withinBounds(pos) && !pos.equals(oldPos))
      {
         this.setOccupancyCell(oldPos, null);
         this.removeEntityAt(pos);
         this.setOccupancyCell(pos, entity);
         entity.setPosition(pos);
      }
   }

   public Background getBackgroundCell(Point pos)
   {
      return this.background[pos.getY()][pos.getX()];
   }


   public Optional<PImage> getBackgroundImage(Point pos)
   {
      if (this.withinBounds(pos))
      {
         return Optional.of(getBackgroundCell(pos).getCurrentImage(getBackgroundCell(pos)));
      }
      else
      {
         return Optional.empty();
      }
   }


   public Optional<Entity> findNearest(Point pos, Class kind)
   {
      List<Entity> ofType = new LinkedList<>();
      for (Entity entity : this.entities)
      {
         if (entity.getClass().equals(kind))
         {
            ofType.add(entity);
         }
      }

      return nearestEntity(ofType, pos);
   }


   public Optional<Entity> nearestEntity(List<Entity> entities, Point pos)
   {
      if (entities.isEmpty())
      {
         return Optional.empty();
      }
      else
      {
         Entity nearest = entities.get(0);
         int nearestDistance = nearest.getPosition().distanceSquared(pos);

         for (Entity other : entities)
         {
            int otherDistance = other.getPosition().distanceSquared(pos);

            if (otherDistance < nearestDistance)
            {
               nearest = other;
               nearestDistance = otherDistance;
            }
         }

         return Optional.of(nearest);
      }
   }

   public void removeEntity(Entity entity)
   {
      removeEntityAt(entity.getPosition());
   }


   public void removeEntityAt(Point pos)
   {
      if (this.withinBounds(pos)
              && this.getOccupancyCell(pos) != null)
      {
         Entity entity = this.getOccupancyCell(pos);

         /* this moves the entity just outside of the grid for
            debugging purposes */
         entity.setPosition(new Point(-1,-1));
         this.entities.remove(entity);
         this.setOccupancyCell(pos, null);
      }
   }

   public Optional<Entity> getOccupant(Point pos)
   {
      if (this.isOccupied(pos))
      {
         return Optional.of(this.getOccupancyCell(pos));
      }
      else
      {
         return Optional.empty();
      }
   }

   public void tryAddEntity(Point position, Entity entity)
   {
      if (this.isOccupied(position))
      {
         // arguably the wrong type of exception, but we are not
         // defining our own exceptions yet
         throw new IllegalArgumentException("position occupied");
      }

      addEntity(entity);
   }

    public int getNumRows(){
      return numRows;
   }
   public int getNumCols(){
      return numCols;
   }
   public Set<Entity> getEntities(){
      return entities;
   }


}
