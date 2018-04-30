public class Bigger{
	public static double whichIsBigger(Circle circle, Rectangle rectangle, Polygon polygon){
		double circlePerim = circle.perimeter();
		double rectanglePerim = rectangle.perimeter();
		double polygonPerim = polygon.perimeter(); 
		
		if (circlePerim > rectanglePerim && circlePerim > polygonPerim){
			return circlePerim; 
		}
		else if (rectanglePerim > circlePerim && rectanglePerim > polygonPerim){
			return rectanglePerim;
		}
		else if (polygonPerim > circlePerim && polygonPerim > rectanglePerim){
			return polygonPerim;
		}
		else if (polygonPerim == rectanglePerim){
			return polygonPerim;
		}
		else if (polygonPerim == circlePerim){
			return polygonPerim;
		}
		else { 
			return rectanglePerim;
		}
	}
}