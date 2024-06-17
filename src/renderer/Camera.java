package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.MissingResourceException;
import static primitives.Util.isZero;

/**
 * Camera class represents a camera in the 3D space
 */
public class Camera implements Cloneable{

    public static class Builder{
        final private Camera camera = new Camera();

        /**
         * @param p0 the location of the camera
         * @return the Builder object
         */
        public Builder setLocation(Point p0) {
            camera.p0 = p0;
            return this;
        }

        /**
         * @param vUp the up vector of the camera
         * @param vTo the to vector of the camera
         * @return the Builder object
         */
        public Builder setDirection( Vector vTo,Vector vUp){
            if (vTo.dotProduct(vUp) != 0)
                throw new IllegalArgumentException("vTo and vUp must be orthogonal");
            camera.vTo = vTo.normalize();
            camera.vUp = vUp.normalize();
            return this;
        }

        /**
         * @param width the width of the view plane
         * @param height the height of the view plane
         * @return the Builder object
         */
        public Builder setVpSize(double width, double height){
            camera.width = width;
            camera.height = height;
            return this;
        }

        /**
         * @param distance the distance from the camera to the view plane
         * @return the Builder object
         */
        public Builder setVpDistance(double distance) {
            camera.distance = distance;
            return this;
        }

        /**
         * @return the Camera object
         */
        public Camera build() {
            if(camera.p0 == null)
                throw new MissingResourceException("Location of the camera is not defined", "Camera", "p0");
            if(camera.vUp == null)
                throw new MissingResourceException("Up vector of the camera is not defined", "Camera", "vUp");
            if(camera.vTo == null)
                throw new MissingResourceException("To vector of the camera is not defined", "Camera", "vTo");
            if(camera.width == 0.0)
                throw new MissingResourceException("Width of the view plane is not defined", "Camera", "width");
            if(camera.height == 0.0)
                throw new MissingResourceException("Height of the view plane is not defined", "Camera", "height");
            if(camera.distance == 0.0)
                throw new MissingResourceException("Distance of the view plane is not defined", "Camera", "distance");

            if(camera.distance <= 0)
                throw new IllegalArgumentException("Distance of the view plane must be positive");
            if(camera.width <= 0)
                throw new IllegalArgumentException("Width of the view plane must be positive");
            if(camera.height <= 0)
                throw new IllegalArgumentException("Height of the view plane must be positive");

            if(camera.vRight == null)
                camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
    private Point p0;//the location of the camera
    private Vector vUp, vTo, vRight;//the orientation of the camera
    private double width=0.0, height=0.0, distance=0.0;//the size of the view plane and the distance from the camera

    private Camera(){
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
    }

    /**
     * @return the Builder object
     */
    public static Builder getBuilder(){
        return new Builder();
    }

    /**
     * Constructs a ray from the camera to a pixel in the view plane
     * @param nX the number of pixels in the x direction
     * @param nY the number of pixels in the y direction
     * @param j the x index of the pixel
     * @param i the y index of the pixel
     * @return the ray from the camera to the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i){
        Point pC = p0.add(vTo.scale(distance));
        double rX = width / nX;
        double rY = height / nY;
        double xJ = (j - (nX - 1) / 2.0) * rX;
        double yI = (i - (nY - 1) / 2.0) * rY;
        Point pIJ = pC;
        if (!isZero(xJ))
            pIJ = pIJ.add(vRight.scale(xJ));
        if (!isZero(yI))
            pIJ = pIJ.add(vUp.scale(-yI));
        Vector vIJ = pIJ.subtract(p0);
        return new Ray(p0, vIJ);
    }
}
