package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import java.util.List;

/** A simple ray tracer that traces rays in a scene */
public class SimpleRayTracer extends RayTracerBase{

    /** Constructs a new SimpleRayTracer instance with the given scene.
     * @param scene the scene to trace rays in */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /** Traces a ray in the scene and returns the color of the closest intersection point.
     * @param ray the ray to trace
     * @return the color of the closest intersection point */
    @Override
    public Color traceRay(Ray ray) {
        List< Point> intersections=this.scene.geometries.findIntersections(ray);
        if (intersections==null)
            return scene.background;
        return calcColor(ray.findClosestPoint(intersections));

    }

    /** Calculates the color of a point in the scene.
     * @param point the point to calculate the color of
     * @return the color of the point */
    private Color calcColor(Point point){
        return scene.ambientLight.getIntensity();
    }
}
