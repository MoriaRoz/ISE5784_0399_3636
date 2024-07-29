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

public class tringleCube {

        @Test
        public void tringleCube() {
            Scene scene = new Scene("tringleCube");

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

//

            //        //region triangular pyramid
//        //Base triangle of the pyramid
//        Triangle tB =new Triangle(new Point(29,0,0),new Point(46,10,0),new Point(46,-10,0));
//        tB.setEmission(new Color(BLACK));
//
//        //Three sides of the pyramid
//        Triangle t1=new Triangle(new Point(29,0,0),new Point(46,10,0),new Point(40,0,17));
//        t1.setEmission(new Color(10,10,10));
//
//        Triangle t2=new Triangle(new Point(29,0,0),new Point(40,0,17),new Point(46,-10,0));
//        t2.setEmission(new Color(20,20,20));
//
//        Triangle t3=new Triangle(new Point(40,0,17),new Point(46,10,0),new Point(46,-10,0));
//        t3.setEmission(new Color(30,30,30));
//        scene.geometries.add(tB,t1,t2,t3);
//
//        //endregion


            //region triangular pyramid
            //Base triangle of the pyramid
            Triangle tB =new Triangle(new Point(27.13,-0.67,-1.13),new Point(46.4+1,12,-1.13),new Point(46.4+1,-12,-1.13));
            tB.setEmission(new Color(BLACK));

//region smaller triangles of tB
// Adjust the midpoints with the offset
            Point m1 = new Point((29 + 46 ) / 2, (0 + 10 ) / 2, (0 + 0 ) / 2);
            Point m2 = new Point((46 + 46 ) / 2, (10 + -10 ) / 2, (0 + 0) / 2);
            Point m3 = new Point((46 + 29 ) / 2, (-10 + 0 ) / 2, (0 + 0 ) / 2);

            Point p1 = new Point(29,0,0);
            Point p2 = new Point(46,10,0);
            Point p3 = new Point(46,-10,0);

// Create 4 smaller triangles
            Triangle smallB1 = new Triangle(p1, m1, m3);
            smallB1.setEmission(new Color(GREEN));

            Triangle smallB2 = new Triangle(m1, p2, m2);
            smallB2.setEmission(new Color(RED));

            Triangle smallB3 = new Triangle(m3, m2, p3);
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

            p1 = new Point(29-1-0.5+0.2,0+0.1-0.5,0-1+0.5);
            p2 = new Point(46-1-0.5,10+0.1,0-1);
            p3 = new Point(40-1-0.5,0+0.1,17-1);

// Create 4 smaller triangles
            Triangle small11 = new Triangle(p1, m1, m3);
            small11.setEmission(new Color(GREEN));

            m1 = new Point((29 + 46 ) / 2-0.5+0.5, (0 + 10 ) / 2+0.1+0.2, (0 + 0 ) / 2-1+0.5);
            m2 = new Point((46 + 40 ) / 2-0.5+0.5, (10 + 0 ) / 2+0.1+0.5+0.5, (0 + 17 ) / 2-1+0.5);
            m3 = new Point((40 + 29 ) / 2-0.5, (0 + 0 ) / 2+0.1+0.5, (17 + 0 ) / 2-1);

            p1 = new Point(29-0.5,0+0.1+0.5,0-1);
            p2 = new Point(46-0.5+0.5,10+0.1+0.5+0.5,0-1+0.5);
            p3 = new Point(40-0.5,0+0.1+0.5,17-1);

            Triangle small12 = new Triangle(m1, p2, m2);
            small12.setEmission(new Color(RED));

            m1 = new Point((29 + 46 ) / 2-0.5, (0 + 10 ) / 2+0.1, (0 + 0 ) / 2+1);
            m2 = new Point((46 + 40 ) / 2-0.5+0.5, (10 + 0 ) / 2+0.1+0.5, (0 + 17 ) / 2+1);
            m3 = new Point((40 + 29 ) / 2-0.5+0.5, (0 + 0 ) / 2+0.1, (17 + 0 ) / 2+1);

            p1 = new Point(29-0.5,0+0.1,0+1);
            p2 = new Point(46-0.5,10+0.1,0+1);
            p3 = new Point(40-0.5+0.5,0+0.1+0.5,17+1);

            Triangle small13 = new Triangle(m3, m2, p3);
            small13.setEmission(new Color(BLUE));

            m1 = new Point((29 + 46 ) / 2-0.5, (0 + 10 ) / 2+0.1, (0 + 0 ) / 2);
            m2 = new Point((46 + 40 ) / 2-0.5, (10 + 0 ) / 2+0.1+0.5, (0 + 17 ) / 2);
            m3 = new Point((40 + 29 ) / 2-0.5, (0 + 0 ) / 2+0.1, (17 + 0 ) / 2);

            p1 = new Point(29-0.5,0+0.1,0);
            p2 = new Point(46-0.5,10+0.1,0);
            p3 = new Point(40-0.5,0+0.1,17);

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

             p1 = new Point(29,0-1-0.5,0-0.5);
             p2 = new Point(40,0-1,17);
             p3 = new Point(46,-10-1,0);

// Create 4 smaller triangles
            Triangle small21 = new Triangle(p1, m1, m3);
            small21.setEmission(new Color(BLUE));

            m1 = new Point((29 + 40 ) / 2, (0 + 0 ) / 2-1, (0 + 17 ) / 2+0.5);
            m2 = new Point((40 + 46 ) / 2, (0 + -10 ) / 2-1, (17 + 0 ) / 2+0.5);
            m3 = new Point((46 + 29 ) / 2, (0 + -10 ) / 2-1, (0 + 0 ) / 2);

            p1 = new Point(29,0-1,0);
            p2 = new Point(40,0-1+0.5-0.3,17+0.5);
            p3 = new Point(46,-10-1,0);

            Triangle small22 = new Triangle(m1, p2, m2);
            small22.setEmission(new Color(GREEN));

            m1 = new Point((29 + 40 ) / 2, (0 + 0 ) / 2-1, (0 + 17 ) / 2);
            m2 = new Point((40 + 46 ) / 2, (0 + -10 ) / 2-1-0.5, (17 + 0 ) / 2-0.5);
            m3 = new Point((46 + 29 ) / 2, (0 + -10 ) / 2-1, (0 + 0 ) / 2-0.5);

            p1 = new Point(29,0-1,0);
            p2 = new Point(40,0-1,17);
            p3 = new Point(46,-10-1,0-0.5);


            Triangle small23 = new Triangle(m3, m2, p3);
            small23.setEmission(new Color(RED));

            m1 = new Point((29 + 40 ) / 2, (0 + 0 ) / 2-1, (0 + 17 ) / 2);
            m2 = new Point((40 + 46 ) / 2, (0 + -10 ) / 2-1, (17 + 0 ) / 2);
            m3 = new Point((46 + 29 ) / 2, (0 + -10 ) / 2-1, (0 + 0 ) / 2);

            p1 = new Point(29,0-1,0);
            p2 = new Point(40,0-1,17);
            p3 = new Point(46,-10-1,0);


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

             p1 = new Point(39.7+1, 0, 17+1);
             p2 = new Point(46+0.1+1, 10, 0+1);
             p3 = new Point(46+0.1+1, -10, 0+1);

            Triangle small31 = new Triangle(p1, m1, m3);
            small31.setEmission(new Color(RED));

            m1 = new Point((40 + 46 ) / 2+0.5+1, (0 + 10 ) / 2+1, (17 + 0 ) / 2);
            m2 = new Point((46 + 46 ) / 2+0.5+1, (10 + -10 ) / 2+1, (0 + 0 ) / 2);
            m3 = new Point((46 + 40 ) / 2+0.5+1, (-10 + 0 ) / 2+1, (0 + 17 ) / 2);

            p1 = new Point(40+0.5+1, 0+1, 17);
            p2 = new Point(46+0.5+1, 10+1, 0);
            p3 = new Point(46+0.5+1, -10+1, 0);

            Triangle small32 = new Triangle(m1, p2, m2);
            small32.setEmission(new Color(GREEN));

            m1 = new Point((40 + 46 ) / 2+0.5+1, (0 + 10 ) / 2-1, (17 + 0 ) / 2);
            m2 = new Point((46 + 46 ) / 2+0.5+1, (10 + -10 ) / 2-1, (0 + 0 ) / 2);
            m3 = new Point((46 + 40 ) / 2+0.5+1, (-10 + 0 ) / 2-1, (0 + 17 ) / 2);

            p1 = new Point(40+0.5+1, 0-1, 17);
            p2 = new Point(46+0.5+1, 10-1, 0);
            p3 = new Point(46+0.5+1, -10-1, 0);

            Triangle small33 = new Triangle(m3, m2, p3);
            small33.setEmission(new Color(BLUE));

            m1 = new Point((40 + 46 ) / 2+0.5+1, (0 + 10 ) / 2, (17 + 0 ) / 2);
            m2 = new Point((46 + 46 ) / 2+0.5+1, (10 + -10 ) / 2, (0 + 0 ) / 2);
            m3 = new Point((46 + 40 ) / 2+0.5+1, (-10 + 0 ) / 2, (0 + 17 ) / 2);

            p1 = new Point(40+0.5+1, 0, 17);
            p2 = new Point(46+0.5+1, 10, 0);
            p3 = new Point(46+0.5+1, -10, 0);

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
            ImageWriter imageWriter = new ImageWriter("tringleCube", 900, 900);
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


