import java.security.cert.CollectionCertStoreParameters;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.abs;

class SingleStepPathingStrategy implements PathingStrategy {

    public class Node {
        Point p;
        Node previous;
        int g;
        int h;

        @Override
        public boolean equals(Object o) {
           if(o == null){
               return false;
           }
            if (getClass() != o.getClass()) {
                return false;
            }
            final Node other = (Node) o;
            if (this.p.getX() != other.p.getX()) {
                return false;
            }
            if (this.p.getY() != other.p.getY()) {
                return false;
            }
            return true;

        }



        public Node(Point p) {
            this.p = p;
            this.g = 0;
            this.h = 0;
            this.previous = null;
        }

        public void setPrevious(Node previous){
            this.previous = previous;
        }

        public Node getPrevious(){
            if (this.previous == null){
                Node returnNode = new Node(this.p);
                return returnNode;
            }
            return previous;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "p=" + p +
                    ", previous=" + previous +
                    ", g=" + g +
                    ", h=" + h +
                    '}';
        }

    }

    public Node lowestF(List<Node> inputList) {
        Node smalledFNode = inputList.get(0);
        for(int i=0;i<inputList.size();i++){
            if((inputList.get(i).h + inputList.get(i).g) < (smalledFNode.g + smalledFNode.h)){
                smalledFNode = inputList.get(i);
            }
        }
        return smalledFNode;
    }

    public LinkedList<Point> constructPath(Node start, Node end){
        LinkedList<Point> path = new LinkedList<>();
        Node current = end;
        boolean flag = false;
        while(!flag){
            path.addFirst(current.p);
            current = current.getPrevious();

            if(current.p.equals(start.p)){
                flag = true;
            }
        }
        return path;
    }
    /* replaced this with streams
    public LinkedList<Point> getValidAdjacent(Node start){
        LinkedList<Node> returnList = new LinkedList<>();
        Node current;


    }*/

    public boolean isInClosed(List<Node> closedList, Point pt){
        boolean flag = false;
        for (Node n : closedList){
            if(n.p.equals(pt)){
                flag = true;
            }
        }
        return flag;
    }

    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors) {

        List<Node> open = new LinkedList<>();
        List<Node> closed = new LinkedList<>();
        Node startNode = new Node(start);
        startNode.h =  Math.abs((start.getX() - end.getX()) + Math.abs(start.getY() - end.getY()));
        open.add(startNode);
        Node current;
        List<Point> tempList;
        List<Node> nodeList = new LinkedList<>();
        //System.out.println("End pt is: " + end.toString());

        boolean flag = false;
        while (!flag) {
            current = lowestF(open);
            closed.add(current);
            open.remove(current);
            if (current.p.equals(end)) {
                return constructPath(new Node(start), current);
            }

            tempList = potentialNeighbors.apply(current.p).filter(canPassThrough).filter(pt ->
                    !pt.equals(start) && !isInClosed(closed,pt)).collect(Collectors.toList());

            //System.out.println(tempList.toString());
            for (Point thisPt : tempList) { //Transfer to node
                Node newNode = new Node(thisPt);
                newNode.h = Math.abs((newNode.p.getX() - end.getX())) + Math.abs(newNode.p.getY() - end.getY());
                newNode.g = Math.abs((newNode.p.getX() - start.getX())) + Math.abs(newNode.p.getY() - start.getY());
                nodeList.add(newNode);
            }

            if(current.p.getX() == end.getX() && (current.p.getY() + 1) == end.getY()){ //The point above is end
                Node newNode = new Node(new Point(current.p.getX(),current.p.getY()+1));
                newNode.h = Math.abs((newNode.p.getX() - end.getX())) + Math.abs(newNode.p.getY() - end.getY());
                newNode.g = Math.abs((newNode.p.getX() - start.getX())) + Math.abs(newNode.p.getY() - start.getY());
                nodeList.add(newNode); }
            if (current.p.getX() == end.getX() && (current.p.getY() - 1) == end.getY()){ //down
                Node newNode = new Node(new Point(current.p.getX(),current.p.getY()-1));
                newNode.h = Math.abs((newNode.p.getX() - end.getX())) + Math.abs(newNode.p.getY() - end.getY());
                newNode.g = Math.abs((newNode.p.getX() - start.getX())) + Math.abs(newNode.p.getY() - start.getY());
                nodeList.add(newNode); }
            if ((current.p.getX() + 1) == end.getX() && (current.p.getY()) == end.getY()){
                Node newNode = new Node(new Point(current.p.getX() + 1,current.p.getY())); //right
                newNode.h = Math.abs((newNode.p.getX() - end.getX())) + Math.abs(newNode.p.getY() - end.getY());
                newNode.g = Math.abs((newNode.p.getX() - start.getX())) + Math.abs(newNode.p.getY() - start.getY());
                nodeList.add(newNode); }
            if ((current.p.getX() - 1) == end.getX() && (current.p.getY()) == end.getY()){
                Node newNode = new Node(new Point(current.p.getX() - 1,current.p.getY()));
                newNode.h = Math.abs((newNode.p.getX() - end.getX())) + Math.abs(newNode.p.getY() - end.getY());
                newNode.g = Math.abs((newNode.p.getX() - start.getX())) + Math.abs(newNode.p.getY() - start.getY());
                nodeList.add(newNode);
            }

            for (int i = 0; i < nodeList.size(); i++) {
                Node thisNode = nodeList.get(i);
                if (!open.contains(thisNode)) {
                    thisNode.previous = current;
                    thisNode.h = Math.abs((thisNode.p.getX() - end.getX())) + Math.abs(thisNode.p.getY() - end.getY());
                    thisNode.g = Math.abs((thisNode.p.getX() - start.getX())) + Math.abs(thisNode.p.getY() - start.getY());
                    open.add(thisNode);

                } else {
                    if (thisNode.g > thisNode.getPrevious().g) {
                        thisNode.previous = current;
                        thisNode.g = Math.abs((thisNode.p.getX() - start.getX())) + Math.abs(thisNode.p.getY() - start.getY());
                    }
                }
            }

            nodeList = new LinkedList<>();

            if (open.isEmpty()) {
                return null;
            }
        }
        return null;
    }

    public List<Point> computePathExample(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors) {
        return potentialNeighbors.apply(start)
                .filter(canPassThrough)
                .filter(pt ->
                        !pt.equals(start)
                                && !pt.equals(end)
                                && Math.abs(end.getX() - pt.getX()) <= Math.abs(end.getX() - start.getX())
                                && Math.abs(end.getY() - pt.getY()) <= Math.abs(end.getY() - start.getY()))
                .limit(1)
                .collect(Collectors.toList());
    }

}




