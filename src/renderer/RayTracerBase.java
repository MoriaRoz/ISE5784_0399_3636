package renderer;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

/** Abstract class for ray tracing algorithms */
abstract public class RayTracerBase {
    protected Scene scene;

    /** Constructor for RayTracerBase
     * @param scene the scene to be rendered */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /** Traces a ray and returns the color of the closest intersection point
     * @param ray the ray to trace
     * @return the color of the closest intersection point */
    abstract public Color traceRay(Ray ray);
}
