package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * A class representing a sphere in 3D space
 */
public class Sphere extends RadialGeometry {
    /**
     * The center point of the sphere
     */
    private final Point center;

    /**
     * Constructs a new Sphere instance with the given radius and center point.
     *
     * @param radius the radius of the sphere
     * @param center the center point of the sphere
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * @param p the point on the sphere's surface
     * @return the normal vector at the given point
     */
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }


    /**
     * Finds the intersections of a ray with the sphere.
     *
     * @param ray The ray to find the intersections with.
     *            Must not be null.
     * @return A list of the intersection points.
     * Null if there are no intersections
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double distance) {
        Point p0 = ray.getHead();
        if (center.equals(p0))
            return List.of(new GeoPoint(this, ray.getPoint(radius)));
        Vector u = center.subtract(p0);
        double tm = u.dotProduct(ray.getDirection());
        double d = Math.sqrt(u.lengthSquared() - tm * tm);
        double dif = alignZero(d - radius);
        if (dif >= 0)
            return null;
        double th = Math.sqrt(radius * radius - d * d);
        double t2 = alignZero(tm + th);
        if (t2 <= 0 || alignZero(t2 - distance) >= 0)
            return null;
        double t1 = alignZero(tm - th);
        return (t1 > 0  && alignZero(distance - t1) >= 0)//
                ? List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2))) //
                : List.of(new GeoPoint(this, ray.getPoint(t2)));
    }
}
