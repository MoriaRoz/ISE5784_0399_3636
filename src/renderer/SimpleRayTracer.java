package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * A simple ray tracer that traces rays in a scene.
 * This class extends RayTracerBase and implements ray tracing algorithms
 * to render a scene with various lighting effects.
 */
public class SimpleRayTracer extends RayTracerBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;
    private static final double DELTA = 0.1;

    /**
     * Constructs a SimpleRayTracer with the given scene.
     *
     * @param scene The scene to be ray traced.
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Traces a ray through the scene and returns the resulting color.
     *
     * @param ray The ray to be traced.
     * @return The color resulting from tracing the ray.
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K);
    }

    /**
     * Calculates the color at a given intersection point.
     *
     * @param gp The geometric point of intersection.
     * @param ray The ray that intersected the point.
     * @return The calculated color at the intersection point.
     */
    private Color calcColor(GeoPoint gp, Ray ray){
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    /**
     * Recursively calculates the color at a given intersection point,
     * taking into account global lighting effects.
     *
     * @param intersection The geometric point of intersection.
     * @param ray The ray that intersected the point.
     * @param level The current recursion level.
     * @param k The attenuation factor.
     * @return The calculated color at the intersection point.
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        if (level == 0 || k.lowerThan(MIN_CALC_COLOR_K)) {
            return Color.BLACK;
        }

        Color color = calcLocalEffects(intersection, ray, k);

        if (level > 1) {
            color = color.add(calcGlobalEffects(intersection, ray, level, k));
        }

        return color;
    }

    /**
     * Calculates all global lighting effects (reflection and refraction) for a given point.
     *
     * @param gp The geometric point of intersection.
     * @param ray The incoming ray.
     * @param level The current recursion level.
     * @param k The attenuation factor.
     * @return The combined color from all global effects.
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material material = gp.geometry.getMaterial();
        Double3 kR = material.kR;
        Double3 kT = material.kT;

        if (!kR.equals(Double3.ZERO)) {
            Ray reflectedRay = constructReflectedRay(gp.point, gp.geometry.getNormal(gp.point), ray);
            color = color.add(calcGlobalEffect(gp, reflectedRay, kR, level));
        }

        if (!kT.equals(Double3.ZERO)) {
            Ray refractedRay = constructRefractedRay(gp.point, gp.geometry.getNormal(gp.point), ray);
            color = color.add(calcGlobalEffect(gp, refractedRay, kT, level));
        }

        return color;
    }

    /**
     * Calculates the global lighting effect for a single ray.
     *
     * @param gp The geometric point of intersection.
     * @param ray The ray to calculate the effect for.
     * @param kx The attenuation factor for this effect.
     * @param level The current recursion level.
     * @return The color contribution from this global effect.
     */
    private Color calcGlobalEffect(GeoPoint gp, Ray ray, Double3 kx, int level) {
        GeoPoint gp1 = findClosestIntersection(ray);
        if (gp1 == null) {
            return Color.BLACK;
        }
        return calcColor(gp1, ray, level - 1, kx).scale(kx);
    }

    /**
     * Constructs a refracted ray from a given point and incoming ray.
     *
     * @param point The point of intersection.
     * @param normal The surface normal at the intersection point.
     * @param inRay The incoming ray.
     * @return The refracted ray.
     */
    private Ray constructRefractedRay(Point point, Vector normal, Ray inRay) {
        return new Ray(point, inRay.getDirection(), normal);
    }

    /**
     * Constructs a reflected ray from a given point and incoming ray.
     *
     * @param point The point of intersection.
     * @param normal The surface normal at the intersection point.
     * @param inRay The incoming ray.
     * @return The reflected ray.
     */
    private Ray constructReflectedRay(Point point, Vector normal, Ray inRay) {
        Vector v = inRay.getDirection();
        Vector r = v.subtract(normal.scale(2 * v.dotProduct(normal)));
        return new Ray(point, r, normal);
    }

    /**
     * Calculates the local lighting effects at a given point.
     *
     * @param gp The geometric point of intersection.
     * @param ray The incoming ray.
     * @param k The attenuation factor.
     * @return The color resulting from local lighting effects.
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material mat = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (!(ktr.product(k).lowerThan(MIN_CALC_COLOR_K))) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(iL.scale(calcDiffusive(mat, nl)),
                            iL.scale(calcSpecular(mat, n, l, nl, v)));
                }
            }
        }
        return color;
    }

    /**
     * Calculates the diffuse lighting component.
     *
     * @param material The material properties of the surface.
     * @param cosAngle The cosine of the angle between the normal and light direction.
     * @return The diffuse lighting component.
     */
    private Double3 calcDiffusive(Material material, double cosAngle) {
        return material.kD.scale(Math.abs(cosAngle));
    }

    /**
     * Calculates the specular lighting component.
     *
     * @param material The material properties of the surface.
     * @param normal The surface normal at the intersection point.
     * @param lightDir The direction of the light source.
     * @param cosAngle The cosine of the angle between the normal and light direction.
     * @param rayDir The direction of the viewing ray.
     * @return The specular lighting component.
     */
    private Double3 calcSpecular(Material material, Vector normal, Vector lightDir, double cosAngle, Vector rayDir) {
        Vector r = lightDir.subtract(normal.scale(2 * cosAngle));
        double coefficient = -rayDir.dotProduct(r);
        return material.kS.scale(Math.pow(Math.max(coefficient, 0), material.nShininess));
    }

    /**
     * Determines if a point is unshaded (visible) from a light source.
     *
     * @param gp The geometric point to check.
     * @param lightSource The light source.
     * @param l The direction to the light source.
     * @param n The surface normal at the point.
     * @return true if the point is unshaded, false otherwise.
     */
    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return true;
        double lightDistance = lightSource.getDistance(gp.point);
        for (GeoPoint intersection : intersections) {
            if (intersection.point.distance(gp.point) <= lightDistance) {
                if (intersection.geometry.getMaterial().kT.equals(Double3.ZERO)) {
                    return false; // Opaque object blocks the light
                }

            }
        }
        return true;
    }

    private Double3 transparency(GeoPoint gp, LightSource lightSource, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return Double3.ONE;
        double lightDistance = lightSource.getDistance(gp.point);
        Double3 ktr = Double3.ONE;
        for (GeoPoint intersection : intersections) {
            if (intersection.point.distance(gp.point) <= lightDistance) {
                ktr = ktr.product(intersection.geometry.getMaterial().kT);
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }
        return ktr;
    }

    /**
     * Finds the closest intersection point for a given ray.
     *
     * @param ray The ray to find intersections for.
     * @return The closest geometric intersection point, or null if none exists.
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        var intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? null : ray.findClosestGeoPoint(intersections);
    }
}