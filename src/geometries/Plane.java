package geometries;

import primitives.Point;
import primitives.Vector;

/**
 Represents a plane in 3D space.
 */
public class Plane {
    /** A point on the plane */
    private final Point q;
    /** The normal vector of the plane */
    private final Vector normal;

    /**
     Constructs a plane defined by the point q and the vector defined by the points p1 and p2.
     @param q  the point on the plane
     @param p1 the first point of the vector
     @param p2 the second point of the vector
     */
    public Plane(Point q, Point p1, Point p2) {
        this.q = q;
        this.normal = null;
    }

    /**
     Constructs a plane passing through the given point and perpendicular to the given normal vector.
     @param q      the point on the plane
     @param normal the normal vector of the plane
     */
    public Plane(Point q, Vector normal) {
        this.q = q;
        this.normal = normal.normalize();
    }

    /**
     Returns the normal vector at a given point on the plane.
     @param p the point on the plane
     @return the normal vector at the given point
     */
    public Vector getNormal(Point p) {
        return normal;
    }

    /**
     Returns the normal vector of the plane.
     @return the normal vector of the plane
     */
    public Vector getNormal() {
        return normal;
    }
}

