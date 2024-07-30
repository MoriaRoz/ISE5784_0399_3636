import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.SimpleRayTracer;
import scene.Scene;
import static java.awt.Color.*;
import static java.awt.Color.WHITE;

public class Minip1 {

    @Test
    public void Minip1() {
        Scene scene = new Scene("Minip1");

        //region background
        //Right mirror plane
        Plane rightMirror = new Plane(new Point(-40, 0, 0), new Vector(1,0,0));
        rightMirror.setEmission(new Color(GRAY)).setMaterial(new Material().setkR(0.9).setkT(0.0));
        scene.geometries.add(rightMirror);

        //Left mirror plane
        Plane leftMirror = new Plane(new Point(0, -40, 0), new Vector(0,1,0));
        leftMirror.setEmission(new Color(GRAY)).setMaterial(new Material().setkR(0.9).setkT(0.0));
        scene.geometries.add(leftMirror);

        //Floor plane
        Plane floor = new Plane(new Point(0, 0,-20), new Vector(0,0,1));
        floor.setEmission(new Color(GRAY))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30));
        scene.geometries.add(floor,rightMirror,leftMirror);
        //endregion

        //region red cube face
        // Add black background rectangle
        Polygon backGround = new Polygon(new Point(10.9, -11, 11), new Point(10.9, -11, -11), new Point(10.9, 11, -11), new Point(10.9, 11, 11));
        backGround.setEmission(new Color(0, 0, 0));
        scene.geometries.add(backGround);

        // Create the red cubes
        Polygon p1 = new Polygon(new Point(11, -10, 10), new Point(11, -10, 4), new Point(11, -4, 4), new Point(11, -4, 10));
        p1.setEmission(new Color(255, 0, 0));
        Polygon p2 = new Polygon(new Point(11, -3, 10), new Point(11, -3, 4), new Point(11, 3, 4), new Point(11, 3, 10));
        p2.setEmission(new Color(255, 0, 0));
        Polygon p3 = new Polygon(new Point(11, 4, 10), new Point(11, 4, 4), new Point(11, 10, 4), new Point(11, 10, 10));
        p3.setEmission(new Color(255, 0, 0));
        Polygon p4 = new Polygon(new Point(11, -10, 3), new Point(11, -10, -3), new Point(11, -4, -3), new Point(11, -4, 3));
        p4.setEmission(new Color(255, 0, 0));
        Polygon p5 = new Polygon(new Point(11, -3, 3), new Point(11, -3, -3), new Point(11, 3, -3), new Point(11, 3, 3));
        p5.setEmission(new Color(255, 0, 0));
        Polygon p6 = new Polygon(new Point(11, 4, 3), new Point(11, 4, -3), new Point(11, 10, -3), new Point(11, 10, 3));
        p6.setEmission(new Color(255, 0, 0));
        Polygon p7 = new Polygon(new Point(11, -10, -4), new Point(11, -10, -10), new Point(11, -4, -10), new Point(11, -4, -4));
        p7.setEmission(new Color(255, 0, 0));
        Polygon p8 = new Polygon(new Point(11, -3, -4), new Point(11, -3, -10), new Point(11, 3, -10), new Point(11, 3, -4));
        p8.setEmission(new Color(255, 0, 0));
        Polygon p9 = new Polygon(new Point(11, 4, -4), new Point(11, 4, -10), new Point(11, 10, -10), new Point(11, 10, -4));
        p9.setEmission(new Color(255, 0, 0));

        scene.geometries.add(p1, p2, p3, p4, p5, p6, p7, p8, p9);
        //endregion

        //region orange cube face
        // Add black background rectangle
        Polygon backGround1 = new Polygon(new Point(-10.9, 11, 11), new Point(-10.9, -11, 11), new Point(-10.9, -11, -11), new Point(-10.9, 11, -11));
        backGround1.setEmission(new Color(0, 0, 0));
        scene.geometries.add(backGround1);

        // Create the orange cubes
        Polygon p11 = new Polygon(new Point(-11, -10, 10), new Point(-11, -10, 4), new Point(-11, -4, 4), new Point(-11, -4, 10));
        p11.setEmission(new Color(255, 50, 0));
        Polygon p22 = new Polygon(new Point(-11, -3, 10), new Point(-11, -3, 4), new Point(-11, 3, 4), new Point(-11, 3, 10));
        p22.setEmission(new Color(255, 50, 0));
        Polygon p33 = new Polygon(new Point(-11, 4, 10), new Point(-11, 4, 4), new Point(-11, 10, 4), new Point(-11, 10, 10));
        p33.setEmission(new Color(255, 50, 0));
        Polygon p44 = new Polygon(new Point(-11, -10, 3), new Point(-11, -10, -3), new Point(-11, -4, -3), new Point(-11, -4, 3));
        p44.setEmission(new Color(255, 50, 0));
        Polygon p55 = new Polygon(new Point(-11, -3, 3), new Point(-11, -3, -3), new Point(-11, 3, -3), new Point(-11, 3, 3));
        p55.setEmission(new Color(255, 50, 0));
        Polygon p66 = new Polygon(new Point(-11, 4, 3), new Point(-11, 4, -3), new Point(-11, 10, -3), new Point(-11, 10, 3));
        p66.setEmission(new Color(255, 50, 0));
        Polygon p77 = new Polygon(new Point(-11, -10, -4), new Point(-11, -10, -10), new Point(-11, -4, -10), new Point(-11, -4, -4));
        p77.setEmission(new Color(255, 50, 0));
        Polygon p88 = new Polygon(new Point(-11, -3, -4), new Point(-11, -3, -10), new Point(-11, 3, -10), new Point(-11, 3, -4));
        p88.setEmission(new Color(255,50,0));
        Polygon p99 = new Polygon(new Point(-11, 4, -4), new Point(-11, 4, -10), new Point(-11, 10, -10), new Point(-11, 10, -4));
        p99.setEmission(new Color(255, 50, 0));

        scene.geometries.add(p11, p22, p33, p44, p55, p66, p77, p88, p99);
        //endregion

        //region yellow cube face
        // Add black background rectangle
        Polygon rightFaceBackground = new Polygon(new Point(-11, 11, 10.9), new Point(11, 11, 10.9), new Point(11, -11, 10.9), new Point(-11, -11, 10.9));
        rightFaceBackground.setEmission(new Color(0, 0, 0));
        scene.geometries.add(rightFaceBackground);

        // Create the yellow cubes
        Polygon p10 = new Polygon(new Point(10, -10, 11), new Point(4, -10, 11), new Point(4, -4, 11), new Point(10, -4, 11));
        p10.setEmission(new Color(YELLOW));
        Polygon p20 = new Polygon(new Point(10, -3, 11), new Point(4, -3, 11), new Point(4, 3, 11), new Point(10, 3, 11));
        p20.setEmission(new Color(YELLOW));
        Polygon p30 = new Polygon(new Point(10, 4, 11), new Point(4, 4, 11), new Point(4, 10, 11), new Point(10, 10, 11));
        p30.setEmission(new Color(YELLOW));
        Polygon p40 = new Polygon(new Point(3, -10, 11), new Point(-3, -10, 11), new Point(-3, -4, 11), new Point(3, -4, 11));
        p40.setEmission(new Color(YELLOW));
        Polygon p50 = new Polygon(new Point(3, -3, 11), new Point(-3, -3, 11), new Point(-3, 3, 11), new Point(3, 3, 11));
        p50.setEmission(new Color(YELLOW));
        Polygon p60 = new Polygon(new Point(3, 4, 11), new Point(-3, 4, 11), new Point(-3, 10, 11), new Point(3, 10, 11));
        p60.setEmission(new Color(YELLOW));
        Polygon p70 = new Polygon(new Point(-4, -10, 11), new Point(-10, -10, 11), new Point(-10, -4, 11), new Point(-4, -4, 11));
        p70.setEmission(new Color(YELLOW));
        Polygon p80 = new Polygon(new Point(-4, -3, 11), new Point(-10, -3, 11), new Point(-10, 3, 11), new Point(-4, 3, 11));
        p80.setEmission(new Color(YELLOW));
        Polygon p90 = new Polygon(new Point(-4, 4, 11), new Point(-10, 4, 11), new Point(-10, 10, 11), new Point(-4, 10, 11));
        p90.setEmission(new Color(YELLOW));

        scene.geometries.add(p10, p20, p30, p40, p50, p60, p70, p80, p90);
        //endregion

        //region white cube face
        // Add black background rectangle
        Polygon leftFaceBackground = new Polygon(new Point(11, 11, -10.9), new Point(-11, 11, -10.9), new Point(-11, -11, -10.9), new Point(11, -11, -10.9));
        leftFaceBackground.setEmission(new Color(0, 0, 0));
        scene.geometries.add(leftFaceBackground);

        // Create the white cubes
        Polygon p100 = new Polygon(new Point(-10, -10, -11), new Point(-4, -10, -11), new Point(-4, -4, -11), new Point(-10, -4, -11));
        p100.setEmission(new Color(WHITE));
        Polygon p200 = new Polygon(new Point(-10, -3, -11), new Point(-4, -3, -11), new Point(-4, 3, -11), new Point(-10, 3, -11));
        p200.setEmission(new Color(WHITE));
        Polygon p300 = new Polygon(new Point(-10, 4, -11), new Point(-4, 4, -11), new Point(-4, 10, -11), new Point(-10, 10, -11));
        p300.setEmission(new Color(WHITE));
        Polygon p400 = new Polygon(new Point(-3, -10, -11), new Point(3, -10, -11), new Point(3, -4, -11), new Point(-3, -4, -11));
        p400.setEmission(new Color(WHITE));
        Polygon p500 = new Polygon(new Point(-3, -3, -11), new Point(3, -3, -11), new Point(3, 3, -11), new Point(-3, 3, -11));
        p500.setEmission(new Color(WHITE));
        Polygon p600 = new Polygon(new Point(-3, 4, -11), new Point(3, 4, -11), new Point(3, 10, -11), new Point(-3, 10, -11));
        p600.setEmission(new Color(WHITE));
        Polygon p700 = new Polygon(new Point(4, -10, -11), new Point(10, -10, -11), new Point(10, -4, -11), new Point(4, -4, -11));
        p700.setEmission(new Color(WHITE));
        Polygon p800 = new Polygon(new Point(4, -3, -11), new Point(10, -3, -11), new Point(10, 3, -11), new Point(4, 3, -11));
        p800.setEmission(new Color(WHITE));
        Polygon p900 = new Polygon(new Point(4, 4, -11), new Point(10, 4, -11), new Point(10, 10, -11), new Point(4, 10, -11));
        p900.setEmission(new Color(WHITE));

        scene.geometries.add(p100, p200, p300, p400, p500, p600, p700, p800, p900);
        //endregion

        //region green cube face
        // Add black background rectangle
        Polygon topFaceBackground = new Polygon(new Point(-11, 10.9, -11), new Point(11, 10.9, -11), new Point(11, 10.9, 11), new Point(-11, 10.9, 11));
        topFaceBackground.setEmission(new Color(0, 0, 0));
        scene.geometries.add(topFaceBackground);

        // Create the green cubes
        Polygon pt1 = new Polygon(new Point(-10, 11, -10), new Point(-4, 11, -10), new Point(-4, 11, -4), new Point(-10, 11, -4));
        pt1.setEmission(new Color(GREEN));
        Polygon pt2 = new Polygon(new Point(-10, 11, -3), new Point(-4, 11, -3), new Point(-4, 11, 3), new Point(-10, 11, 3));
        pt2.setEmission(new Color(GREEN));
        Polygon pt3 = new Polygon(new Point(-10, 11, 4), new Point(-4, 11, 4), new Point(-4, 11, 10), new Point(-10, 11, 10));
        pt3.setEmission(new Color(GREEN));
        Polygon pt4 = new Polygon(new Point(-3, 11, -10), new Point(3, 11, -10), new Point(3, 11, -4), new Point(-3, 11, -4));
        pt4.setEmission(new Color(GREEN));
        Polygon pt5 = new Polygon(new Point(-3, 11, -3), new Point(3, 11, -3), new Point(3, 11, 3), new Point(-3, 11, 3));
        pt5.setEmission(new Color(GREEN));
        Polygon pt6 = new Polygon(new Point(-3, 11, 4), new Point(3, 11, 4), new Point(3, 11, 10), new Point(-3, 11, 10));
        pt6.setEmission(new Color(GREEN));
        Polygon pt7 = new Polygon(new Point(4, 11, -10), new Point(10, 11, -10), new Point(10, 11, -4), new Point(4, 11, -4));
        pt7.setEmission(new Color(GREEN));
        Polygon pt8 = new Polygon(new Point(4, 11, -3), new Point(10, 11, -3), new Point(10, 11, 3), new Point(4, 11, 3));
        pt8.setEmission(new Color(GREEN));
        Polygon pt9 = new Polygon(new Point(4, 11, 4), new Point(10, 11, 4), new Point(10, 11, 10), new Point(4, 11, 10));
        pt9.setEmission(new Color(GREEN));

        scene.geometries.add(pt1, pt2, pt3, pt4, pt5, pt6, pt7, pt8, pt9);
        //endregion

        //region blue cube face
        // Add black background rectangle
        Polygon bottomFaceBackground = new Polygon(new Point(-11, -10.9, -11), new Point(11, -10.9, -11), new Point(11, -10.9, 11), new Point(-11, -10.9, 11));
        bottomFaceBackground.setEmission(new Color(0, 0, 0));
        scene.geometries.add(bottomFaceBackground);

        // Create the blue cubes
        Polygon pb1 = new Polygon(new Point(-10, -11, -10), new Point(-4, -11, -10), new Point(-4, -11, -4), new Point(-10, -11, -4));
        pb1.setEmission(new Color(BLUE));
        Polygon pb2 = new Polygon(new Point(-10, -11, -3), new Point(-4, -11, -3), new Point(-4, -11, 3), new Point(-10, -11, 3));
        pb2.setEmission(new Color(BLUE));
        Polygon pb3 = new Polygon(new Point(-10, -11, 4), new Point(-4, -11, 4), new Point(-4, -11, 10), new Point(-10, -11, 10));
        pb3.setEmission(new Color(BLUE));
        Polygon pb4 = new Polygon(new Point(-3, -11, -10), new Point(3, -11, -10), new Point(3, -11, -4), new Point(-3, -11, -4));
        pb4.setEmission(new Color(BLUE));
        Polygon pb5 = new Polygon(new Point(-3, -11, -3), new Point(3, -11, -3), new Point(3, -11, 3), new Point(-3, -11, 3));
        pb5.setEmission(new Color(BLUE));
        Polygon pb6 = new Polygon(new Point(-3, -11, 4), new Point(3, -11, 4), new Point(3, -11, 10), new Point(-3, -11, 10));
        pb6.setEmission(new Color(BLUE));
        Polygon pb7 = new Polygon(new Point(4, -11, -10), new Point(10, -11, -10), new Point(10, -11, -4), new Point(4, -11, -4));
        pb7.setEmission(new Color(BLUE));
        Polygon pb8 = new Polygon(new Point(4, -11, -3), new Point(10, -11, -3), new Point(10, -11, 3), new Point(4, -11, 3));
        pb8.setEmission(new Color(BLUE));
        Polygon pb9 = new Polygon(new Point(4, -11, 4), new Point(10, -11, 4), new Point(10, -11, 10), new Point(4, -11, 10));
        pb9.setEmission(new Color(BLUE));

        scene.geometries.add(pb1, pb2, pb3, pb4, pb5, pb6, pb7, pb8, pb9);
        //endregion

        //region triangular pyramid
        //Base triangle of the pyramid
        Triangle tB =new Triangle(new Point(27.13,-0.67,-1.13),new Point(46.4+1,12,-1.13),new Point(46.4+1,-12,-1.13));
        tB.setEmission(new Color(BLACK));
        //region smaller triangles of tB
        // Adjust the midpoints with the offset
        Point m1 = new Point((29 + 46 ) / 2, (0 + 10 ) / 2, (0 + 0 ) / 2);
        Point m2 = new Point((46 + 46 ) / 2, (10 + -10 ) / 2, (0 + 0) / 2);
        Point m3 = new Point((46 + 29 ) / 2, (-10 + 0 ) / 2, (0 + 0 ) / 2);

        Point h1 = new Point(29,0,0);
        Point h2 = new Point(46,10,0);
        Point h3 = new Point(46,-10,0);

        // Create 4 smaller triangles
        Triangle smallB1 = new Triangle(h1, m1, m3);
        smallB1.setEmission(new Color(GREEN));

        Triangle smallB2 = new Triangle(m1, h2, m2);
        smallB2.setEmission(new Color(RED));

        Triangle smallB3 = new Triangle(m3, m2, h3);
        smallB3.setEmission(new Color(BLUE));

        Triangle smallB4 = new Triangle(m1, m2, m3);
        smallB4.setEmission(new Color(YELLOW));
        //endregion

        //Three sides of the pyramid
        Triangle t1=new Triangle(new Point(27.13,-0.67,-1.13),new Point(46.4+1,12,-1.13),new Point(39+1.2,0,19.27));
        t1.setEmission(new Color(BLACK));
        //region smaller triangles of t1
        // Adjust the midpoints with the offset
        m1 = new Point((29 + 46 ) / 2-1-0.5, (0 + 10 ) / 2+0.1-0.5, (0 + 0 ) / 2-1+0.5);
        m2 = new Point((46 + 40 ) / 2-1-0.5, (10 + 0 ) / 2+0.1, (0 + 17 ) / 2-1);
        m3 = new Point((40 + 29 ) / 2-1-0.5+0.2+0.2, (0 + 0 ) / 2+0.1-0.3, (17 + 0 ) / 2-1+0.5);

        h1 = new Point(29-1-0.5+0.2,0+0.1-0.5,0-1+0.5);
        h2 = new Point(46-1-0.5,10+0.1,0-1);
        h3 = new Point(40-1-0.5,0+0.1,17-1);

        // Create 4 smaller triangles
        Triangle small11 = new Triangle(h1, m1, m3);
        small11.setEmission(new Color(GREEN));

        m1 = new Point((29 + 46 ) / 2-0.5+0.5, (0 + 10 ) / 2+0.1+0.2, (0 + 0 ) / 2-1+0.5);
        m2 = new Point((46 + 40 ) / 2-0.5+0.5, (10 + 0 ) / 2+0.1+0.5+0.5, (0 + 17 ) / 2-1+0.5);
        m3 = new Point((40 + 29 ) / 2-0.5, (0 + 0 ) / 2+0.1+0.5, (17 + 0 ) / 2-1);

        h1 = new Point(29-0.5,0+0.1+0.5,0-1);
        h2 = new Point(46-0.5+0.5,10+0.1+0.5+0.5,0-1+0.5);
        h3 = new Point(40-0.5,0+0.1+0.5,17-1);

        Triangle small12 = new Triangle(m1, h2, m2);
        small12.setEmission(new Color(RED));

        m1 = new Point((29 + 46 ) / 2-0.5, (0 + 10 ) / 2+0.1, (0 + 0 ) / 2+1);
        m2 = new Point((46 + 40 ) / 2-0.5+0.5, (10 + 0 ) / 2+0.1+0.5, (0 + 17 ) / 2+1);
        m3 = new Point((40 + 29 ) / 2-0.5+0.5, (0 + 0 ) / 2+0.1, (17 + 0 ) / 2+1);

        h1 = new Point(29-0.5,0+0.1,0+1);
        h2 = new Point(46-0.5,10+0.1,0+1);
        h3 = new Point(40-0.5+0.5,0+0.1+0.5,17+1);

        Triangle small13 = new Triangle(m3, m2, h3);
        small13.setEmission(new Color(BLUE));

        m1 = new Point((29 + 46 ) / 2-0.5, (0 + 10 ) / 2+0.1, (0 + 0 ) / 2);
        m2 = new Point((46 + 40 ) / 2-0.5, (10 + 0 ) / 2+0.1+0.5, (0 + 17 ) / 2);
        m3 = new Point((40 + 29 ) / 2-0.5, (0 + 0 ) / 2+0.1, (17 + 0 ) / 2);

        h1 = new Point(29-0.5,0+0.1,0);
        h2 = new Point(46-0.5,10+0.1,0);
        h3 = new Point(40-0.5,0+0.1,17);

        Triangle small14 = new Triangle(m1, m2, m3);
        small14.setEmission(new Color(YELLOW));
        //endregion

        Triangle t2=new Triangle(new Point(39+1.2,0,19.27),new Point(27.13,-0.67,-1.13),new Point(46.4+1,-12,-1.13));
        t2.setEmission(new Color(20,20,20));
        //region smaller triangles of t2
        // Adjust the midpoints with the offset
        m1 = new Point((29 + 40 ) / 2, (0 + 0 ) / 2-1-0.5, (0 + 17 ) / 2-0.5);
        m2 = new Point((40 + 46 ) / 2, (0 + -10 ) / 2-1, (17 + 0 ) / 2);
        m3 = new Point((46 + 29 ) / 2, (0 + -10 ) / 2-1-0.5, (0 + 0 ) / 2-0.5);

        h1 = new Point(29,0-1-0.5,0-0.5);
        h2 = new Point(40,0-1,17);
        h3 = new Point(46,-10-1,0);

        Triangle small21 = new Triangle(h1, m1, m3);
        small21.setEmission(new Color(BLUE));

        m1 = new Point((29 + 40 ) / 2, (0 + 0 ) / 2-1, (0 + 17 ) / 2+0.5);
        m2 = new Point((40 + 46 ) / 2, (0 + -10 ) / 2-1, (17 + 0 ) / 2+0.5);
        m3 = new Point((46 + 29 ) / 2, (0 + -10 ) / 2-1, (0 + 0 ) / 2);

        h1 = new Point(29,0-1,0);
        h2 = new Point(40,0-1+0.5-0.3,17+0.5);
        h3 = new Point(46,-10-1,0);

        Triangle small22 = new Triangle(m1, h2, m2);
        small22.setEmission(new Color(GREEN));

        m1 = new Point((29 + 40 ) / 2, (0 + 0 ) / 2-1, (0 + 17 ) / 2);
        m2 = new Point((40 + 46 ) / 2, (0 + -10 ) / 2-1-0.5, (17 + 0 ) / 2-0.5);
        m3 = new Point((46 + 29 ) / 2, (0 + -10 ) / 2-1, (0 + 0 ) / 2-0.5);

        h1 = new Point(29,0-1,0);
        h2 = new Point(40,0-1,17);
        h3 = new Point(46,-10-1,0-0.5);


        Triangle small23 = new Triangle(m3, m2, h3);
        small23.setEmission(new Color(RED));

        m1 = new Point((29 + 40 ) / 2, (0 + 0 ) / 2-1, (0 + 17 ) / 2);
        m2 = new Point((40 + 46 ) / 2, (0 + -10 ) / 2-1, (17 + 0 ) / 2);
        m3 = new Point((46 + 29 ) / 2, (0 + -10 ) / 2-1, (0 + 0 ) / 2);

        h1 = new Point(29,0-1,0);
        h2 = new Point(40,0-1,17);
        h3 = new Point(46,-10-1,0);


        Triangle small24 = new Triangle(m1, m2, m3);
        small24.setEmission(new Color(YELLOW));
        //endregion

        //Triangle t3=new Triangle(new Point(40,0,17),new Point(46,10,0),new Point(46,-10,0));
        Triangle t3=new Triangle(new Point(39+1.2,0,19.27),new Point(46.4+1,12,-1.13),new Point(46.4+1,-12,-1.13));
        t3.setEmission(new Color(30,30,30));
        //region smaller triangles of t3
        // Adjust the midpoints with the offset

        // Create 4 smaller triangles

        m1 = new Point((40 + 46 ) / 2+0.1+1, (0 + 10 ) / 2, (17 + 0 ) / 2+1);
        m2 = new Point((46 + 46 ) / 2+0.1+1, (10 + -10 ) / 2, (0 + 0 ) / 2+1);
        m3 = new Point((46 + 40 ) / 2+0.1+1, (-10 + 0 ) / 2, (0 + 17 ) / 2+1);

        h1 = new Point(39.7+1, 0, 17+1);
        h2 = new Point(46+0.1+1, 10, 0+1);
        h3 = new Point(46+0.1+1, -10, 0+1);

        Triangle small31 = new Triangle(h1, m1, m3);
        small31.setEmission(new Color(RED));

        m1 = new Point((40 + 46 ) / 2+0.5+1, (0 + 10 ) / 2+1, (17 + 0 ) / 2);
        m2 = new Point((46 + 46 ) / 2+0.5+1, (10 + -10 ) / 2+1, (0 + 0 ) / 2);
        m3 = new Point((46 + 40 ) / 2+0.5+1, (-10 + 0 ) / 2+1, (0 + 17 ) / 2);

        h1 = new Point(40+0.5+1, 0+1, 17);
        h2 = new Point(46+0.5+1, 10+1, 0);
        h3 = new Point(46+0.5+1, -10+1, 0);

        Triangle small32 = new Triangle(m1, h2, m2);
        small32.setEmission(new Color(GREEN));

        m1 = new Point((40 + 46 ) / 2+0.5+1, (0 + 10 ) / 2-1, (17 + 0 ) / 2);
        m2 = new Point((46 + 46 ) / 2+0.5+1, (10 + -10 ) / 2-1, (0 + 0 ) / 2);
        m3 = new Point((46 + 40 ) / 2+0.5+1, (-10 + 0 ) / 2-1, (0 + 17 ) / 2);

        h1 = new Point(40+0.5+1, 0-1, 17);
        h2 = new Point(46+0.5+1, 10-1, 0);
        h3 = new Point(46+0.5+1, -10-1, 0);

        Triangle small33 = new Triangle(m3, m2, h3);
        small33.setEmission(new Color(BLUE));

        m1 = new Point((40 + 46 ) / 2+0.5+1, (0 + 10 ) / 2, (17 + 0 ) / 2);
        m2 = new Point((46 + 46 ) / 2+0.5+1, (10 + -10 ) / 2, (0 + 0 ) / 2);
        m3 = new Point((46 + 40 ) / 2+0.5+1, (-10 + 0 ) / 2, (0 + 17 ) / 2);

        h1 = new Point(40+0.5+1, 0, 17);
        h2 = new Point(46+0.5+1, 10, 0);
        h3 = new Point(46+0.5+1, -10, 0);

        Triangle small34 = new Triangle(m1, m2, m3);
        small34.setEmission(new Color(YELLOW));
        //endregion

        // Output the smaller triangles or add them to your scene

        scene.geometries.add(tB,t1,t2,t3,
                small11,small12,small13,small14,
                small21,small22,small23,small24,
                small31,small32,small33,small34);
        //smallB1,smallB2,smallB3,smallB4,);

        //endregion

        //region lamp
        // Add a sphere as the lamp
        Sphere s1= new Sphere(7,new Point(0,20,30));
        s1.setEmission(new Color(75,0,150))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30).setkT(0.4));
        // Add a rectangle as the lamp's base
        Polygon s2=new Polygon(new Point(0,19.9,37),new Point(0,20.1,37),new Point(0,20.1,150),new Point(0,19.9,150));
        s2.setEmission(new Color(0,0,0));
        scene.geometries.add(s1,s2);
        //endregion

        //region lights
        // add spotLight from the top of the picture to the floor
        SpotLight spotLight = new SpotLight(new Color(255, 255, 255), new Point(0, 0, 100), new Vector(0, 0, -1))
                .setkL(0.001).setkQ(0.0005);
        scene.lights.add(spotLight);

        // add pointLight from the sphere
        PointLight pointLight = new PointLight(new Color(WHITE), new Point(0,20,30))
                .setkL(0.001).setkQ(0.0005);
        scene.lights.add(pointLight);
        //endregion

        //camera
        Camera.Builder camera = Camera.getBuilder()
                .setDirection(new Vector(-70,-70,-30), new Vector(-3,-3,14))
                .setRayTracer(new SimpleRayTracer(scene));
        ImageWriter imageWriter=new ImageWriter("Minip1", 900, 900);
        camera.setLocation(new Point(70, 70, 30))
                .setVpSize(100, 100)
                .setVpDistance(100)
                .setImageWriter(imageWriter)
                .setRayTracer(new SimpleRayTracer(scene))
                .build()
                .setAntiAliasingFactor(4) // Set 4x4 anti-aliasing
                .renderImage()
                .writeToImage();
    }
}
