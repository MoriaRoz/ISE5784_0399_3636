package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
import java.util.List;

/**
 * A class representing a triangle in 3D space.
 */
public class Triangle extends Polygon{
    /**
     * Constructor for a triangle.
     * @param p1 The first point of the triangle.
     *           Must be different from p2 and p3.
     *           Must not be on the same line as p2 and p3.
     *           Must not be null.
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    /**
     * Returns the normal vector of the triangle.
     * @return The normal vector of the triangle.
     */
    public Vector getNormal(Point p) {
        return super.getNormal(p);
    }

    public List<Point> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }
}
