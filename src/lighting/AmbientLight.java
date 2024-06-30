package lighting;

import primitives.Color;
import primitives.Double3;

/** Represents an ambient light source */
public class AmbientLight {
    /** The intensity of the ambient light source */
    final private Color intensity;
    /** A constant ambient light source with no intensity */
    final public static AmbientLight NONE = new AmbientLight(Color.BLACK, 0);

    /**
     * Constructs a new AmbientLight instance with the given intensity.
     * @param iA the intensity of the ambient light source */
    public AmbientLight(Color iA, Double3 kA) {
        this.intensity = iA.scale(kA);
    }

    /**
     * Constructs a new AmbientLight instance with the given intensity.
     * @param intensity the intensity of the ambient light source */
    public AmbientLight(Color intensity, double kA) {
        this.intensity = intensity.scale(kA);
    }

    /**
     * Returns the intensity of the ambient light source.
     * @return the intensity of the ambient light source */
    public Color getIntensity() {
        return intensity;
    }
}
