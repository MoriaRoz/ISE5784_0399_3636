package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {

    private Point position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * Constructor that initializes the intensity field
     *
     * @param intensity the color of the light
     * @param position  the position of the light
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        return super.getIntensity().scale(1d / (kC + kL * d + kQ * d * d));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return point.distance(position);
    }

    /**
     * Setter for the kC field
     *
     * @param kC the new value for the kC field
     * @return the PointLight object
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Setter for the kL field
     *
     * @param kL the new value for the kL field
     * @return the PointLight object
     */
    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Setter for the kQ field
     *
     * @param kQ the new value for the kQ field
     * @return the PointLight object
     */
    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }
}
