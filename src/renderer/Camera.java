package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * Camera class represents a camera in the 3D space
 */
public class Camera implements Cloneable {


    private Point p0;//the location of the camera
    private Vector vUp, vTo, vRight;//the orientation of the camera
    private double width = 0.0, height = 0.0, distance = 0.0;//the size of the view plane and the distance from the camera
    private ImageWriter imageWriter;//the image writer of the camera
    private RayTracerBase rayTracer;//the ray tracer of the camera

    private Camera() {
    }

    /**
     * @return the Builder object
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    //Getters
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
     * Constructs a ray from the camera to a pixel in the view plane
     *
     * @param nX the number of pixels in the x direction
     * @param nY the number of pixels in the y direction
     * @param j  the x index of the pixel
     * @param i  the y index of the pixel
     * @return the ray from the camera to the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
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
        return new Ray(p0, vIJ.normalize());
    }

    /**
     * Renders the image
     *
     * @return the camera
     */
    public Camera renderImage() {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++)
            for (int j = 0; j < nX; j++) {
                castRay(nX, nY, j, i);
            }
        return this;
    }

    /**
     * Casts a ray from the camera to a pixel in the view plane
     *
     * @param nX the number of pixels in the x direction
     * @param nY the number of pixels in the y direction
     * @param j  the x index of the pixel
     * @param i  the y index of the pixel
     */
    private void castRay(int nX, int nY, int j, int i) {
        Ray ray = constructRay(nX, nY, j, i);
        Color color = rayTracer.traceRay(ray);
        imageWriter.writePixel(j, i, color);
    }

    /**
     * paint the lines and columns of the view plane with the given color
     */
    public Camera printGrid(int interval, Color color) {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; i += interval)
            for (int j = 0; j < nY; j++)
                imageWriter.writePixel(j, i, color);

        for (int i = 0; i < nY; i += interval)
            for (int j = 0; j < nX; j++)
                imageWriter.writePixel(i, j, color);

        return this;
    }

    /**
     * write the image to the file
     */
    public void writeToImage() {
        imageWriter.writeToImage();
    }

    /**
     * Builder class for the Camera class
     */
    public static class Builder {
        final private Camera camera = new Camera();

        /**
         * @param p0 the location of the camera
         * @return the Builder object
         */
        public Builder setLocation(Point p0) {
            if (p0 == null)
                throw new MissingResourceException("Location of the camera is not defined", "Camera", "p0");

            camera.p0 = p0;
            return this;
        }

        /**
         * @param vUp the up vector of the camera
         * @param vTo the to vector of the camera
         * @return the Builder object
         */
        public Builder setDirection(Vector vTo, Vector vUp) {
            if (vTo == null)
                throw new MissingResourceException("To vector of the camera is not defined", "Camera", "vTo");
            if (vUp == null)
                throw new MissingResourceException("Up vector of the camera is not defined", "Camera", "vUp");
            if (vTo.dotProduct(vUp) != 0)
                throw new IllegalArgumentException("vTo and vUp must be orthogonal");

            camera.vTo = vTo.normalize();
            camera.vUp = vUp.normalize();
            return this;
        }

        /**
         * @param width  the width of the view plane
         * @param height the height of the view plane
         * @return the Builder object
         */
        public Builder setVpSize(double width, double height) {
            if (width <= 0)
                throw new IllegalArgumentException("Width of the view plane must be positive");
            if (height <= 0)
                throw new IllegalArgumentException("Height of the view plane must be positive");

            camera.width = width;
            camera.height = height;
            return this;
        }

        /**
         * @param distance the distance from the camera to the view plane
         * @return the Builder object
         */
        public Builder setVpDistance(double distance) {
            if (distance <= 0)
                throw new IllegalArgumentException("Distance of the view plane must be positive");

            camera.distance = distance;
            return this;
        }

        /**
         * @param imageWriter the image writer of the camera
         * @return the Builder object
         */
        public Builder setImageWriter(ImageWriter imageWriter) {
            if (imageWriter == null)
                throw new MissingResourceException("Image writer of the camera is not defined", "Camera", "imageWriter");

            camera.imageWriter = imageWriter;
            return this;
        }

        /**
         * @param rayTracer the ray tracer of the camera
         * @return the Builder object
         */
        public Builder setRayTracer(RayTracerBase rayTracer) {
            if (rayTracer == null)
                throw new MissingResourceException("Ray tracer of the camera is not defined", "Camera", "rayTracer");

            camera.rayTracer = rayTracer;
            return this;
        }

        /**
         * @return the camera object
         * @throws MissingResourceException if a resource is missing
         */
        public Camera build() {
            if (camera.p0 == null)
                throw new MissingResourceException("Location of the camera is not defined", "Camera", "p0");
            if (camera.vUp == null)
                throw new MissingResourceException("Up vector of the camera is not defined", "Camera", "vUp");
            if (camera.vTo == null)
                throw new MissingResourceException("To vector of the camera is not defined", "Camera", "vTo");
            if (camera.imageWriter == null)
                throw new MissingResourceException("Image writer of the camera is not defined", "Camera", "imageWriter");
            if (camera.rayTracer == null)
                throw new MissingResourceException("Ray tracer of the camera is not defined", "Camera", "rayTracer");
            if (camera.width == 0.0)
                throw new MissingResourceException("Width of the view plane is not defined", "Camera", "width");
            if (camera.height == 0.0)
                throw new MissingResourceException("Height of the view plane is not defined", "Camera", "height");
            if (camera.distance == 0.0)
                throw new MissingResourceException("Distance of the view plane is not defined", "Camera", "distance");

            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();

            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }
        }
    }
}
