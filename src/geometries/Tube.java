package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/** A class representing a tube in 3D space */
public class Tube extends RadialGeometry{
    /** The axis ray of the tube */
    protected final Ray axis;

    /**
     * Constructs a new Tube instance with the given radius and axis ray.
     * @param radius the radius of the tube
     * @param axis   the axis ray of the tube */
    public Tube(double radius, Ray axis) {
        super(radius);
        this.axis = axis;
    }

    /**
     * Returns the normal vector at a given point on the tube's surface.
     * @param p the point on the tube's surface
     * @return the normal vector at the given point */
    public Vector getNormal(Point p) {

        // The vector from the head of the axis ray to the given point
        Vector v = p.subtract(axis.getHead());

        // The projection of the vector v on the axis ray
        double t = axis.getDirection().dotProduct(v);

        // The point on the axis ray that is closest to the given point
        Point o = axis.getHead().add(axis.getDirection().scale(t));

        // The normal vector at the given point
        return p.subtract(o).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
