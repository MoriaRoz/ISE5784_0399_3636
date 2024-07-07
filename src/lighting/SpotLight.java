package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;

public class SpotLight extends PointLight {

    private Vector direction;

    /**
     * Constructor that initializes the intensity field
     *
     * @param intensity the color of the light
     * @param position  the position of the light
     * @param direction the direction of the light
     */
    protected SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public SpotLight setkC(double kC) {
        return (SpotLight) super.setkC(kC);
    }

    @Override
    public SpotLight setkL(double kL) {
        return (SpotLight) super.setkL(kL);
    }

    @Override
    public SpotLight setkQ(double kQ) {
        return (SpotLight) super.setkQ(kQ);
    }

    @Override
    public Color getIntensity(Point p) {
        return (super.getIntensity(p)).scale(Math.max(0,getL(p).dotProduct(direction)));
    }
}
