package primitives;

import java.util.Objects;
import static primitives.Util.*;

/**
 * Represents a ray in 3D space.
 */
public class Ray {
    /** The head (starting point) of the ray */
    private Point head;
    /** The direction vector of the ray */
    private Vector direction;

    /**
     * Constructs a new Ray instance with the given head and direction.
     * @param head      the head (starting point) of the ray
     * @param direction the direction vector of the ray
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    /**
     * Returns the head (starting point) of the ray.
     * @return the head of the ray
     */
    public Point getHead() {
        return head;
    }

    /**
     * Returns the direction vector of the ray.
     * @return the direction of the ray
     */
    public Vector getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return Objects.equals(head, ray.head) && Objects.equals(direction, ray.direction);
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
     * @return the point on the ray at the given distance
     */
    public Point getPoint(double t) {
        if (isZero(t))
            return head;
        return head.add(direction.scale(t));
    }
}
