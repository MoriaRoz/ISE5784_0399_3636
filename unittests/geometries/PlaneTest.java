package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test class for {@link Plane}.
 */
class PlaneTest {

    /**
     * Test method for {@link Plane#Plane(Point, Point, Point)}.
     * This method tests the constructor of the Plane class to ensure it constructs a plane
     * with three different points.
     */
    @Test
    void constructorTest(){
        // =============== Boundary Values Tests ==================

        //T1: check if the points are different
        assertDoesNotThrow(() -> new Plane(new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(1, 0, 0)),
                "Failed constructing a correct Plane- 2 points are the same");

        //T2: check if the points are different
        assertDoesNotThrow(() -> new Plane(new Point(1, 0, 0),
                        new Point(2, 0, 0),
                        new Point(3, 0, 0)),
                "Failed constructing a correct Plane- 3 points are in the same line");
    }

    /**
     * Additional test method for {@link Plane#getNormal()}.
     * This method provides additional test cases for the getNormal method
     * of the Plane class to further ensure its correctness.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        Point p1=new Point(1,0,0);
        Point p2=new Point(0,1,0);
        Point p3=new Point(0,0,1);

        Plane pl=new Plane(p1,p2,p3);

        Vector v1=p1.subtract(p2);
        Vector v2=p2.subtract(p3);

        Vector expected=v1.crossProduct(v2);
        Vector result= pl.getNormal();

        //T1: check if length=1
        assertEquals(1, result.length(), "The getNormal on plane isn't working- not of length 1");
        //T2: check if the direction is the same
        assertEquals(Point.ZERO,result.crossProduct(expected),"The getNormal on plane isn't working- not in the same direction");
    }

//   @Test
//    void testGetNormal(Point p){
//        // ============ Equivalence Partitions Tests ==============
//        //T1: check if the normal is correct
//        Plane pl=new Plane(new Point(1,0,0),new Point(0,1,0),new Point(0,0,1));
//        assertEquals(new Vector(1,1,1).normalize(),pl.getNormal(),"The getNormal on plane isn't working- not in the same direction");
//    }
}
