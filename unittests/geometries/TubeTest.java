package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link Tube}.
 */
class TubeTest {

    /**
     * Test method for {@link Tube#getNormal(primitives.Point)}.
     * This method tests the getNormal method of the Tube class
     * to ensure it returns the correct normal vector at a given point on the tube's surface.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        Point head=new Point(0,0,0);
        Vector direction= new Vector(1,0,0);

        Tube t=new Tube(3,new Ray(head,direction));

//        Vector result=t.getNormal();
    }
}
