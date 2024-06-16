package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.List;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests class for {@link Sphere}
 */
class SphereTests {
    private final double DELTA = 0.000001;

    /**
     * Test method for {@link Sphere#getNormal(primitives.Point)}.
     * This method tests the getNormal method of the Sphere class
     * to ensure it returns the correct normal vector at a given point on the sphere's surface.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        Point center = new Point(0, 0, 0);
        Sphere s = new Sphere(5, center);
        Point p = new Point(5, 0, 0);

        Vector result = s.getNormal(p);
        Vector expected = (p.subtract(center));

        //T1: check if length=1
        assertEquals(1, result.length(),
                "The getNormal on sphere isn't working- not of length 1");

        //T2: check if the direction is the same
        assertThrows(IllegalArgumentException.class, () -> result.crossProduct(expected),
                "The getNormal on sphere isn't working- not in the direction of the point");
    }

    private final Point p001 = new Point(0, 0, 1);
    private final Point p100 = new Point(1, 0, 0);
    private final Vector v001 = new Vector(0, 0, 1);

    /**
     * Test method for {@link Sphere#findIntersections(Ray)}.
     * This method tests the findIntersections method of the Sphere class
     * to ensure it returns the correct intersection points of a given ray with the sphere.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(1d, p100);

        final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
        final var exp = List.of(gp1, gp2);

        final Vector v310 = new Vector(3, 1, 0);
        final Vector v110 = new Vector(1, 1, 0);
        final Vector v_100 = new Vector(-1, 0, 0);
        final Point p0$500 = new Point(0.5, 0, 0);
        final Point p000 = new Point(0, 0, 0);
        Point p101 = new Point(1, 0, 1);
        Vector v101 = new Vector(1, 0, 1);
        Point p_100 = new Point(-1, 0, 0);
        Vector v100 = new Vector(1, 0, 0);
        Point p200 = new Point(2, 0, 0);
        final Vector v00_1 = new Vector(0, 0, -1);


        // ============ Equivalence Partitions Tests ==============

        // T1: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p_100, v110)), "Ray's line out of sphere");

        // T2: Ray starts before and crosses the sphere (2 points)
        final var result2 = sphere.findIntersections(new Ray(p_100, v310))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p_100))).toList();
        assertEquals(2, result2.size(), "Wrong number of points");
        assertEquals(exp, result2, "Ray crosses sphere");

        // T3: Ray starts inside the sphere (1 point)
        final var result3 = sphere.findIntersections(new Ray(p0$500, v_100));
        final var exp3 = List.of(p000);
        assertEquals(1, result3.size(), "Wrong number of points");
        assertEquals(exp3, result3, "Ray starts inside the sphere");


        // T4: Ray starts after the sphere (0 points)
        final var result4 = sphere.findIntersections(new Ray(p001, v001));
        assertNull(sphere.findIntersections(new Ray(gp2, v310)), "Ray starts after the sphere");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // T5: Ray starts at sphere and goes inside (1 point)

        final var result5 = sphere.findIntersections(new Ray(p000, v101));
        final var exp5 = List.of(p101);
        assertEquals(1, result5.size(), "Wrong number of points");
        assertEquals(exp5, result5, "Ray starts at sphere and goes inside");

        // T6: Ray starts at sphere and goes outside (0 points)
        final var result6 = sphere.findIntersections(new Ray(p000, new Vector(-1, -1, -1)));
        assertNull(result6, "Ray starts at sphere and goes outside");

        // **** Group: Ray's line goes through the center
        // T7: Ray starts before the sphere (2 points)
        final var result7 = sphere.findIntersections(new Ray(p_100, v100))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p_100))).toList();
        final var exp7 = List.of(p000, p200);

        assertEquals(2, result7.size(), "Wrong number of points");
        assertEquals(exp7, result7, "Ray starts before the sphere");

        // T8: Ray starts at sphere and goes inside (1 point)
        final var result8 = sphere.findIntersections(new Ray(p000, v100));
        final var exp8 = List.of(p200);
        assertEquals(1, result8.size(), "Wrong number of points");
        assertEquals(exp8, result8, "Ray starts at sphere and goes inside");

        // T9: Ray starts inside (1 point)
        final var result9 = sphere.findIntersections(new Ray(p0$500, v100));
        assertEquals(1, result9.size(), "Wrong number of points");
        assertEquals(exp8, result9, "Ray starts inside");

        // T10: Ray starts at the center (1 point)
        final var result10 = sphere.findIntersections(new Ray(p000, v100));
        assertEquals(1, result10.size(), "Wrong number of points");
        assertEquals(exp8, result10, "Ray starts at the center");

        // T11: Ray starts at sphere and goes outside (0 points)
        final var result11 = sphere.findIntersections(new Ray(p000, v_100));
        assertNull(result11, "Ray starts at sphere and goes outside");

        // T12: Ray starts after sphere (0 points)
        final var result12 = sphere.findIntersections(new Ray(new Point(3, 0, 0), v100));
        assertNull(result12, "Ray starts after sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // T13: Ray starts before the tangent point
        final var result13 = sphere.findIntersections(new Ray(p001, v00_1));
        assertNull(result13, "Ray starts before the tangent point");

        // T14: Ray starts at the tangent point
        final var result14 = sphere.findIntersections(new Ray(p000, v001));
        assertNull(result14, "Ray starts at the tangent point");

        // T15: Ray starts after the tangent point
        final var result15 = sphere.findIntersections(new Ray(p001, v001));
        assertNull(result15, "Ray starts after the tangent point");

        // **** Group: Special cases
        // T16: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        final var result16 = sphere.findIntersections(new Ray(p_100, v001));
        assertNull(result16, "Ray's line is outside, ray is orthogonal to ray start to sphere's center line");

        // T17: Ray's line is inside, ray is orthogonal to ray start to sphere's center line
        Point gp17 = new Point(0.5, 0, 0.8660254037844386);
        final var result17 = sphere.findIntersections(new Ray(p0$500, v001));
        final var exp17 = List.of(gp17);
        assertEquals(1, result17.size(), "Wrong number of points");
        assertEquals(exp17, result17, "Ray starts at the center");
    }
}
