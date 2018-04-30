import java.lang.reflect.Method;
   import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;
import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class PartOneTestCases
{
   public static final double DELTA = 0.00001;

   @Test
   public void testPerimPoly() {
        List<Point> points = new ArrayList<Point>(); 
        points.add(new Point(0.0, 0.0));
        points.add(new Point(3.0,0.0));
        points.add(new Point(0.0,4));
        double d = Util.perimeter(new Polygon(points));
        assertEquals(12.0, d, DELTA);
   }

   @Test
   public void testPerimPoly2(){
      List<Point> points = new ArrayList<Point>(); 
      points.add(new Point(-5, 3));
      points.add(new Point(4,2));
      points.add(new Point(0,4));
      double d = Util.perimeter(new Polygon(points));
      assertEquals(18.626540606729783, d, DELTA);
   }

   @Test
   public void testPerimPoly3(){ //Square sides length 2.0 
      List<Point> points = new ArrayList<Point>(); 
      points.add(new Point(1, 1));
      points.add(new Point(1,-1));
      points.add(new Point(-1,-1));
      points.add(new Point(-1,1));

      double d = Util.perimeter(new Polygon(points));
      assertEquals(8.0, d, DELTA);
   }

   @Test
   public void testPerimRect(){ 
      Rectangle rect = new Rectangle(new Point(0,0), new Point(5,5));
      double d = Util.perimeter(rect);
      assertEquals(20.0, d, DELTA);
   }

   @Test
   public void testPerimRect2(){ 
      Rectangle rect = new Rectangle(new Point(-10,-5), new Point(6,3));
      double d = Util.perimeter(rect);
      assertEquals(48.0, d, DELTA);
   }

   @Test
   public void testPerimCircle(){ 
      Circle circlez = new Circle(new Point(-10,-5), 5.0);
      double d = Util.perimeter(circlez);
      assertEquals(25.0 * Math.PI, d, DELTA);
   }

   @Test
   public void testPerimCircle2(){ 
      Circle circlez = new Circle(new Point(-10,-5), -3.0);
      double d = Util.perimeter(circlez);
      assertEquals(9.0 * Math.PI, d, DELTA);
   }

   @Test
   public void testBiggerCirc(){
      Circle circ = new Circle(new Point(0,-5), 50.0);
      Rectangle rect = new Rectangle(new Point(-5.0,2.0),new Point(6.0,2.4));
      List<Point> points = new ArrayList<Point>(); 
      points.add(new Point(1, 1));
      points.add(new Point(1,-1));
      points.add(new Point(-1,-1));
      points.add(new Point(-1,1));
      Polygon poly = new Polygon(points);
      double d = Util.perimeter(circ);
      assertEquals(Math.pow(50.0,2)*Math.PI,d,DELTA);
   }

   @Test
   public void testBiggerRect(){
      Circle circ = new Circle(new Point(0,-5), 2.0);
      Rectangle rect = new Rectangle(new Point(-5.0,2.0),new Point(5.0,2.4));
      List<Point> points = new ArrayList<Point>(); 
      points.add(new Point(1, 1));
      points.add(new Point(1,-1));
      points.add(new Point(-1,-1));
      points.add(new Point(-1,1));
      Polygon poly = new Polygon(points);
      double d = Util.perimeter(rect);
      assertEquals(20.8,d,DELTA);
   }

   @Test
   public void testBiggerPoly(){
      Circle circ = new Circle(new Point(0,-5), 2.0); //12.56
      Rectangle rect = new Rectangle(new Point(-5.0,2.0),new Point(5.0,2.4)); //20.8
      List<Point> points = new ArrayList<Point>(); 
      points.add(new Point(2, 3));
      points.add(new Point(1,-1));
      points.add(new Point(-1,-7));
      points.add(new Point(-3,2));
      Polygon poly = new Polygon(points);
      double d = Util.perimeter(poly);
      assertEquals(24.766224916840095,d,DELTA);
   }

   @Test
   public void testBiggerInstructorNumbers(){
      Circle circ = new Circle(new Point(1.0,1.0), 2.0); //12.56
      Rectangle rect = new Rectangle(new Point(-1.0,2.0),new Point(1.0,-1.6));//11.2
      List<Point> points = new ArrayList<Point>(); 
      points.add(new Point(0, 0));
      points.add(new Point(3,1));
      points.add(new Point(1,4));
      points.add(new Point(-1,4));
      Polygon poly = new Polygon(points); // 11.76
      double d = Util.perimeter(circ);
      assertEquals(4*Math.PI,d,DELTA);
   }


   @Test
   public void testCircleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getCenter", "getRadius");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Point.class, double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[0]);

      verifyImplSpecifics(Circle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testRectangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getTopLeft", "getBottomRight");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Point.class, Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[0]);

      verifyImplSpecifics(Rectangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testPolygonImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getPoints");

      final List<Class> expectedMethodReturns = Arrays.asList(
         List.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[][] {new Class[0]});

      verifyImplSpecifics(Polygon.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testUtilImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "perimeter", "perimeter", "perimeter");

      final List<Class> expectedMethodReturns = Arrays.asList(
         double.class, double.class, double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[] {Circle.class},
         new Class[] {Polygon.class},
         new Class[] {Rectangle.class});

      verifyImplSpecifics(Util.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   private static void verifyImplSpecifics(
      final Class<?> clazz,
      final List<String> expectedMethodNames,
      final List<Class> expectedMethodReturns,
      final List<Class[]> expectedMethodParameters)
      throws NoSuchMethodException
   {
      assertEquals("Unexpected number of public fields",
         0, Point.class.getFields().length);

      final List<Method> publicMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()))
            .collect(Collectors.toList());

      assertEquals("Unexpected number of public methods",
         expectedMethodNames.size(), publicMethods.size());

      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodReturns.size());
      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodParameters.size());

      for (int i = 0; i < expectedMethodNames.size(); i++)
      {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
            expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }
}
