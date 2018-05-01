import processing.core.PApplet;
import processing.core.PImage;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

final class WorldView
{
   private PApplet screen;
   private WorldModel world;
   private int tileWidth;
   private int tileHeight;
   private Viewport viewport;
   public static final String RAINBOW_ID = "rainbow";


   public WorldView(int numRows, int numCols, PApplet screen, WorldModel world,
      int tileWidth, int tileHeight)
   {
      this.screen = screen;
      this.world = world;
      this.tileWidth = tileWidth;
      this.tileHeight = tileHeight;
      this.viewport = new Viewport(numRows, numCols);
   }
   public void drawEntities()
   {
      for (Entity entity : this.world.getEntities())
      {
         Point pos = entity.getPosition();

         if (this.viewport.contains(pos))
         {
            Point viewPoint = this.viewport.worldToViewport(pos.getX(), pos.getY());
            this.screen.image(entity.getCurrentImage(),
                    viewPoint.getX() * this.tileWidth, viewPoint.getY() * this.tileHeight);
         }
      }
   }

   public void drawViewport()
   {
      drawBackground();
      this.drawEntities();
   }

   public void drawBackground() {
      for (int row = 0; row < this.viewport.getNumRows(); row++) {
         for (int col = 0; col < this.viewport.getNumCols(); col++) {
            Point worldPoint = this.viewport.viewportToWorld(col, row);
            Optional<PImage> image = this.world.getBackgroundImage(
                    worldPoint);
            if (image.isPresent()) {
               this.screen.image(image.get(), col * this.tileWidth,
                       row * this.tileHeight);
            }
         }
      }
   }

   public void shiftView(int colDelta, int rowDelta)
   {
      int newCol = clamp(this.viewport.getCol() + colDelta, 0,
              this.world.getNumCols() - this.viewport.getNumCols());
      int newRow = clamp(this.viewport.getRow() + rowDelta, 0,
              this.world.getNumRows() - this.viewport.getNumRows());

      this.viewport.shift(newCol, newRow);
   }

   public void drawRainbow(int x, int y, ImageStore imageStore){
      List<PImage> rainbowImageList = imageStore.getImageList(RAINBOW_ID);
      world.drawRainbowWorld(x,y,viewport, rainbowImageList); //Change here for different shapes on click
      world.drawRainbowWorld(x+1,y,viewport,rainbowImageList);
      world.drawRainbowWorld(x-1,y,viewport,rainbowImageList);
      world.drawRainbowWorld(x+2,y+1,viewport,rainbowImageList);
      world.drawRainbowWorld(x-2,y+1,viewport,rainbowImageList);
      world.drawRainbowWorld(x+3,y+2,viewport,rainbowImageList);
      world.drawRainbowWorld(x-3,y+2,viewport,rainbowImageList);
   }




   public static int clamp(int value, int low, int high)
   {
      return Math.min(high, Math.max(value, low));
   }


}
