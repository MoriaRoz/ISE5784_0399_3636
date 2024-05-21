package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the Vector class that contains unit tests for various vector operations.
 */
class VectorTest {

    /**
     * Test for the add method in the Vector class.
     * This test checks the correctness of the vector addition operation.
     * It includes both equivalence partition tests and boundary value tests.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4, 5, 6);

        Vector result = v1.add(v2);

        Vector expected = new Vector(v1.xyz.d1 + v2.xyz.d1, v1.xyz.d2 + v2.xyz.d2, v1.xyz.d3 + v2.xyz.d3);

        assertEquals(expected, result, "The add between 2 vectors isn't working");

        // =============== Boundary Values Tests ==================
        Vector v3 = new Vector(1, 2, 3);
        Vector v4 = new Vector(-1, -2, -3);

        result = v3.add(v4);

        assertEquals(Point.ZERO, result, "The add between 2 opposite vectors isn't working");
    }

    /**
     * Test for the scale method in the Vector class.
     * This test checks the correctness of the vector scaling operation by a scalar.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        Double d = 2.3;

        Vector result = v1.scale(d);

        Vector expected = new Vector(v1.xyz.d1 * d, v1.xyz.d2 * d, v1.xyz.d3 * d);

        assertEquals(expected, result, "The scale between vector and double isn't working");
    }

    /**
     * Test for the dotProduct method in the Vector class.
     * This test checks the correctness of the dot product operation.
     * <p>
     * The dot product of two vectors v1 and v2 is defined as:
     * v1.x * v2.x + v1.y * v2.y + v1.z * v2.z
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // Create two vectors
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4, 5, 6);

        double result = v1.dotProduct(v2);

        double expected = (v1.xyz.d1 * v2.xyz.d1 + v1.xyz.d2 * v2.xyz.d2 + v1.xyz.d3 * v2.xyz.d3);

        // Use assertEquals to check if the result matches the expected value
        assertEquals(expected, result, "The dot product between 2 vectors isn't working");
        // Calculate the dot product

        // =============== Boundary Values Tests ==================
        v1 = new Vector(1, 0, 0);
        v2 = new Vector(0, 1, 0);
        result = v1.dotProduct(v2);

        // The expected result is 0 for orthogonal vectors
        expected = (v1.xyz.d1 * v2.xyz.d1 + v1.xyz.d2 * v2.xyz.d2 + v1.xyz.d3 * v2.xyz.d3);

        // Use assertEquals to check if the result matches the expected value
        assertEquals(expected, result, "The dot product between 2 vertical vectors isn't working");
    }

    /**
     * Test for the crossProduct method in the Vector class.
     * This test checks the correctness of the cross product operation.
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4, 5, 6);

        Vector result = v1.crossProduct(v2);

        Vector expected = new Vector((v1.xyz.d2 * v2.xyz.d3 - v1.xyz.d3 * v2.xyz.d2),
                (v1.xyz.d3 * v2.xyz.d1 - v1.xyz.d1 * v2.xyz.d3),
                (v1.xyz.d1 * v2.xyz.d2 - v1.xyz.d2 * v2.xyz.d1));

        assertEquals(expected, result, "The cross product between 2 vectors isn't working");

        assertEquals(0, result.crossProduct(v1), "The cross product result is not orthogonal to v1");
        assertEquals(0, result.crossProduct(v2), "The cross product result is not orthogonal to v2");

        // =============== Boundary Values Tests ==================
        v2 = new Vector(2, 4, 6);
        result = v1.crossProduct(v2);
        expected = new Vector((v1.xyz.d2 * v2.xyz.d3 - v1.xyz.d3 * v2.xyz.d2),
                (v1.xyz.d3 * v2.xyz.d1 - v1.xyz.d1 * v2.xyz.d3),
                (v1.xyz.d1 * v2.xyz.d2 - v1.xyz.d2 * v2.xyz.d1));
        assertEquals(expected, result, "The cross product between 2 parallel vectors isn't working");
    }

    /**
     * Test for the lengthSquared method in the Vector class.
     * This test checks the correctness of the squared length calculation of a vector.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1, 2, 3);

        Double result = v.lengthSquared();

        Double expected = (v.xyz.d1 * v.xyz.d1 + v.xyz.d2 * v.xyz.d2 + v.xyz.d3 * v.xyz.d3);

        assertEquals(expected, result, "The length squared of the vector is incorrect");
    }

    /**
     * Test for the length method in the Vector class.
     * This test checks the correctness of the length calculation of a vector.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1, 2, 3);

        Double result = v.length();

        Double expected = Math.sqrt(v.xyz.d1 * v.xyz.d1 + v.xyz.d2 * v.xyz.d2 + v.xyz.d3 * v.xyz.d3);

        assertEquals(expected, result, "The length of the vector is incorrect");
    }

    /**
     * Test for the normalize method in the Vector class.
     * This test checks the correctness of the vector normalization operation.
     */
    @Test
    void testNormalize() {
        // Implementation needed
    }
}
