package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import java.util.List;

/** A class representing a sphere in 3D space */
public class Sphere extends RadialGeometry{
    /** The center point of the sphere */
     private  final Point center;

    /**
     * Constructs a new Sphere instance with the given radius and center point.
     * @param radius the radius of the sphere
     * @param center the center point of the sphere
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * Returns the normal vector at a given point on the sphere's surface.
     * @param p the point on the sphere's surface
     * @return the normal vector at the given point
     */
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }

    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
