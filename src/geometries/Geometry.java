package geometries;

import primitives.Point;
import primitives.Vector;

/**
 *An interface representing a geometric object in 3D space
 */
public interface Geometry {

    /**
     *Returns the normal vector at a given point on the geometry.
     * @param p is point on the geometry
     * @return normal of the geometry on this point
     */
    public Vector getNormal (Point p);
}
