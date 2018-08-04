import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance(){
        Point p1 = new Point(2, 6);
        Point p2 = new Point(3, 8);
        Assert.assertEquals(Point.distanceMethodInPointClass(p1, p2), 8.48528137423857);
    }
}
