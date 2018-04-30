import java.lang.Math;

public class Util{
	public static double perimeter(Circle circle){
		double perim = Math.pow(circle.getRadius(),2) * Math.PI; 
		return perim;
	}

	public static double perimeter(Polygon polygon){
		double totalPerim = 0.0;
		for (int x = 0; x < polygon.getPoints().size()-1; x++){
			Point temp1 = polygon.getPoints().get(x);
			Point temp2 = polygon.getPoints().get(x+1);
			double distance = Math.sqrt(Math.pow(temp1.getX() - temp2.getX(),2) + Math.pow(temp1.getY() - temp2.getY(),2));
			//System.out.println(temp1,temp2);
			totalPerim = totalPerim + distance;
		}
		Point lastPt = polygon.getPoints().get(polygon.getPoints().size()-1);
		Point firstPt = polygon.getPoints().get(0);
		double firstAndLast = Math.sqrt(Math.pow(lastPt.getX() - firstPt.getX(),2) + Math.pow(lastPt.getY() - firstPt.getY(),2));

		totalPerim = totalPerim + firstAndLast;
		return totalPerim;
	}

	public static double perimeter(Rectangle rectangle){
		double sideOne = Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()) * 2; //|X2 - X1|*2
		double sideTwo = Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()) * 2; //|Y2 - Y1|*2
		double perim = sideTwo + sideOne; 
		return perim; 
	}
}