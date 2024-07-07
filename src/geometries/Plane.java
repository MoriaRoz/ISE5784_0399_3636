package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Represents a plane in 3D space
 */
public class Plane extends Geometry {
    /**
     * A point on the plane
     */
    private final Point q;
    /**
     * The normal vector of the plane
     */
    private final Vector normal;

    /**
     * Constructs a plane defined by the point q and the vector defined by the points p1 and p2.
     *
     * @param q  the point on the plane
     * @param p1 the first point of the vector
     * @param p2 the second point of the vector
     */
    public Plane(Point q, Point p1, Point p2) {
        if (p1.equals(p2) || p1.equals(q) || p2.equals(q))
            throw new IllegalArgumentException("Two points are the same");

        double disP1P2 = p1.distance(p2);
        double disP1Q = p1.distance(q);
        double disP2Q = p2.distance(q);
        boolean sameLine = (disP1P2 + disP1Q == disP2Q) || (disP1P2 + disP2Q == disP1Q) || (disP1Q + disP2Q == disP1P2);
        if (sameLine)
            throw new IllegalArgumentException("The points are on the same line");

        this.q = q;

        Vector v1 = p1.subtract(q);
        Vector v2 = p2.subtract(q);
        this.normal = v1.crossProduct(v2).normalize();
    }

    /**
     * Constructs a plane defined by the point q and the normal vector.
     *
     * @param q      the point on the plane
     * @param normal the normal vector of the plane
     */
    public Plane(Point q, Vector normal) {
        this.q = q;
        this.normal = normal.normalize();
    }

    /**
     * Returns the normal vector of the plane at a given point.
     *
     * @param p the point on the plane
     * @return the normal vector of the plane at the given point
     */
    public Vector getNormal(Point p) {
        return normal;
    }

    /**
     * Returns the normal vector of the plane.
     *
     * @return the normal vector of the plane
     */
    public Vector getNormal() {
        return normal;
    }


    /**
     * Finds the intersections of a ray with the plane.
     *
     * @param ray The ray to find the intersections with.
     *            Must not be null.
     * @return A list of the intersection points.
     * Null if there are no intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double distance) {
        Vector n = getNormal();
        Point q0 = ray.getHead();
        Vector v = ray.getDirection();

        double nv = n.dotProduct(v);
        if (isZero(nv))//ray is parallel to the plane
            return null;
        double nq0 = 0;
        try {
            nq0 = n.dotProduct(q0.subtract(this.q));
        } catch (IllegalArgumentException e) {
            return null;
        }
        double t = alignZero(-nq0 / nv);
        if (t <= 0 || alignZero(t - distance) >= 0) //ray starts after the plane
            return null;

        Point p = q0.add(v.scale(t));
        return List.of(new GeoPoint(this, p));
    }

}

