import image.AlphaPixel;
import image.Line;
import image.Pixel;
import image.PixelRaster;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

        PixelRaster raster = new PixelRaster(image);
        BufferedImage fixed = null;
        try {
            fixed = createImage(raster);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ImageIO.write(fixed, "jpg", new File("C:\\Users\\Daniel\\code\\output.jpg"));
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

    public static void main(String[] args) {
        //TODO temporary main
        new HoughTransform();
    }
}
