package primitives;

import java.util.List;
import java.util.Objects;
import static primitives.Util.*;
import geometries.Intersectable.GeoPoint;

/** Represents a ray in 3D space */
public class Ray {
    /** The head (starting point) of the ray */
    private Point head;
    /** The direction vector of the ray */
    private Vector direction;

    /**
     * Constructs a new Ray instance with the given head and direction.
     * @param head      the head (starting point) of the ray
     * @param direction the direction vector of the ray */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    /**
     * Returns the head (starting point) of the ray.
     * @return the head of the ray */
    public Point getHead() {
        return head;
    }

    /**
     * Returns the direction vector of the ray.
     * @return the direction of the ray */
    public Vector getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return head.equals(ray.head) && direction.equals(ray.direction);
    }

    @Override
    public String toString() {
        return "Ray:\n" +
                "head=" + head +
                "\ndirection=" + direction;
    }

    /**
     * Returns the point on the ray at a given distance from the head.
     * @param t the distance from the head
     * @return the point on the ray at the given distance */
    public Point getPoint(double t) {
        if (isZero(t))
            return head;
        return head.add(direction.scale(t));
    }

    /**
     * Finds the closest point to the head of the ray from a list of points.
     * @param points the list of points
     * @return the closest point to the head of the ray */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Finds the closest GeoPoint to the head of the ray from a list of GeoPoints.
     * @param points the list of GeoPoints
     * @return the closest GeoPoint to the head of the ray */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
        if (points == null)
            return null;
        GeoPoint result = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (GeoPoint point : points) {
            double distance = head.distanceSquared(point.point);
            if (distance < minDistance) {
                minDistance = distance;
                result = point;
            }
        }
        return result;
    }
}
