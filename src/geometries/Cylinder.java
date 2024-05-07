package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 Represents a cylinder in 3D space.
 */
public class Cylinder extends Tube {
    /** The height of the cylinder */
    private final double height;

    /**
     Constructs a cylinder with the given radius, axis, and height.
     @param radius the radius of the cylinder
     @param axis the axis of the cylinder
     @param height the height of the cylinder
     */
    public Cylinder(double radius, Ray axis, double height) {
        super(radius, axis);
        this.height = height;
    }

    /**
     Returns the normal vector at a given point on the cylinder.
     @param p the point on the cylinder
     @return the normal vector at the given point
     */
    public Vector getNormal(Point p) {
        return null;
    }
}
