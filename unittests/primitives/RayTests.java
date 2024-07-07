package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests class for {@link Ray} class
 */
public class RayTests {

    /**
     * Test method for {@link primitives.Ray#getPoint(double)}
     */
    @Test
    void testGetPoint() {
        Point p = new Point(1, 2, 3);
        Vector v = new Vector(1, 1, 1);
        Ray r = new Ray(p, v);

        // ============ Equivalence Partitions Tests ==============
        //T1: positive distance
        Point result1 = r.getPoint(2.0);
        Point expected1 = new Point(2.1547005383792515, 3.1547005383792515, 4.1547005383792515);
        assertEquals(expected1, result1, "The getPoint method is not working correctly for positive distance");

        //T2: negative distance
        Point result2 = r.getPoint(-2.0);
        Point expected2 = new Point(-0.15470053837925168, 0.8452994616207483, 1.8452994616207483);
        assertEquals(expected2, result2, "The getPoint method is not working correctly for negative distance");

        // =============== Boundary Values Tests ==================
        //T3: Distance is equal to 0
        Point result3 = r.getPoint(0.0);
        assertEquals(p, result3, "The getPoint method is not working correctly for distance equal to 0");
    }

    /**
     * Test method for {@link primitives.Ray#findClosestPoint(java.util.List)}
     */
    @Test
    void testFindClosestPoint() {
        Point p = new Point(0, 0, 1);
        Vector v = new Vector(1, 1, 1);
        Ray r = new Ray(p, v);

        final Point p002 = new Point(0, 0, 2);
        final Point p234 = new Point(2, 3, 4);
        final Point p345 = new Point(3, 4, 5);

        // ============ Equivalence Partitions Tests ==============
        //T1: The closest point is the middle point in the list
        Point result1 = r.findClosestPoint(List.of(p234, p002, p345));
        assertEquals(p002, result1, "The findClosestPoint method is not working correctly when the closest point is the middle point in the list");

        // =============== Boundary Values Tests ==================

        //T2: The closest point is the first point in the list
        Point result2 = r.findClosestPoint(List.of(p002, p234, p345));
        assertEquals(p002, result2, "The findClosestPoint method is not working correctly when the closest point is the head of the ray");

        //T3: The closest point is the last point in the list
        Point result3 = r.findClosestPoint(List.of(p234, p345, p002));
        assertEquals(p002, result3, "The findClosestPoint method is not working correctly when the closest point is the last point in the list");

        //T4: The list is empty
        Point result4 = r.findClosestPoint(List.of());
        assertNull(result4, "The findClosestPoint method is not working correctly when the list is empty");
    }
}
