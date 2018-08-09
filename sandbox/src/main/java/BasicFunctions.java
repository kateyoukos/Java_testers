public class BasicFunctions {

    public static void main(String[] args){
        Point p1 = new Point(1, 5);
        Point p2 = new Point(4, 4);
        //run method from Point Class
        System.out.println("Distance between point = " + p1.distanceMethodInPointClass(p2));
        //run method from current(BasicFunctions) Class
        System.out.println(distance(p1, p2));
    }

    public static double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow((p2.b - p1.b), 2) + Math.pow((p2.a - p1.a), 2));
    }
}