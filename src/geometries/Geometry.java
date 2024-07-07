package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * An interface representing a geometric object in 3D space
 */
public abstract class Geometry extends Intersectable {

    /**
     * The color of the geometry
     */
    protected Color emission = Color.BLACK;

    /**
     * The material of the geometry
     */
    private Material material = new Material();

    /**
     * getter for the emission field
     *
     * @return the emission field
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * setter for the emission field
     *
     * @param emission the new emission field
     * @return the geometry object
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * getter for the material field
     *
     * @return the material field
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * setter for the material field
     *
     * @param material the new material field
     * @return the geometry object
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Returns the normal vector at a given point on the geometry.
     *
     * @param p is point on the geometry
     * @return normal of the geometry on this point
     */
    public abstract Vector getNormal(Point p);
}
