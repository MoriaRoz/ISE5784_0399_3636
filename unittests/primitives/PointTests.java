package primitives;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Unit tests class for {@link Point} */
class PointTests {

    Point p1;
    Point p2;
    Point Zero=new Point(0,0,0);

    /**
     * Test method for {@link Point#subtract(Point)}.
     * This method tests the subtract method of the Point class
     * to ensure it correctly subtracts another point.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============

        //T1: Two points
        p1=new Point(1,2,3);
        p2=new Point(4,5,6);

        Point result=p1.subtract(p2);
        Point expected=new Point(p1.xyz.d1-p2.xyz.d1,p1.xyz.d2-p2.xyz.d2,p1.xyz.d3-p2.xyz.d3);
        
        assertEquals(expected, result,
                "The subtract between 2 points isn't working");

        // =============== Boundary Values Tests ==================

        //T2:Two same points
        p1=new Point(1,2,3);
        p2=new Point(1,2,3);

        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p2),
                "Subtracting two same points does not throw an exception");
    }

    /**
     * Test method for {@link Point#add(Vector)}.
     * This method tests the add method of the Point class
     * to ensure it correctly adds a vector.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============

        //T1: Two points
        this.p1 =new Point(1,2,3);
        Vector v=new Vector(4,5,6);


        Point result= this.p1.add(v);

        Point expected=new Point(this.p1.xyz.d1 + v.xyz.d1,
                                 this.p1.xyz.d2 + v.xyz.d2,
                                 this.p1.xyz.d3 + v.xyz.d3);

        assertEquals(expected, result,
                "The add between point and vector isn't working");

        // =============== Boundary Values Tests ==================

        //T2: Two opposite points
        p1 =new Point(1,2,3);
        v=new Vector(-1,-2,-3);

        result= p1.add(v);

        assertEquals(Zero, result,
                "The add between vector opposite to a point isn't working");
    }

    /**
     * Test method for {@link Point#distanceSquared(Point)}.
     * This method tests the distanceSquared method of the Point class
     * to ensure it correctly calculates the squared distance between two points.
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============

        //T1: Two points
        p1=new Point(1,2,3);
        p2=new Point(4,5,6);

        Double result=p1.distanceSquared(p2);
        Double expected=((p1.xyz.d1-p2.xyz.d1)*(p1.xyz.d1-p2.xyz.d1)+
                         (p1.xyz.d2-p2.xyz.d2)*(p1.xyz.d2-p2.xyz.d2)+
                         (p1.xyz.d3-p2.xyz.d3)*(p1.xyz.d3-p2.xyz.d3));

        assertEquals(expected, result,
                "The distanceSquared between 2 points isn't working");

        // =============== Boundary Values Tests ==================

        //T2: Two same points
        p1=new Point(1,2,3);
        p2=new Point(1,2,3);

        result=p1.distanceSquared(p2);

        assertEquals(0.0, result,
                "The distanceSquared between 2 same points isn't working");
    }

    /**
     * Test method for {@link Point#distance(Point)}.
     * This method tests the distance method of the Point class
     * to ensure it correctly calculates the distance between two points.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============

        //T1: Two points
        p1=new Point(1,2,3);
        p2=new Point(4,5,6);

        Double result=p1.distance(p2);
        Double expected=Math.sqrt((p1.xyz.d1-p2.xyz.d1)*(p1.xyz.d1-p2.xyz.d1)+
                                  (p1.xyz.d2-p2.xyz.d2)*(p1.xyz.d2-p2.xyz.d2)+
                                  (p1.xyz.d3-p2.xyz.d3)*(p1.xyz.d3-p2.xyz.d3));

        assertEquals(expected, result,
                "The distance between 2 points isn't working");

        // =============== Boundary Values Tests ==================

        //T2: Two same points
        p1=new Point(1,2,3);
        p2=new Point(1,2,3);

        result=p1.distance(p2);

        assertEquals(0.0, result,
                "The distance between 2 same points isn't working");
    }
}
