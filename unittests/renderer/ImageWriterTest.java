package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

/**
 * A class of tests for the ImageWriter class
 */
public class ImageWriterTest {

    /**
     * Test method for {@link renderer.ImageWriter#writeToImage()}.
     * Test for ImageWriter 16x10
     * The entire image is painted in yellow
     * The columns and rows are painted in red
     */
    @Test
    void testWriteToImage() {
        //T1: Test for ImageWriter 16x10
        ImageWriter imageWriter1 = new ImageWriter("Test", 800, 500);

        //paint the entire image in yellow
        for (int i = 0; i < imageWriter1.getNx(); i++)
            for (int j = 0; j < imageWriter1.getNy(); j++)
                imageWriter1.writePixel(i, j, new Color(java.awt.Color.yellow));

        //paint the columns in red
        for (int i = 0; i < imageWriter1.getNx(); i += 50)
            for (int j = 0; j < imageWriter1.getNy(); j++)
                imageWriter1.writePixel(i, j, new Color(java.awt.Color.red));

        //paint the rows in red
        for (int i = 0; i < imageWriter1.getNx(); i++)
            for (int j = 0; j < imageWriter1.getNy(); j += 50)
                imageWriter1.writePixel(i, j, new Color(java.awt.Color.red));

        //create the image
        imageWriter1.writeToImage();
    }
}
