package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import java.util.List;
import static primitives.Util.*;

/** A class representing a sphere in 3D space */
public class Sphere extends RadialGeometry{
    /** The center point of the sphere */
     private  final Point center;

    /**
     * Constructs a new Sphere instance with the given radius and center point.
     * @param radius the radius of the sphere
     * @param center the center point of the sphere */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * @param p the point on the sphere's surface
     * @return the normal vector at the given point */
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }

    /**
     * Finds the intersections of a ray with the sphere.
     * @param ray The ray to find the intersections with.
     *            Must not be null.
     * @return A list of the intersection points.
     *         Null if there are no intersections */
    @Override
    public List<Point> findIntersections(Ray ray) {
            Point p0 = ray.getHead();
            Vector v = ray.getDirection();

            if (p0.equals(center))
                return List.of(ray.getPoint(radius));

            Vector u = center.subtract(p0);
            double tm = v.dotProduct(u);
            double dSquared = u.lengthSquared() - tm * tm;
            double rSquared = radius * radius;

            if (dSquared >= rSquared)
                return null;

            double th = Math.sqrt(rSquared - dSquared);
            double t1 = alignZero(tm - th);
            double t2 = alignZero(tm + th);

            if (t1 <= 0 && t2 <= 0)
                return null;

            if (t1 > 0 && t2 > 0)
                return List.of(ray.getPoint(t1), ray.getPoint(t2));
            else if (t1 > 0)
                return List.of(ray.getPoint(t1));
            else
                return List.of(ray.getPoint(t2));

    }

    /**
     * Finds the intersections of a ray with the sphere.
     * @param ray The ray to find the intersections with.
     *            Must not be null.
     * @return A list of the intersection points.
     *         Null if there are no intersections */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<Point> intersections = findIntersections(ray);
        GeoPoint g0, g1;
        if (intersections.get(0) != null) {
            g0 = new GeoPoint(this, intersections.get(0));

            if (intersections.get(1) != null) {
                g1 = new GeoPoint(this, intersections.get(1));
                return List.of(g0, g1);
            }
            else {
                return List.of(g0);
            }
        }
        return null;
    }
}
