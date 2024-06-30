package renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** A class of tests that checks intersection points between camera rays and geometric bodies */
public class IntegrationTest {

    /** Asserts the number of intersection points between a camera and a geometric body
     * @param camera The camera
     * @param geo The geometric body
     * @param expected The expected number of intersection points */
    private void assertIntersectionPoints(Camera camera, Intersectable geo, int expected) {
        int nX = 3;
        int nY = 3;
        int count = 0;
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                Ray ray = camera.constructRay(nX, nY, j, i);
                var result = geo.findIntersections(ray);
                if (result != null)
                    count += result.size();
            }
        }
        assertEquals(expected, count, "Wrong number of intersection points");
    }

    /** Camera for the tests */
    Camera camera = new Camera.Builder()
            .setLocation(new Point(0, 0, 0))
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVpDistance(1)
            .setVpSize(3, 3)
            .setImageWriter(new ImageWriter("test", 3, 3))
            .setRayTracer(new SimpleRayTracer(new Scene("test")))
            .build();

    /** Tests the intersection points between the camera and a sphere */
    @Test
    public void sphereIntegrationTest() {

        //T1: Sphere with 2 intersection points
        Sphere sphere1 = new Sphere(1, new Point(0, 0, -3));
        assertIntersectionPoints(camera, sphere1, 2);

        //T2: Sphere with 18 intersection points
        Sphere sphere2 = new Sphere(9, new Point(0, -1, -10));
        assertIntersectionPoints(camera, sphere2, 18);

        //T3: Sphere with 10 intersection points
        Sphere sphere3 = new Sphere(4, new Point(0, 0, -5));
        assertIntersectionPoints(camera, sphere3, 10);

        //T4: Sphere with 9 intersection points
        Sphere sphere4 = new Sphere(4, new Point(0, 0, -3));
        assertIntersectionPoints(camera, sphere4, 9);

        //T5: Sphere with 0 intersection points
        Sphere sphere5 = new Sphere(0.5, new Point(0, 0, 2));
        assertIntersectionPoints(camera, sphere5, 0);
    }

    /** Tests the intersection points between the camera and a plane */
    @Test
    public void planeIntegrationTest() {
        //T1: Plane with 9 intersection points
        Plane plane1 = new Plane(new Point(0, 0, -3), new Vector(0, 0, 1));
        assertIntersectionPoints(camera, plane1, 9);

        //T2: Plane with 9 intersection points
        Plane plane2 = new Plane(new Point(0,0,-5),new Vector( 0,0,-1));
        assertIntersectionPoints(camera, plane2, 9);

        //T3: Plane with 6 intersection points
        Plane plane3 = new Plane(new Point(0,0, -2),new Vector(1, -1,1));
        assertIntersectionPoints(camera, plane3, 6);

    }

    /** Tests the intersection points between the camera and a triangle */
    @Test
    public void triangleIntegrationTest() {
        //T1: Triangle with 2 intersection point
        Triangle triangle1 = new Triangle(
                new Point(0,20, -2),
                new Point(1, -1, -2),
                new Point(-1, -1, -2));
        assertIntersectionPoints(camera, triangle1, 2);

        //T2: Triangle with 1 intersection points
        Triangle triangle2 = new Triangle(
                new Point(0, 0.5, -2),
                new Point(1, -1, -2),
                new Point(-1, -1, -2));
        assertIntersectionPoints(camera, triangle2, 1);

    }
}
