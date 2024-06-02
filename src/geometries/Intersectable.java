package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;

/**
 * Represents an intersectable geometry in 3D space.
 */
public interface Intersectable {
    /**
     * Finds the intersections of the given ray with this geometry.
     * @param ray the ray to find intersections with
     * @return a list of intersection points
     */
    List<Point> findIntersections(Ray ray);
}
