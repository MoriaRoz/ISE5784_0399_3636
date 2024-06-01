package primitives;

import java.util.Objects;

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
}
