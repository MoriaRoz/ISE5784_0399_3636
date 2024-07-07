package geometries;

/**
 * An abstract class representing radial geometries (geometries with a radius)
 */
abstract public class RadialGeometry extends Geometry {
    /**
     * The radius of the radial geometry.
     */
    protected final double radius;

    /**
     * Constructs a new RadialGeometry instance with the given radius.
     *
     * @param radius the radius of the radial geometry
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
