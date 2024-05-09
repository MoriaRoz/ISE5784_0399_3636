package primitives;

import java.util.Objects;

/**
 * Represents a point in 3D space.
 */
public class Point {
    /** x,y,z is the coordinates of the point */
    protected final Double3 xyz;
    /** A constant representing the zero point (0, 0, 0) */
    public static final Point ZERO = new Point(Double3.ZERO);

    /**
     * Constructs a new Point instance with the given x, y, and z coordinates.
     * @param d1 the x-coordinate
     * @param d2 the y-coordinate
     * @param d3 the z-coordinate
     */
    public Point(double d1, double d2, double d3) {
        this.xyz = new Double3(d1,d2,d3);
    }

    /**
     * Constructs a new Point instance with the given Double3 coordinates.
     * @param d the Double3 coordinates
     */
    public Point(Double3 d){
        this.xyz=d;
    }

    /**
     * Calculates the vector from this point to the given point.
     * @param p the other point
     * @return the vector from this point to the given point
     */
    public Vector subtract(Point p){
        return new Vector(this.xyz.subtract(p.xyz));
    }

    /**
     * Calculates the point resulting from adding the given vector to this point.
     * @param v the vector to add
     * @return the new point resulting from the addition
     */
    public Point add(Vector v){
        return new Point(this.xyz.add(v.xyz));
    }

    /**
     * Calculates the squared distance between this point and the given point.
     * @param p the other point
     * @return the squared distance between the two points
     */
    public double distanceSquared(Point p){
        return ((this.xyz.d1-p.xyz.d1)*(this.xyz.d1-p.xyz.d1)+
                (this.xyz.d2-p.xyz.d2)*(this.xyz.d2-p.xyz.d2)+
                (this.xyz.d3-p.xyz.d3)*(this.xyz.d3-p.xyz.d3));
    }

    /**
     * Calculates the distance between this point and the given point.
     * @param p the other point
     * @return the distance between the two points
     */
    public double distance(Point p){
        return Math.sqrt(this.distanceSquared(p));
    }

    @Override
    public String toString() {
        return this.xyz.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point p)) return false;
        return Objects.equals(xyz, p.xyz) ;
    }
}
