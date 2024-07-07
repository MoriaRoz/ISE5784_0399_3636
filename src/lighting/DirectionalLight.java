package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{

    private Vector direction;
    /**
     * Constructor that initializes the intensity field
     * @param intensity the color of the light
     * @param direction the direction of the light
     */
    protected DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }
}
