public class Point {

    public double a;
    public double b;

    public Point(double a, double b){
        this.a = a;   // in 1-st case a - is an attribute; in 2-nd case a - is a variable
        this.b = b;
    }

    public double distanceMethodInPointClass(Point p1){
        return Math.sqrt(Math.pow((this.b - p1.b), 2) + Math.pow((this.a - p1.a), 2));
    }
}
