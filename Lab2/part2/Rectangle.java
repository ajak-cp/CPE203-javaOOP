public class Rectangle{
	private final Point topLeft;
	private final Point bottomRight;

	public Rectangle(){
		topLeft = new Point(0,0);
		bottomRight = new Point(0,0);
	}
	public Rectangle(Point topLeft, Point bottomRight){
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
	}

	public Point getTopLeft(){
		return topLeft;
	}

	public Point getBottomRight(){
		return bottomRight;
	}

	public double perimeter(){
		//double sideOne = Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()) * 2; //|X2 - X1|*2
		//double sideTwo = Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()) * 2; //|Y2 - Y1|*2
		//double perim = sideTwo + sideOne; 
		double side1 = Math.abs((topLeft.getX() - bottomRight.getX()) * 2);
		double side2 = Math.abs((topLeft.getY() - bottomRight.getY()) * 2);
		double perim = side1 + side2;
		return perim;
	}
}