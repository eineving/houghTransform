package image;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Image
 */
public class Image {
    private byte data[];
    private boolean alphaRaster;
    private int width, height;


    public Image(BufferedImage bufferedImage) {
        data = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        alphaRaster = bufferedImage.getAlphaRaster() != null;
        this.width = bufferedImage.getWidth();
        this.height = bufferedImage.getHeight();

    }

    public Image(byte[] data, boolean hasAlphaRaster, int height, int width) {
        this.data = data;
        this.alphaRaster = hasAlphaRaster;
        this.width = width;
        this.height = height;
    }

    public boolean hasAlphaRaster() {
        return alphaRaster;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
