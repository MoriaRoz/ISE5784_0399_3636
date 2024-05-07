package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * A class representing a tube in 3D space.
 */
public class Tube extends RadialGeometry{
    /** The axis ray of the tube */
    protected final Ray axis;

    /**
     * Constructs a new Tube instance with the given radius and axis ray.
     * @param radius the radius of the tube
     * @param axis   the axis ray of the tube
     */
    public Tube(double radius, Ray axis) {
        super(radius);
        this.axis = axis;
    }

    /**
     * Returns the normal vector at a given point on the tube's surface.
     * @param p the point on the tube's surface
     * @return the normal vector at the given point
     */
    public Vector getNormal(Point p) {
        return null;
    }
}
