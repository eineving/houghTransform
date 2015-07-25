import image.Image;
import image.Line;
import image.Pixel.PixelRaster;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Daniel on 2015-07-12.
 */
public class HoughTransform {

    public HoughTransform() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:\\Users\\Daniel\\code\\houghTransform\\src\\main\\resources\\References\\20150613_110956221_iOS.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image golfImage = new Image(image);

        for (int col = 1, row = 1; row <= golfImage.getHeight(); col++) {

            golfImage.setPixel(col, row, (byte) 0, (byte) 100, (byte) 0);
            if (col == golfImage.getWidth()) {
                row++;
                col = 1;
            }
        }

        BufferedImage fixed = golfImage.getBufferedImage();

        try {
            ImageIO.write(fixed, "jpg", new File("C:\\Users\\Daniel\\code\\output2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Line> findLines(BufferedImage image) {

        //TODO implement
        return null;

    }


    private BufferedImage noGreenImage(PixelRaster raster) {
        BufferedImage image = new BufferedImage(raster.getWidth(), raster.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < raster.getWidth(); x++) {
            for (int y = 0; y < raster.getHeight(); y++) {
                raster.getPixel(x, y).setGreen((byte) 0);
                image.setRGB(x, y, raster.getPixel(x, y).getRGB());
            }
        }
        return image;
    }

    /*
    private BufferedImage createImage(PixelRaster pixels, int bufferedImageType) throws IOException {

        BufferedImage image = new BufferedImage(pixels.getWidth(), pixels.getHeight(), bufferedImageType);
        int bands = 3;
        if (pixels.hasAlphaRaster()) {
            bands = 4;
        }
        byte[] imgData = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        byte[] rawData = pixels.rawData();

        System.arraycopy(rawData, 0, imgData, 0, rawData.length);

        return image;

    }

    private BufferedImage createImage(PixelRaster pixels) throws IOException {
        return createImage(pixels, BufferedImage.TYPE_3BYTE_BGR);
    }
*/
    public static void main(String[] args) {

        //TODO temporary main
        System.out.println("Starting application ...");
        long start = System.currentTimeMillis();
        new HoughTransform();
        System.out.println("Finished!");
        System.out.println("Runtime: " + (System.currentTimeMillis()-start) + "ms");
    }
}
