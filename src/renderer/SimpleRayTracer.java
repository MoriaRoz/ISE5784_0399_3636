package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.*;

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
    private static final Double INITIAL_K=1.0;

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
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return scene.background;
        return calcColor(ray.findClosestGeoPoint(intersections), ray);

    }

    /**
     * Calculates the color at the given intersection point with the provided ray.
     * @param gp  the intersection point
     * @param ray the ray
     * @return the calculated color
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, new Double3(INITIAL_K)).add(scene.ambientLight.getIntensity());
    }

    /**
     * Calculates the color of a given point by combining its emission color and local effects.
     *
     * @param geoPoint the intersection point to calculate the color for
     * @param ray      the ray that intersected with the point
     * @return the color of the point after taking emission and local effects into account
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        var color = calcLocalEffects(geoPoint, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level,k));
}

    /**
     * Calculate the reflection and refracted color on given point and ray
     * @param gp the point
     * @param ray the ray
     * @param level recursion level
     * @param k amount of color of that colors to calculate
     * @return the color
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray,
                                    int level, Double3 k) {
        Color color = Color.BLACK;
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        Double3 kr = material.kR, kkr = k.product(kr);
        Ray reflectedRay = constructReflectedRay(gp, v, n);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            color = color.add(calcGlobalEffect(reflectedRay, level - 1, kr, kkr)).scale(kr);
        }
        Double3 kt = material.kT, kkt = k .product(kt);
        Ray refractedRay = constructRefractedRay(gp, v, n);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            color = color.add(calcGlobalEffect(refractedRay, level - 1, kt, kkt)).scale(kt);
        }
        return color;    }

    /**
     * Calculate the local effects on given point and ray
     * @param gp
     * @param v
     * @param n
     * @return
     */
    private Ray constructRefractedRay(GeoPoint gp, Vector v, Vector n) {
        return new Ray(gp.point, v, n);
    }

    /**
     * Calculate the reflection or refracted color on given point and ray
     * @param ray the ray
     * @param level recursion level
     * @param k amount of color of that colors to calculate
     * @param kx the kr/kt
     * @return the color
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp = findClosestIntersection(ray);
        if (gp == null) return scene.background.scale(kx);
        return isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getPoint())) ? Color.BLACK : calcColor(gp, ray, level -1,kkx);
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
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
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
     * checks if the point is shaded
     *
     * @param gp the point to check
     * @param l  the direction of the light
     * @param n  the normal to the object
     * @return true if the point is shaded, false otherwise
     */
    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(gp.point));
        return intersections==null;
    }

    /**
     * construct the reflected ray of given ray and point
     * @param gp the point
     * @param v the ray
     * @param n the normal
     * @return the reflected ray
     */
    public static Ray constructReflectedRay(GeoPoint gp, Vector v, Vector n){
        return new Ray(gp.point, v.subtract(n.scale(2 * alignZero(n.dotProduct(v)))),n);
    }

    /**
     * Find the closest intersection point in given ray
     * @param ray the ray
     * @return the closest point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        var intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? null : ray.findClosestGeoPoint(intersections);
}
}
