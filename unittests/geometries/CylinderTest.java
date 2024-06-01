package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Cylinder} class.
 */
class CylinderTest {

    /**
     * Test method for {@link Cylinder#getNormal(primitives.Point)}.
     * This method will test the getNormal method of the Cylinder class to ensure it returns the correct normal vector
     * at various points on the cylinder's surface.
     */
    @Test
    void testGetNormal() {
        // Test case 1: Test the getNormal method for a point on the cylinder's side surface.
        Cylinder cylinder = new Cylinder(1, 1, 1);
        Point point = new Point(1, 0, 1);
        assertEquals(new Point(1, 0, 0), cylinder.getNormal(point),
                "getNormal() did not return the correct normal vector for a point on the cylinder's side surface.");

        // Test case 2: Test the getNormal method for a point on the cylinder's top surface.
        point = new Point(1, 0, 2);
        assertEquals(new Point(0, 0, 1), cylinder.getNormal(point),
                "getNormal() did not return the correct normal vector for a point on the cylinder's top surface.");

        // Test case 3: Test the getNormal method for a point on the cylinder's bottom surface.
        point = new Point(1, 0, 0);
        assertEquals(new Point(0, 0, -1), cylinder.getNormal(point),
                "getNormal() did not return the correct normal vector for a point on the cylinder's bottom surface.");
    }
}
