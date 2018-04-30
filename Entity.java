import java.util.List;
import java.util.Optional;
import java.util.Random;

import processing.core.PImage;
//Version 2.0 Friday October 24th
interface Entity {

  Point getPosition();
  void setPosition(Point newPos);
  List<PImage> getImages();
  PImage getCurrentImage();

}
