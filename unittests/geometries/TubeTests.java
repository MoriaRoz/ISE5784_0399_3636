package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/** Unit test class for {@link Tube} */
class TubeTests {

    /** Test method for {@link Tube#getNormal(primitives.Point)} */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        Point head=new Point(0,0,0);
        Vector direction= new Vector(1,0,0);
        Point p=new Point(1,3,0);

        Tube t=new Tube(3,new Ray(head,direction));

        //T1: Point on the tube's surface
        Vector result = t.getNormal(p);
        Vector expected=p.subtract(new Point(1,0,0)).normalize();

        assertEquals(expected, result,
                "The normal to the tube is not correct");

        // =============== Boundary Values Tests ==================

        //T2: Point in front of the head ray
        Point point=new Point(3,0,0);

        assertThrows(IllegalArgumentException.class, () -> t.getNormal(point),
                "The normal to the tube is not correct- point in front of the head ray");
    }
}
