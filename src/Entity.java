import java.util.List;
import java.util.Optional;
import java.util.Random;

import processing.core.PImage;
//Version 3.0 Friday November 9th
//BOOM test!

interface Entity {

  Point getPosition();
  void setPosition(Point newPos);
  List<PImage> getImages();
  PImage getCurrentImage();

  <R> R accept(EntityVisitor<R> visitor);

}
