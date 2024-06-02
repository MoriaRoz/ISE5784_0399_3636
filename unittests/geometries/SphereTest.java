package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/** Unit tests class for {@link Sphere} */
class SphereTest {
    private final double DELTA = 0.000001;

    /**
     * Test method for {@link Sphere#getNormal(primitives.Point)}.
     * This method tests the getNormal method of the Sphere class
     * to ensure it returns the correct normal vector at a given point on the sphere's surface.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        Point center=new Point(0,0,0);
        Sphere s=new Sphere(5,center);
        Point p=new Point(5,0,0);

        Vector result=s.getNormal(p);
        Vector expected=(p.subtract(center));

        //T1: check if length=1
        assertEquals(1, result.length(),
                "The getNormal on sphere isn't working- not of length 1");

        //T2: check if the direction is the same
        assertThrows(IllegalArgumentException.class, () -> result.crossProduct(expected),
                "The getNormal on sphere isn't working- not in the direction of the point");
    }
}
