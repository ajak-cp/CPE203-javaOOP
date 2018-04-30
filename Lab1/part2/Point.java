import java.lang.Math;

public class Point
{
		private double x;
		private double y;

		public Point(double x, double y){
			this.x = x;
			this.y = y;
		}

		public double getX(){
			return x;
		}

		public double getY(){
			return y; 
		}

		public double getRadius(){
			double xDistance = getX() - 0;
			double yDistance  = getY() - 0;
			double hypotenuse = Math.pow(xDistance,2) + Math.pow(yDistance,2);
			return Math.sqrt(hypotenuse);
		}

		public double getAngle(){
			double yDistance = getY();
			double xDistance = getX();

			double answer = Math.atan2(yDistance,xDistance);
			return answer; 
		}

		public Point rotate90(){
			// (A,B) -> (-B,A)
			double invertedY = (getY() * -1);
			Point returnP = new Point(invertedY,getX());
			return returnP;
		}
	}
