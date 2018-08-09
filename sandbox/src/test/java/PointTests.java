import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistanceCase1(){
        Point p1 = new Point(1, 7);
        Point p2 = new Point(4, 4);
        Assert.assertEquals(p1.distanceMethodInPointClass(p2), 4.242640687119285);
    }

    @Test
    public void testDistanceCase2(){
        Point p1 = new Point(1, 4);
        Point p2 = new Point(4, 4);
        Assert.assertEquals(p1.distanceMethodInPointClass(p2), 3);
    }

}
