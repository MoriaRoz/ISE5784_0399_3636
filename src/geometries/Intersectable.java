package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Represents an intersectable geometry in 3D space.
 * This interface is implemented by all geometries that can be intersected by a ray
 */


public abstract class Intersectable {

    /**
     * Finds the intersections of the given ray with this geometry.
     *
     * @param ray the ray to find intersections with
     * @return a list of intersection points with the given ray
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray, Double.POSITIVE_INFINITY);
    }

    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

    /**
     * Represents a point in 3D space
     */
    public static class GeoPoint {
        /**
         * The geometry that the point is on
         */
        public Geometry geometry;
        /**
         * The point in 3D space
         */
        public Point point;

        /**
         * Constructor for GeoPoint
         *
         * @param geometry the geometry that the point is on
         * @param point    the point in 3D space
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * Checks if this GeoPoint is equal to another object
         *
         * @param o the object to compare to
         * @return true if the objects are equal, false otherwise
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        /**
         * Returns the string representation of this GeoPoint
         *
         * @return the string representation of this GeoPoint
         */
        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
}
