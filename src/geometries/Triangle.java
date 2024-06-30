package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
import java.util.List;

/**  A class representing a triangle in 3D space  */
public class Triangle extends Polygon{
    /**
     * Constructor for a triangle.
     * @param p1 The first point of the triangle.
     *           Must be different from p2 and p3.
     *           Must not be on the same line as p2 and p3.
     *           Must not be null */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    /**  @return The normal vector of the triangle */
    public Vector getNormal(Point p) {
        return super.getNormal(p);
    }

    /**
     * Finds the intersections of a ray with the triangle.
     * @param ray The ray to find the intersections with.
     *            Must not be null.
     * @return A list of the intersection points.
     *         Null if there are no intersections */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = plane.findIntersections(ray);
        if (intersections == null)
            return null;

        Point rayP0 = ray.getHead();
        Vector rayVec = ray.getDirection();
        Vector v1 = vertices.get(0).subtract(rayP0);
        Vector v2 = vertices.get(1).subtract(rayP0);
        double t1 = alignZero(rayVec.dotProduct(v1.crossProduct(v2)));
        if (t1 == 0)
            return null;

        Vector v3 = vertices.get(2).subtract(rayP0);
        double t2 = alignZero(rayVec.dotProduct(v2.crossProduct(v3)));
        if (t1 * t2 <= 0)
            return null;

        double t3 = alignZero(rayVec.dotProduct(v3.crossProduct(v1)));
        return t1 * t3 <= 0 ? null : intersections;
    }
}
