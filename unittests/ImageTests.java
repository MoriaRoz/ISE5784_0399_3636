import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBase;
import renderer.SimpleRayTracer;
import scene.Scene;

import static java.awt.Color.*;

/**
 * Image tests
 */
public class ImageTests {

    @Test
    public void rubiksCube() {
        Scene scene = new Scene("Rubik's Cube");

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
        Triangle tB =new Triangle(new Point(29,0,0),new Point(46,10,0),new Point(46,-10,0));
        tB.setEmission(new Color(BLACK));

        //Three sides of the pyramid
        Triangle t1=new Triangle(new Point(29,0,0),new Point(46,10,0),new Point(40,0,17));
        t1.setEmission(new Color(10,10,10));

        Triangle t2=new Triangle(new Point(29,0,0),new Point(40,0,17),new Point(46,-10,0));
        t2.setEmission(new Color(20,20,20));

        Triangle t3=new Triangle(new Point(40,0,17),new Point(46,10,0),new Point(46,-10,0));
        t3.setEmission(new Color(30,30,30));
        scene.geometries.add(tB,t1,t2,t3);

        //endregion

        //region sphere
        // Add a sphere
        Sphere s1= new Sphere(7,new Point(0,20,30));
                s1.setEmission(new Color(BLUE))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30).setkT(0.4));
        scene.geometries.add(s1);
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
                .setDirection(new Vector(-70,-70,-30), new Vector(-3,-3,14))  // שינוי כיוון המצלמה
                .setRayTracer(new SimpleRayTracer(scene));
        ImageWriter imageWriter = new ImageWriter("Rubik's Cube", 900, 900);
        camera.setImageWriter(imageWriter)
                .setLocation(new Point(70, 70, 30))  // שינוי מיקום המצלמה
                .setVpDistance(100)
                .setVpSize(100, 100)
                .setRayTracer(new SimpleRayTracer(scene))
                .build()
                .renderImage()
                .writeToImage();
    }
}
