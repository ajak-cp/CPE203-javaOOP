public class Point{
    private double x;
    private double y;
    private double z;

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Point scale(double scaleFactor){
        this.x = x * scaleFactor;
        this.y = y * scaleFactor;
        this.z = z * scaleFactor;
        return this;
    }
    public Point translate(Point translatePt){
        this.x = x + translatePt.getX();
        this.y = y + translatePt.getY();
        return this;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        Point point = (Point) object;

        if (x != point.x) return false;
        if (y != point.y) return false;
        if (z != point.z) return false;

        return true;
    }

    public int hashCode() {
        double result = super.hashCode();
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + z;
        return (int)result;
    }

    public Point(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

}