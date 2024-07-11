package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * A simple ray tracer that traces rays in a scene
 */
public class SimpleRayTracer extends RayTracerBase {

    /**
     * The maximum level of recursive color calculation.
     * This constant determines the maximum number of recursive calls
     * to calculate the color of a point in the scene.
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * The minimum coefficient threshold for color calculation.
     * This constant determines the minimum value of the coefficient
     * below which the color calculation is considered negligible
     * and will not be further processed recursively.
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * The starting K for the recursion
     */
    private static final Double3 INITIAL_K = Double3.ONE;

    /**
     * A small value used for moving the head of the ray slightly
     */
    private static final double DELTA = 0.1;

    /**
     * Constructs a new SimpleRayTracer instance with the given scene.
     *
     * @param scene the scene to trace rays in
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Traces a ray in the scene and returns the color of the closest intersection point.
     *
     * @param ray the ray to trace
     * @return the color of the closest intersection point
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> geoPoints = scene.geometries.findGeoIntersections(ray);
        return (geoPoints == null) ? scene.background : calcColor(ray.findClosestGeoPoint(geoPoints), ray);
    }

    /**
     * returns the color of a given point
     *

     * @return the color of the point
     */
    private Color calcColor(GeoPoint intersection, Ray ray) {
        return scene.ambientLight.getIntensity().add(calcLocalEffects(intersection, ray));
    }

    /**
     * calculate the effects of the geometry itself without the effect of other
     * geometries on this object
     *
     * @param gp  the point to calculate the effects on
     * @param ray the ray we intersected with
     * @return the result color of the local effects
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = Util.alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = Util.alignZero(n.dotProduct(l));
            if ((nl * nv > 0) && unshaded(gp, lightSource, l, n, nl)) {
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)), iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;
    }

    /**
     * calculates the diffusive light part of the object
     *
     * @param material the material of the object
     * @param cosAngle the cosine of the angle between the light and the normal to
     *                 the object
     * @return the diffusive light color
     */
    private Double3 calcDiffusive(Material material, double cosAngle) {
        return material.kD.scale(cosAngle > 0 ? cosAngle : -cosAngle);
    }

    /**
     * calculates the specular light part of the object
     *
     * @param material the material of the object
     * @param normal   the normal to the object
     * @param lightDir the direction of the light
     * @param cosAngle the cosine of the angle between the light and the normal to
     *                 the object
     * @param rayDir   the direction the camera is pointed to
     * @return
     */
    private Double3 calcSpecular(Material material, Vector normal, Vector lightDir, double cosAngle, Vector rayDir) {
        Vector r = lightDir.subtract(normal.scale(2 * cosAngle));
        double coefficient = -rayDir.dotProduct(r);
        coefficient = coefficient > 0 ? coefficient : 0;
        return material.kS.scale(Math.pow(coefficient, material.nShininess));
    }

    /**
     * checks if a point on a geometry is shaded by a light source
     *
     * @param gp       the point on the geometry
     * @param l        the direction vector of the light source
     * @param n        the normal to the geometry
     * @param cosAngle the cosine of the angle between l and n
     * @return True if the object is not shaded, False otherwise
     */
    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double cosAngle) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(cosAngle < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay,
                lightSource.getDistance(gp.point));
        return intersections == null || intersections.isEmpty();
    }


    /**
     * Finds the closest intersection point of a given ray
     * @param ray the ray
     * @return the closest point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        var intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? null : ray.findClosestGeoPoint(intersections);
    }

}