package image.Pixel;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Wrapper class for a pixel matrix
 */
public class PixelRaster {

    /**
     * Matrix of pixels with coordinate [x][y] with origo in the upper left corner.
     */
    private Pixel[][] pixels;
    private boolean hasAlphaRaster;

    private int width, height;

    public PixelRaster(BufferedImage image) {

        long start = System.currentTimeMillis();
        System.out.println("Creating PixelRaster ... ");
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        width = image.getWidth();
        height = image.getHeight();

        pixels = new Pixel[width][height];

        if (image.getAlphaRaster() != null) {
            hasAlphaRaster = true;
            for (int count = 0, row = 0, col = 0; count < data.length; count += 4) {
                pixels[col][row] = new AlphaPixel(data[count + 3], data[count + 2], data[count + 1], data[count]);
                if (col + 1 == width) {
                    col = 0;
                    row++;
                } else {
                    col++;
                }
            }
        } else {
            hasAlphaRaster = false;
            for (int count = 0, row = 0, col = 0; count < data.length; count += 3) {
                pixels[col][row] = new Pixel(data[count + 2], data[count + 1], data[count]);
                if (col + 1 == width) {
                    col = 0;
                    row++;
                } else {
                    col++;
                }
            }
        }
        System.out.println("PixalRaster creation took " + (System.currentTimeMillis()-start)+"ms");
    }

    /**
     * Get the pixels as an array in the order (alpha), blue, green, red.
     *
     * @return byte array where each row is places after eachother,
     */
    public byte[] rawData() {
        System.out.println("Starting raw data export...");
        long start = System.currentTimeMillis();
        byte[] array;

        if (hasAlphaRaster) {
            array = new byte[width * height * 4];
            AlphaPixel pixel;
            for (int count = 0, x = 0, y = 0; count < array.length; count += 4) {
                pixel = (AlphaPixel) pixels[x][y];
                array[count] = pixel.getAlpha();
                array[count + 1] = pixel.getBlue();
                array[count + 2] = pixel.getGreen();
                array[count + 3] = pixel.getRed();
                x++;
                if (x == width) {
                    x = 0;
                    y++;
                }
            }
        } else {
            array = new byte[width * height * 3];
            Pixel pixel;
            for (int count = 0, x = 0, y = 0; count < array.length; count += 3) {
                pixel = pixels[x][y];
                array[count] = pixel.getBlue();
                array[count + 1] = pixel.getGreen();
                array[count + 2] = pixel.getRed();
                x++;
                if (x == width) {
                    x = 0;
                    y++;
                }
            }
        }
        System.out.println("RawData took: " + (System.currentTimeMillis() - start) + "ms");
        return array;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean hasAlphaRaster() {
        return hasAlphaRaster;
    }

    public Pixel getPixel(int x, int y) {
        //TODO Check if arguments are valid

        return pixels[x][y];

    }

    public void setPixel(int x, int y, Pixel pixel) {
        pixels[x][y] = pixel;
    }
}
