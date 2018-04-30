import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.*;


public class Stream {

    public static void main (String args[])
    {
        List<Point> points = new ArrayList<>();

        try{
            readInPoints(points);
        }
        catch(Exception e){
            System.out.println("try again");
        }


        points = points.stream()
                .filter(p -> p.getZ() <= 2.0)
                .map(p -> new Point(p.getX() * 0.50, p.getY() * 0.50, p.getZ() * 0.50))
                .map(p -> new Point(p.getX() -150, p.getY() - 37, p.getZ()))
                .collect((Collectors.toList()));
        writePoints(points);

    }

    public static void readInPoints(List<Point> points){


        try{
            Scanner in = new Scanner(new File("point.txt"));
            while (in.hasNext()){
                String[] line = in.nextLine().split(",");
                points.add(new Point(Double.parseDouble(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2])));
                in.nextLine();
            }

        }
        catch (Exception e){
            System.out.println("Cant open input file.");
        }

    }

    public static void writePoints(List<Point> points){

        try
        {
            PrintStream ps = new PrintStream("drawMe.txt");
            for (Point p : points){
                ps.println(p);
            }
        }

        catch (Exception e){

            System.out.println("Cant open the file, homie");
        }



    }


}
