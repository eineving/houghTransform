import image.Image;
import image.Line;
import image.PixelValueChanger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
            image = ImageIO.read(new File("C:\\Users\\danie\\code\\houghTransform\\src\\main\\resources\\References\\20150613_111004302_iOS.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image greyscale = new Image(image);

        greyscale.applyPixelChange(new PixelValueChanger() {
            @Override
            public byte[] changePixelValues(byte blue, byte green, byte red) {
                //Convert to greyscale by average
                byte avg;
                int sum = 0;
                sum += unsignedToInt(blue);
                sum += unsignedToInt(green);
                sum += unsignedToInt(red);

                avg = (byte) (sum / 3);

                //System.out.println("avg: " +  avg);
                byte[] values = new byte[3];

                values[0] = avg;
                values[1] = avg;
                values[2] = avg;

                return values;
            }
        });
        BufferedImage fixedGreyscale = greyscale.getBufferedImage();

        try {
            ImageIO.write(fixedGreyscale, "jpg", new File("C:\\Users\\danie\\code\\greyscale.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image binary = new Image(image);
        binary.applyPixelChange(new PixelValueChanger() {
            @Override
            public byte[] changePixelValues(byte blue, byte green, byte red) {
                //Creating binary image
                byte[] values = new byte[3];
                int sum = unsignedToInt(blue);
                sum+=unsignedToInt(green);
                sum+=unsignedToInt(red);


                byte value=0;

                //TODO remove hard code 600
                if(sum>600){
                    value= (byte) 0xFF;
                }

                for(int i = 0; i<3; i++){
                    values[i]=value;
                }
                return values;
            }
        });

        binary.createBinaryOutline();
        try {
            ImageIO.write(binary.getBufferedImage(), "jpg", new File("C:\\Users\\danie\\code\\noGreen.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Line> findLines(BufferedImage image) {

        //TODO implement
        return null;

    }

    public static void main(String[] args) {

        //TODO temporary main
        System.out.println("Starting application ...");
        long start = System.currentTimeMillis();
        new HoughTransform();
        System.out.println("Finished!");
        System.out.println("Runtime: " + (System.currentTimeMillis() - start) + "ms");
    }

    private int unsignedToInt(byte b) {
        return b & 0xFF;
    }
}
