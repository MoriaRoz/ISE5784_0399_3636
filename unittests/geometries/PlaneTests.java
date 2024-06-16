package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test class for {@link Plane}
 */
class PlaneTests {

    /**
     * Test method for {@link Plane#Plane(Point, Point, Point)}.
     * This method tests the constructor of the Plane class to ensure it constructs a plane
     * with three different points.
     */
    @Test
    void testConstructor() {
        // =============== Boundary Values Tests ==================

        //T1: check if the points are different
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(1, 0, 0)),
                "Failed constructing a correct Plane- 2 points are the same");

        //T2: check if the points are different
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 0, 0),
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

        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(0, 0, 1);

        Plane pl = new Plane(p1, p2, p3);

        Vector v1 = p1.subtract(p2);
        Vector v2 = p2.subtract(p3);

        Vector expected = v1.crossProduct(v2);
        Vector result = pl.getNormal();

        //T1: check if length=1
        assertEquals(1, result.length(),
                "The getNormal on plane isn't working- not of length 1");

        //T2: check if the direction is the same
        assertThrows(IllegalArgumentException.class, () -> result.crossProduct(expected),
                "The getNormal on plane isn't working- not in the same direction");
    }

    /**
     * Test method for {@link Plane#findIntersections(primitives.Ray)}.
     * This method tests the findIntersections method of the Plane class
     * to ensure it returns the correct intersection points with a given ray.
     */
    @Test
    void findIntersections() {
        final Point p000 = new Point(0, 0, 0);
        final Point p100 = new Point(1, 0, 0);
        final Point p010 = new Point(0, 1, 0);
        final Point p00_1 = new Point(0, 0, -1);
        final Vector v001 = new Vector(0, 0, 1);
        final Vector v111 = new Vector(1, 1, 1);
        final Point p110 = new Point(1, 1, 0);
        final Point p111 = new Point(1, 1, 1);
        final Vector v100 = new Vector(1, 0, 0);

        final Plane plane = new Plane(p100, p000, p010);

        // ============ Equivalence Partitions Tests ==============
        // **** Group: The ray is neither orthogonal nor parallel to the plane
        // T1: The ray intersects the plane (1 point)
        final var result1 = plane.findIntersections(new Ray(p00_1, v111));
        final var exp1 = List.of(p110);
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(exp1, result1, "The ray is neither orthogonal nor parallel, and intersects the plane");

        // T2: The ray does not intersect the plane (0 points)
        final var result2 = plane.findIntersections(new Ray(p111, v111));
        assertNull(result2, "The ray is neither orthogonal nor parallel, and does not intersect the plane");

        // =============== Boundary Values Tests ==================

        // **** Group: The ray is parallel to the plane
        // T3: The ray is included in the plane (0 points)
        final var result3 = plane.findIntersections(new Ray(p000, v100));
        assertNull(result3, "Ray is parallel to the plane and included in the plane");

        // T4: The ray is not included in the plane (0 points)
        final var result4 = plane.findIntersections(new Ray(p00_1, v100));
        assertNull(result4, "Ray is parallel to the plane and not included in the plane");

        // **** Group: The ray is orthogonal to the plane
        // T5: The ray intersects the plane (1 point)
        final var result5 = plane.findIntersections(new Ray(p00_1, v001));
        final var exp5 = List.of(p000);
        assertEquals(1, result5.size(), "Wrong number of points");
        assertEquals(exp5, result5, "Ray is orthogonal to the plane and intersects");

        // T6: The ray starts in the plane (0 points)
        final var result6 = plane.findIntersections(new Ray(p110, v001));
        assertNull(result6, "Ray is orthogonal to the plane and starts in the plane");

        // T7: The ray starts after the plane (0 points)
        final var result7 = plane.findIntersections(new Ray(p111, v001));
        assertNull(result7, "Ray is orthogonal to the plane and not intersects");

        // **** Group: The ray is neither orthogonal nor parallel to the plane
        // T8: The ray starts at the plane (0 points)
        final var result8 = plane.findIntersections(new Ray(p110, v111));
        assertNull(result8, "Ray is neither orthogonal nor parallel, and starts at the plane");

        // T9: the ray start from the point of the plane (0 points)
        final var result9 = plane.findIntersections(new Ray(p100, v111));
        assertNull(result9, "Ray is neither orthogonal nor parallel, and starts at point of the plane");

    }
}
