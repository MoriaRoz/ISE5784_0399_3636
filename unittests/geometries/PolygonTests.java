package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.Ray;
import primitives.Point;
import primitives.Vector;
import java.util.List;

/**
 * Testing Polygons
 * @author Dan
 */
public class PolygonTests {
    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private final double DELTA = 0.000001;

    /** Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}. */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        assertDoesNotThrow(() -> new Polygon(new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(-1, 1, 1)),
                "Failed constructing a correct polygon");

        // TC02: Wrong vertices order
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                "Constructed a polygon with wrong order of vertices");

        // TC03: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                "Constructed a polygon with vertices that are not in the same plane");

        // TC04: Concave quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0.5, 0.25, 0.5)), //
                "Constructed a concave polygon");

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0, 0.5, 0.5)),
                "Constructed a polygon with vertix on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Constructed a polygon with vertice on a side");

        // TC12: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a polygon with vertice on a side");

    }

    /** Test method for {@link geometries.Polygon#getNormal(primitives.Point)}. */
    @Test
    public void testGetNormal(){
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Point[] pts =
                { new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1) };
        Polygon pol = new Polygon(pts);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)), "");
        // generate the test result
        Vector result = pol.getNormal(new Point(0, 0, 1));
        // ensure |result| = 1
        assertEquals(1, result.length(), DELTA, "Polygon's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 3; ++i)
            assertEquals(0d, result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1])), DELTA,
                    "Polygon's normal is not orthogonal to one of the edges");
    }

    /** Test method for {@link geometries.Polygon#findIntersections(primitives.Ray)}. */
    @Test
    public void testFindIntersections() {
        final Point p110= new Point(1, 1, 0);
        final Point p_1_1_0 = new Point(-1, -1, 0);
        final Point p_110 = new Point(-1, 1, 0);
        final Point p1_10 = new Point(1, -1, 0);
        final Point p000 = new Point(0, 0, 0);
        final Point p200 = new Point(2, 0, 0);
        final Point p220 = new Point(2, 2, 0);
        final Point p100 = new Point(1, 0, 0);
        final Point p210 = new Point(2, 1, 0);
        final Vector v001 = new Vector(0, 0, 1);

        Polygon polygon = new Polygon(p110, p_110, p_1_1_0, p1_10);

        // ============ Equivalence Partitions Tests ==============
        //T1: Ray intersects inside the polygon(1 point)
        final var result1= polygon.findIntersections(new Ray(new Point(0,0,-1), v001));
        final var exp1 = List.of(p000);
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(exp1, result1, "Wrong intersection with polygon");

        //T2: Ray intersects outside the polygon opposite the side of the polygon(0 points)
        final var result2= polygon.findIntersections(new Ray(new Point(2,0,-1), v001));
        assertNull(result2, "Ray intersects outside the polygon opposite the side of the polygon");

        //T3: Ray intersects outside the polygon opposite the vertex of the polygon(0 points)
        final var result3= polygon.findIntersections(new Ray(new Point(2,2,-1), v001));
        assertNull(result3, "Ray intersects outside the polygon opposite the vertex of the polygon");

        // =============== Boundary Values Tests ==================
        //T4: Ray intersects on the side of the polygon(0 points)
        final var result4= polygon.findIntersections(new Ray(new Point(1,0,-1), v001));
        assertNull(result4, "Ray intersects on the side of the polygon");

        //T5: Ray intersects on the vertex of the polygon(0 points)
        final var result5= polygon.findIntersections(new Ray(new Point(1,1,-1), v001));
        assertNull(result5, "Ray intersects on the vertex of the polygon");

        //T6: Ray intersects in continuation of the side of the polygon(0 points)
        final var result6= polygon.findIntersections(new Ray(new Point(2,1,-1), v001));
        assertNull(result6, "Ray intersects in continuation of the side of the polygon");
    }
}