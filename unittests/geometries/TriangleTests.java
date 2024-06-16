package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleTests {

    @Test
    void testConstructor() {
        // =============== Boundary Values Tests ==================

        //T1: check if the points are different
        assertThrows(IllegalArgumentException.class, () -> new Triangle(new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(1, 0, 0)),
                "Failed constructing a correct Triangle- 2 points are the same");

        //T2: check if the points are not on the same line
        assertThrows(IllegalArgumentException.class, () -> new Triangle(new Point(1, 0, 0),
                        new Point(2, 0, 0),
                        new Point(3, 0, 0)),
                "Failed constructing a correct Triangle- 3 points are in the same line");

    }

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(1, 1, 0);

        Triangle t = new Triangle(p1, p2, p3);

        Vector v1 = p1.subtract(p2);
        Vector v2 = p2.subtract(p3);

        Vector expected = v1.crossProduct(v2);
        Vector result = t.getNormal(new Point(0.75,0.75,0));

        //T1: check if length=1
        assertEquals(1, result.length(),
                "The getNormal on triangle isn't working- not unit vector");

        //T2: check if the direction is the same
        assertThrows(IllegalArgumentException.class, () -> result.crossProduct(expected),
                "The getNormal on triangle isn't working- not in the same direction");

    }

    @Test
    void testFindIntersections() {
        final Point p_0$5_0$50=new Point(-0.5,-0.5,0);
        final Point p000 = new Point(0, 0, 0);
        final Point p200 = new Point(2, 0, 0);
        final Point p020 = new Point(0, 2, 0);
        final Point p11_1 = new Point(1, 1, -1);
        final Point p1_1_1 = new Point(1, -1, -1);
        final Point p_1_1_1 = new Point(-1, -1, -1);
        final Point p00_1 = new Point(0, 0, -1);
        final Point p3_1_1 = new Point(3, -1, -1);
        final Point p20_1 = new Point(2, 0, -1);
        final Vector v001 = new Vector(0, 0, 1);


        Triangle triangle = new Triangle(p_0$5_0$50, p020, p200);

        // ============ Equivalence Partitions Tests ==============
        //T1: Ray intersects inside the triangle(1 point)
        final var result1 = triangle.findIntersections(new Ray(p00_1, v001));
        final var exp1 = List.of(p000);
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(exp1, result1, "Ray intersects inside the triangle");

        //T2: Ray intersects outside the triangle opposite the side of the triangle(0 points)
        final var result2 = triangle.findIntersections(new Ray(p1_1_1, v001));
        assertNull(result2, "Ray intersects outside the triangle opposite the side of the triangle");

        //T3: Ray intersects outside the triangle opposite the vertex of the triangle(0 points)
        final var result3 = triangle.findIntersections(new Ray(p_1_1_1, v001));
        assertNull(result3, "Ray intersects outside the triangle opposite the vertex of the triangle");

        // =============== Boundary Values Tests ==================
        //T4: Ray intersects on the side of the triangle(0 points)
        final var result4 = triangle.findIntersections(new Ray(p11_1, v001));
        assertNull(result4,"Ray intersects on the side of the triangle");

        //T5: Ray intersects on the vertex of the triangle(0 points)
        final var result5 = triangle.findIntersections(new Ray(p20_1, v001));
        assertNull(result5, "Ray intersects on the vertex of the triangle");

        //T6: Ray intersects in continuation of the side of the triangle(0 points)
        final var result6 = triangle.findIntersections(new Ray(p3_1_1, v001));
        assertNull(result6, "Ray intersects in continuation of the side of the triangle");
    }
}

