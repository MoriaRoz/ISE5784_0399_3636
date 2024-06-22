package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Represents a collection of geometries in 3D space */
public class Geometries implements Intersectable{
    /** The geometries in the collection */
    private final List<Intersectable> geometries = new ArrayList<>();

    public Geometries() {
    }

    /** Constructs a new Geometries instance with the given geometries.
     * @param geometries the geometries to add to the collection */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /** Adds the given geometries to the collection.
     * @param geometries the geometries to add to the collection */
    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries) {
            this.geometries.add(geometry);
        }
    }

    /** Finds the intersections of a ray with the geometries in the collection.
     * @param ray the ray to find intersections with
     * @return a list of intersection points with the given ray */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = null;

        for (Intersectable geometry : geometries) {
            List<Point> tempIntersections = geometry.findIntersections(ray);

            if (tempIntersections != null) {
                if (intersections == null) {
                    intersections = new ArrayList<>();
                }
                intersections.addAll(tempIntersections);
            }
        }

        return intersections;
    }
}
