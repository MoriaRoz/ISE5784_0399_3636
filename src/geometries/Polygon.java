package geometries;

import java.util.Comparator;
import java.util.List;
import static primitives.Util.*;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * @author Dan Zilberstein
 */
public class Polygon extends Geometry {
   /** List of polygon's vertices */
   protected final List<Point> vertices;
   /** Associated plane in which the polygon lays */
   protected final Plane       plane;
   /** The size of the polygon - the amount of the vertices in the polygon */
   private final int           size;

   /**
    * Polygon constructor based on vertices list. The list must be ordered by edge
    * path. The polygon must be convex.
    * @param  vertices                 list of vertices according to their order by
    *                                  edge path
    * @throws IllegalArgumentException in any case of illegal combination of
    *                                  vertices:
    *                                  <ul>
    *                                  <li>Less than 3 vertices</li>
    *                                  <li>Consequent vertices are in the same
    *                                  point
    *                                  <li>The vertices are not in the same
    *                                  plane</li>
    *                                  <li>The order of vertices is not according
    *                                  to edge path</li>
    *                                  <li>Three consequent vertices lay in the
    *                                  same line (180&#176; angle between two
    *                                  consequent edges)
    *                                  <li>The polygon is concave (not convex)</li>
    *                                  </ul>
    */
   public Polygon(Point... vertices) {
      if (vertices.length < 3)
         throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
      this.vertices = List.of(vertices);
      size          = vertices.length;

      // Generate the plane according to the first three vertices and associate the
      // polygon with this plane.
      // The plane holds the invariant normal (orthogonal unit) vector to the polygon
      plane         = new Plane(vertices[0], vertices[1], vertices[2]);
      if (size == 3) return; // no need for more tests for a Triangle

      Vector  n        = plane.getNormal();
      // Subtracting any subsequent points will throw an IllegalArgumentException
      // because of Zero Vector if they are in the same point
      Vector  edge1    = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
      Vector  edge2    = vertices[0].subtract(vertices[vertices.length - 1]);

      // Cross Product of any subsequent edges will throw an IllegalArgumentException
      // because of Zero Vector if they connect three vertices that lay in the same
      // line.
      // Generate the direction of the polygon according to the angle between last and
      // first edge being less than 180 deg. It is hold by the sign of its dot product
      // with the normal. If all the rest consequent edges will generate the same sign
      // - the polygon is convex ("kamur" in Hebrew).
      boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
      for (var i = 1; i < vertices.length; ++i) {
         // Test that the point is in the same plane as calculated originally
         if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
            throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
         // Test the consequent edges have
         edge1 = edge2;
         edge2 = vertices[i].subtract(vertices[i - 1]);
         if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
            throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
      }
   }

    /** @return the vertices */
   @Override
   public Vector getNormal(Point point) { return plane.getNormal(); }

   /**
    * Find intersections of the ray with the polygon
    * @param incomingRay the ray
    * @return List of intersections points, or null if there are no intersections */
   @Override
   public List<Point> findIntersections(Ray incomingRay) {
      Point rayOrigin = incomingRay.getHead();      // Starting point of the ray
      Vector rayDirection = incomingRay.getDirection(); // Direction of the ray

      // Determine where the ray intersects the polygon's plane
      List<Point> planeHitPoints = plane.findIntersections(incomingRay);
      if (planeHitPoints == null) {
         return null; // No intersection with the plane
      }

      // We expect only one intersection point with the plane
      Point potentialIntersection = planeHitPoints.getFirst();

      // Examine if the intersection occurs on the polygon's boundary
      Point currentVertex, nextVertex;
      Vector edgeVector, vectorToIntersection, vectorFromIntersection;
      Vector edgeNormal;
      for (int vertexIndex = 0; vertexIndex < size; vertexIndex++) {
         currentVertex = vertices.get(vertexIndex);
         nextVertex = vertices.get((vertexIndex + 1) % size);
         edgeVector = nextVertex.subtract(currentVertex);

         // Intersection at a vertex means no valid intersection
         if (currentVertex.equals(potentialIntersection) || nextVertex.equals(potentialIntersection)) {
            return null;
         }

         vectorToIntersection = potentialIntersection.subtract(currentVertex);
         vectorFromIntersection = nextVertex.subtract(potentialIntersection);
         edgeNormal = edgeVector.crossProduct(plane.getNormal());

         // Check if intersection lies on an edge
         if (isZero(vectorToIntersection.dotProduct(edgeNormal)) &&
                 alignZero(edgeVector.dotProduct(vectorToIntersection)) >= 0 &&
                 alignZero(edgeVector.dotProduct(vectorFromIntersection)) >= 0) {
            return null;
         }
      }

      // Determine if the intersection point is within the polygon
      boolean consistentOrientation = true;
      double orientationTest1 = 0, orientationTest2 = 0;

      for (int vertexIndex = 0; vertexIndex < size; vertexIndex++) {
         currentVertex = vertices.get(vertexIndex);
         nextVertex = vertices.get((vertexIndex + 1) % size);
         edgeVector = nextVertex.subtract(currentVertex);
         vectorToIntersection = potentialIntersection.subtract(currentVertex);
         vectorFromIntersection = potentialIntersection.subtract(nextVertex);
         edgeNormal = edgeVector.crossProduct(plane.getNormal());

         // Ensure consistent edge normal orientation
         edgeNormal = edgeNormal.normalize();

         orientationTest1 = alignZero(vectorToIntersection.dotProduct(edgeNormal));
         orientationTest2 = alignZero(vectorFromIntersection.dotProduct(edgeNormal));

         if (orientationTest1 * orientationTest2 < 0) {
            consistentOrientation = false;
            break;
         }
      }

      if (!consistentOrientation) {
         return null;
      }

      // Final check: Confirm the intersection is within polygon boundaries
      double boundaryTest;
      for (int vertexIndex = 0; vertexIndex < size; vertexIndex++) {
         currentVertex = vertices.get(vertexIndex);
         nextVertex = vertices.get((vertexIndex + 1) % size);
         edgeVector = nextVertex.subtract(currentVertex);
         vectorToIntersection = potentialIntersection.subtract(currentVertex);
         boundaryTest = alignZero(edgeVector.dotProduct(vectorToIntersection));

         if (boundaryTest < 0) {
            return null; // Outside polygon boundary
         }
      }
      return List.of(potentialIntersection);
   }

   @Override
   protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
      return List.of(new GeoPoint(this, findIntersections(ray).get(0)));
   }
}
