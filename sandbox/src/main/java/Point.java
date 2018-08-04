public class Point {

    public double a;
    public double b;

    public Point(double a, double b){
        this.a = a;   // in 1-st case a - is an attribute; in 2-nd case a - is a variable
        this.b = b;
    }

    public static double distanceMethodInPointClass(Point p1, Point p2){
        return Math.sqrt(Math.pow((p2.b - p1.a), 2) + Math.pow((p2.b - p1.a), 2));
    }
}
