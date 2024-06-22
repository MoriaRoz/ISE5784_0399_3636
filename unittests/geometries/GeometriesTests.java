package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/** Unit tests class for {@link Geometries} */
public class GeometriesTests {

    /** Test method for {@link Geometries#findIntersections(Ray)} */
    @Test
    void testFindIntersections() {
        final Vector v001 = new Vector(0, 0, 1);
        final Vector v00_1 = new Vector(0, 0, -1);
        final Vector v111 = new Vector(1, 1, 1);

        Geometries geometries = new Geometries();
        geometries.add(
                new Triangle(new Point(-0.5,-0.5,0),new Point(0,2,0),new Point(2,0,0)),
                new Polygon(new Point(-1,-1,-3),new Point(-1,1,-3),new Point(1,1,-3),new Point(1,-1,-3)),
                new Sphere(1,new Point(1,0,3)));

        // ============ Equivalence Partitions Tests ==============

        //T1: Some of the geometries have intersections but not all of them
        final Ray r1=new Ray(new Point(1,0,-1),v001);
        assertEquals(3, geometries.findIntersections(r1).size(),
                "Some of the geometries have intersections but not all of them");

        // =============== Boundary Values Tests ==================

        //T2: None of the geometries have intersections
        final Ray r2=new Ray(new Point(0,0,1),v111);
        assertNull(geometries.findIntersections(r2),
                "None of the geometries have intersections");

        //T3: All the geometries have intersections
        final Ray r3=new Ray(new Point(0.5,0.5,-5),v001);
        assertEquals(4, geometries.findIntersections(r3).size(),
                "All of the geometries have intersections");


        //T4: Only one of the geometries have intersections
        final Ray r4=new Ray(new Point(0,0,-1),v00_1);
        assertEquals(1, geometries.findIntersections(r4).size(),
                "Only one of the geometries have intersections");

        //T5: geometries list is empty
        Geometries emptyGeometries = new Geometries();
        assertNull(emptyGeometries.findIntersections(r1),
                "geometries list is empty");



    }
}
