import java.lang.Math; 

public class Circle{
	private final Point center;
	private final double radius;

	public Circle(){
		this.center = new Point(0,0);
		this.radius = 0.0;
	}

	public Circle(Point center, double radius){
		this.center = center;
		this.radius = radius;
	}

	public Point getCenter(){
		return center;
	}

	public double getRadius(){
		return radius; 
	}

	public double perimeter(){ //double perim = Math.pow(circle.getRadius(),2) * Math.PI; 
		double perim = 2 * Math.PI * this.radius;
		return perim;
	}

}