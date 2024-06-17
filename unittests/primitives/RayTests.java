package primitives;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RayTests {

    @Test
    void testGetPoint(){
        Point p = new Point(1, 2, 3);
        Vector v = new Vector(1, 1, 1);
        Ray r = new Ray(p, v);

        // ============ Equivalence Partitions Tests ==============
        //T1: positive distance
        Point result1 = r.getPoint(2.0);
        Point expected1 = new Point(2.1547005383792515,3.1547005383792515,4.1547005383792515);
        assertEquals(expected1, result1, "The getPoint method is not working correctly for positive distance");

        //T2: negative distance
        Point result2 = r.getPoint(-2.0);
        Point expected2 = new Point(-0.15470053837925168,0.8452994616207483,1.8452994616207483);
        assertEquals(expected2, result2, "The getPoint method is not working correctly for negative distance");

        // =============== Boundary Values Tests ==================
        //T3: Distance is equal to 0
        Point result3 = r.getPoint(0.0);
        assertEquals(p, result3, "The getPoint method is not working correctly for distance equal to 0");
    }
}
