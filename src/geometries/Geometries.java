package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable{

    private final List<Intersectable> geometries = new ArrayList<>();

    public Geometries() {
    }

    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries) {
            this.geometries.add(geometry);
        }
    }

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
