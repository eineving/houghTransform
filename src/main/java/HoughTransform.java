import image.Line;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.List;

/**
 * Created by Daniel on 2015-07-12.
 */
public class HoughTransform {
    public List<Line> findLines(BufferedImage image) {

        //TODO implement
        return null;

    }

    private Pixel[][] getPixelMatrix(BufferedImage image) {
        byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        int height = image.getHeight();
        int width = image.getWidth();


        Pixel[][] result = new Pixel[width][height];


        if (image.getAlphaRaster() != null) {
            for (int count = 0, row = 0, col = 0; count < pixels.length; count += 4) {
                result[col][row] = new AlphaPixel(pixels[count + 3], pixels[count + 2], pixels[count + 1], pixels[count]);
                if (col + 1 == width) {
                    col = 0;
                    row++;
                } else {
                    col++;
                }
            }
        } else {
            for (int count = 0, row = 0, col = 0; count < pixels.length; count += 3) {
                result[col][row] = new Pixel(pixels[count + 2], pixels[count + 1], pixels[count]);
                if (col + 1 == width) {
                    col = 0;
                    row++;
                } else {
                    col++;
                }
            }
        }
        return result;
    }

}
