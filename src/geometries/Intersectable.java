package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;

/** Represents an intersectable geometry in 3D space.
 * This interface is implemented by all geometries that can be intersected by a ray */


public interface Intersectable {
    /** Finds the intersections of the given ray with this geometry.
     * @param ray the ray to find intersections with
     * @return a list of intersection points with the given ray */
    List<Point> findIntersections(Ray ray);
}
