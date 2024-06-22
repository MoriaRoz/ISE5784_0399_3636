package primitives;

/** Represents a vector in 3D space */
public class Vector extends Point {

    /**
     * Constructs a new Vector instance with the given x, y, and z components.
     * @param d1 the x-component
     * @param d2 the y-component
     * @param d3 the z-component
     * @throws IllegalArgumentException if the given components represent the zero vector */
    public Vector(double d1, double d2, double d3) {
        this(new Double3(d1, d2, d3));
    }

    /**
     * Constructs a new Vector instance with the given Double3 components.
     * @param d the Double3 components
     * @throws IllegalArgumentException if the given components represent the zero vector */
    public Vector(Double3 d) {
        super(d);
        if (d.equals(Double3.ZERO))
            throw new IllegalArgumentException("This is the zero vector");
    }

    /**
     * Calculates the vector sum of this vector and the given vector.
     * @param v the vector to add
     * @return the vector sum */
    public Vector add(Vector v) {
        Double3 val = xyz.add(v.xyz);
        if (val.equals(Double3.ZERO))
            throw new IllegalArgumentException("The sum of the two vectors is the zero vector");
        return new Vector(val);
    }

    /**
     * Scales this vector by the given scalar value.
     * @param d the scalar value
     * @return the scaled vector */
    public Vector scale(double d) {
        if (d == 0)
            throw new IllegalArgumentException("This is the zero vector");
        return new Vector(this.xyz.scale(d));
    }

    /**
     * Calculates the dot product of this vector and the given vector.
     * @param v the other vector
     * @return the dot product */
    public double dotProduct(Vector v) {
        return (this.xyz.d1 * v.xyz.d1 + this.xyz.d2 * v.xyz.d2 + this.xyz.d3 * v.xyz.d3);
    }

    /**
     * Calculates the cross product of this vector and the given vector.
     * @param v the other vector
     * @return the cross product */
    public Vector crossProduct(Vector v) {
        double x = this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2;
        double y = this.xyz.d3 * v.xyz.d1 - this.xyz.d1 * v.xyz.d3;
        double z = this.xyz.d1 * v.xyz.d2 - this.xyz.d2 * v.xyz.d1;

        if (x==0.0&&y==0.0&&z==0.0)
            throw new IllegalArgumentException("Cross product is zero vector");

        if (this.equals(v) || v.equals(this))
            throw new IllegalArgumentException("Cross product operand cannot be the result vector");

        return new Vector(x, y, z);
    }

    /**
     * Calculates the squared length of this vector.
     * @return the squared length */
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    /**
     * Calculates the length of this vector.
     * @return the length */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * Normalizes this vector to a unit vector.
     * @return the normalized vector */
    public Vector normalize() {
        double l = this.length();
        Vector v = this.scale(1 / l);//Multiplication of a vector by 1 divided by its length
        return v;
    }

    /**
     * Checks if this vector is equal to the given object.
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
