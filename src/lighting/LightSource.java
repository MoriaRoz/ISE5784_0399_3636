package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * LightSource is an interface representing a light source in the scene
 */
public interface LightSource {
    /**
     * Returns the intensity of the light at the given point
     *
     * @param p the point at which to calculate the intensity
     * @return the intensity of the light at the given point
     */
    public Color getIntensity(Point p);

    /**
     * Returns the direction of the light at the given point
     *
     * @param p the point at which to calculate the direction
     * @return the direction of the light at the given point
     */
    public Vector getL(Point p);

    /**
     * Returns the distance between the light source and the given point
     *
     * @param point the point to calculate the distance from
     * @return the distance between the light source and the given point
     */
    public double getDistance(Point point);
}
