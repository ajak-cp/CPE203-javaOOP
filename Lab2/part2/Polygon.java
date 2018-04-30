import java.util.*;

public class Polygon{ 

	private final List<Point> vertices;

	public Polygon(List<Point> vertices){
		this.vertices = vertices;
	}
	public List<Point> getPoints(){
		return vertices;
	}
	/* 
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
	*/

	public double perimeter(){
		double totalPerim = 0.0;
		for (int x = 0; x < vertices.size()-1; x++){
			Point temp1 = vertices.get(x);
			Point temp2 = vertices.get(x+1);
			double distance = Math.sqrt(Math.pow(temp1.getX() - temp2.getX(),2) + Math.pow(temp1.getY() - temp2.getY(),2));
			totalPerim = totalPerim + distance;
		}
		Point lastPt = vertices.get(vertices.size()-1);
		Point firstPt = vertices.get(0);
		double firstAndLast = Math.sqrt(Math.pow(lastPt.getX() - firstPt.getX(),2) + Math.pow(lastPt.getY() - firstPt.getY(),2));

		totalPerim = totalPerim + firstAndLast;
		return totalPerim;
	}
}