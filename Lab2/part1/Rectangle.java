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
}