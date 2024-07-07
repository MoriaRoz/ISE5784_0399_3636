package lighting;

import primitives.Color;

/**
 * Light is an abstract class representing a light source in the scene
 * The class has a single field, intensity, which represents the color of the light
 */
abstract class Light {
    /**
     * The color of the light
     */
    protected Color intensity;

    /**
     * Constructor that initializes the intensity field
     * @param intensity the color of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Getter for the intensity field
     * @return the color of the light
     */
    public Color getIntensity() {
        return intensity;
    }
}
