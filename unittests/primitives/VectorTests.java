package primitives;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Unit tests class for {@link Vector} */
class VectorTests {

    private final double DELTA = 0.000001;
    Point Zero=new Point(0,0,0);
    Vector v1;
    Vector v2;

    /** Test method for {@link Vector#Vector} */
    @Test
    void testConstructor(){
        // =============== Boundary Values Tests ==================

        //T1: Zero vector by 3 double
        assertThrows(IllegalArgumentException.class,()->new Vector(0,0,0),
                "Creating the zero vector does not throw an exception");

        //T2: Zero vector by Double3
        assertThrows(IllegalArgumentException.class,()->new Vector(new Double3(0,0,0)),
                "Creating the zero vector does not throw an exception");
    }

    /**
     * Test method for {@link Vector#add(Vector)}
     * This test checks the correctness of the vector addition operation.
     * It includes both equivalence partition tests and boundary value tests.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============

        //T1: Two vectors
        v1 = new Vector(1, 2, 3);
        v2 = new Vector(4, 5, 6);

        Vector result = v1.add(v2);
        Vector expected = new Vector(v1.xyz.d1 + v2.xyz.d1,
                                     v1.xyz.d2 + v2.xyz.d2,
                                     v1.xyz.d3 + v2.xyz.d3);

        assertEquals(expected, result,
                "The add between two vectors isn't working");

        //T2: Two opposite vectors (not same length)
        v2=new Vector(-10,-20,-30);

        result = v1.add(v2);
        expected = new Vector(v1.xyz.d1 + v2.xyz.d1,
                              v1.xyz.d2 + v2.xyz.d2,
                              v1.xyz.d3 + v2.xyz.d3);

        assertEquals(expected, result,
                "The add between two opposite vectors isn't working");

        // =============== Boundary Values Tests ==================

        //T3: Two opposite vectors (same length)-expected Zero vector
        v2 = new Vector(-1, -2, -3);

        assertThrows(IllegalArgumentException.class, () -> v1.add(v2),
                    "Adding zero vector does not throw an exception");
    }

    /**
     * Test method for {@link Vector#scale(double)}
     * This test checks the correctness of the vector scaling operation by a scalar.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============

        v1 = new Vector(1, 2, 3);

        //T1: Positive scalar
        double d = 2.3;

        Vector result = v1.scale(d);
        Vector expected = new Vector(v1.xyz.d1 * d, v1.xyz.d2 * d, v1.xyz.d3 * d);

        assertEquals(expected, result,
                "The scale between vector and positive double isn't working");

        //T2: Opposite scalar
        d=-3.0;

        result = v1.scale(d);
        expected = new Vector(v1.xyz.d1 * d, v1.xyz.d2 * d, v1.xyz.d3 * d);

        assertEquals(expected, result,
                "The scale between vector and opposite double isn't working");

        // =============== Boundary Values Tests ==================

        //T3: Zero scalar
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0.0),
                "Scale zero vector does not throw an exception");
    }

    /**
     * Test method for {@link Vector#dotProduct(Vector)}
     * This test checks the correctness of the dot product operation.
     * <p>
     * The dot product of two vectors v1 and v2 is defined as:
     * v1.x * v2.x + v1.y * v2.y + v1.z * v2.z
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============

        // T1: Two vectors
        v1 = new Vector(1, 2, 3);
        v2 = new Vector(4, 5, 6);

        double result = v1.dotProduct(v2);
        double expected = (v1.xyz.d1 * v2.xyz.d1 + v1.xyz.d2 * v2.xyz.d2 + v1.xyz.d3 * v2.xyz.d3);

        assertEquals(expected, result,
                "The dot product between two vectors isn't working");

        // =============== Boundary Values Tests ==================

        //T2:Two vertical vectors
        v1 = new Vector(1, 0, 0);
        v2 = new Vector(0, 1, 0);

        result = v1.dotProduct(v2);
        expected = (v1.xyz.d1 * v2.xyz.d1 + v1.xyz.d2 * v2.xyz.d2 + v1.xyz.d3 * v2.xyz.d3);

        assertEquals(expected, result,
                "The dot product between two vertical vectors isn't working");
    }

    /**
     * Test method for {@link Vector#crossProduct(Vector)}
     * This test checks the correctness of the cross product operation.
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        v1 = new Vector(1, 2, 3);
        v2 = new Vector(4, 5, 6);

        Vector result = v1.crossProduct(v2);
        Vector expected = new Vector((v1.xyz.d2 * v2.xyz.d3 - v1.xyz.d3 * v2.xyz.d2),
                (v1.xyz.d3 * v2.xyz.d1 - v1.xyz.d1 * v2.xyz.d3),
                (v1.xyz.d1 * v2.xyz.d2 - v1.xyz.d2 * v2.xyz.d1));

        //T1: Two vectors
        assertEquals(expected, result,
                "The cross product between two vectors isn't working");

        //T2:  cross-product result orthogonality to its operands
        assertEquals(0,result.dotProduct(v1),DELTA,
                "The cross product result is not orthogonal to v1");
        assertEquals(0,result.dotProduct(v2),DELTA,
                "The cross product result is not orthogonal to v2");

        // =============== Boundary Values Tests ==================

        //T3: Two parallel vectors
        v2 = new Vector(2, 4, 6);

        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2),
                "Cross product with parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link Vector#lengthSquared()}
     * This test checks the correctness of the squared length calculation of a vector.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============

        //T1: Random vector
        v1 = new Vector(1, 2, 3);

        Double result = v1.lengthSquared();
        Double expected = (v1.xyz.d1 * v1.xyz.d1 + v1.xyz.d2 * v1.xyz.d2 + v1.xyz.d3 * v1.xyz.d3);

        assertEquals(expected, result,
                "The length squared of the vector is incorrect");
    }

    /**
     * Test method for {@link Vector#length()}
     * This test checks the correctness of the length calculation of a vector.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============

        //T1: Random vector
        v1 = new Vector(1, 2, 3);

        Double result = v1.length();
        Double expected = Math.sqrt(v1.xyz.d1 * v1.xyz.d1 + v1.xyz.d2 * v1.xyz.d2 + v1.xyz.d3 * v1.xyz.d3);

        assertEquals(expected, result,
                "The length of the vector is incorrect");
    }

    /**
     * Test method for {@link Vector#normalize()}
     * This test checks the correctness of the vector normalization operation.
     */
    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============

        //T1: Vector of length!=1
        v1 =new Vector(1,2,3);

        Vector result= v1.normalize();
        double vLength=Math.sqrt(v1.xyz.d1* v1.xyz.d1+
                                 v1.xyz.d2* v1.xyz.d2+
                                 v1.xyz.d3* v1.xyz.d3);

        Vector expected=new Vector(v1.xyz.d1/vLength,
                                   v1.xyz.d2/vLength,
                                   v1.xyz.d3/vLength);

        assertEquals(expected, result,
                "The normalize on vector of of length!=1 is incorrect");

        // =============== Boundary Values Tests ==================

        //T2: Vector of length=1
        v1 =new Vector(1,0,0);

        result= v1.normalize();

        assertEquals(v1, result,
                "The normalize on the vector of length=1 is incorrect");
    }
}