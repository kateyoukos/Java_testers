public class BasicFunctions {

    public static void main(String[] args){
        Point p1 = new Point(2, 6);
        Point p2 = new Point(3, 8);
        //run method from current(BasicFunctions) Class
        System.out.println("Distance between point = " + distance(p1, p2));
        //run method from Point Class
        System.out.println(Point.distanceMethodInPointClass(p1, p2));
    }

    public static double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow((p2.b - p1.a), 2) + Math.pow((p2.b - p1.a), 2));
    }
}