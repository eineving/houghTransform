import com.sun.javafx.iio.ios.IosImageLoader;
import image.Line;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Daniel on 2015-07-12.
 */
public class HoughTransform {

    public HoughTransform(){
        BufferedImage image = null;
        try{
            image = ImageIO.read(new File("C:\\Users\\Daniel\\code\\houghTransform\\src\\main\\resources\\References\\20150613_110956221_iOS.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage fixed = noGreenImage(getPixelMatrix(image));

        try {
            ImageIO.write(fixed, "jpg",new File("C:\\Users\\Daniel\\code\\output.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
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

    private BufferedImage noGreenImage(Pixel[][] raster){
        BufferedImage image = new BufferedImage(raster.length, raster[0].length, BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i<raster.length; i++){
            for(int j = 0; j<raster[0].length; j++) {
                raster[i][j].setGreen((byte) 0);
                image.setRGB(i,j, raster[i][j].getRGB());
            }
        }
        return image;
    }

    public static void main(String [] args){
        //TODO temporary main
        new HoughTransform();
    }
}
