package image;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;

/**
 * Image
 * <p>
 * columns and rows are have 1-indexation
 */
public class Image {
    private byte data[];
    private int width, height;
    private ArrayList<PixelArea> areas = new ArrayList<PixelArea>();

    public Image(BufferedImage bufferedImage) {
        this.data = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        this.width = bufferedImage.getWidth();
        this.height = bufferedImage.getHeight();
    }

    public Image(byte[] data, boolean hasAlphaRaster, int height, int width) {
        this.data = data;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<PixelArea> getBinaryOutline() {
        if (areas.size() == 0) {
            findBinaryOutline();
        }
        return areas;
    }

    public void applyBinaryOutline() {

    }

    private void findBinaryOutline(){
        //X-Y
        boolean[][] visited = new boolean[width][height];

        for (int x = 1; x < width; x++) {
            for (int y = 1; y < height; y++) {



            }
        }

    }


    private void createBinaryOutline() {
        ArrayList<Coordinates> border = new ArrayList<Coordinates>();
        for (int x = 1; x < width; x++) {
            for (int y = 1; y < height; y++) {
                if (whitePixel(getPixel(x, y))) {
                    if (blackNeighbour(x, y)) {
                        border.add(new Coordinates(x, y));
                    }
                }
            }
        }
        System.out.println("BorderSize: " + border.size());

        for (int i = 0; i < data.length; i++) {
            data[i] = 0;
        }

        for (Coordinates coordinate : border) {
            setPixel(coordinate.getX(), coordinate.getY(), (byte) 0xFF, (byte) 0xFF, (byte) 0xFF);
        }
    }

    private boolean blackNeighbour(int x, int y) {
        boolean anyBlack = false;
        for (int i = 0; i < 3; i++) {
            if (!whiteOrNonExistingPixel(x - 1 + i, y - 1)) {
                return true;
            }
            if (!whiteOrNonExistingPixel(x - 1 + i, y + 1)) {
                return true;
            }
        }

        if (!whiteOrNonExistingPixel(x - 1, y)) {
            return true;
        }
        if (!whiteOrNonExistingPixel(x + 1, y)) {
            return true;
        }
        return false;

    }

    private boolean whiteOrNonExistingPixel(int x, int y) {
        if (x < 1 || x >= width || y < 1 || y >= height) {
            return true;
        }

        return whitePixel(getPixel(x, y));
    }

    private boolean whitePixel(byte[] values) {
        for (byte value : values) {
            if ((value & 0xFF) != 255) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the pixel values as a byte array as BGR
     *
     * @param x column
     * @param y row
     * @return byte array with BGR value
     */
    public byte[] getPixel(int x, int y) {
        int index = 0;
        int pixelSize = 3;

        if (y > 1) {
            index += y * width * pixelSize;
        }
        index += x * pixelSize;
        byte[] values = new byte[pixelSize];
        System.arraycopy(data, index, values, 0, pixelSize);
        return values;
    }

    public void setPixel(int x, int y, byte blue, byte green, byte red) {
        int index = 0;

        int pixelSize = 3;
        int alphaTick = 0;
        if (y > 1) {
            index += y * width * pixelSize;
        }
        index += x * pixelSize;

        //System.out.println("Index: " + index);
        data[index + alphaTick] = blue;
        data[index + alphaTick + 1] = green;
        data[index + alphaTick + 2] = red;
    }

    public byte getPixelValue(int x, int y, PixelDataType type) {
        byte[] values = getPixel(x, y);

        switch (type) {
            case RED:
                return values[2];
            case GREEN:
                return values[1];
            case BLUE:
                return values[0];
        }
        //Should not happen
        return -1;
    }

    public BufferedImage getBufferedImage() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        byte[] imgData = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

        System.arraycopy(data, 0, imgData, 0, data.length);
        return image;
    }

    public void applyPixelChange(PixelValueChanger changer) {
        for (int i = 0; i < data.length; i += 3) {
            byte[] newData = changer.changePixelValues(data[i], data[i + 1], data[i + 2]);
            System.arraycopy(newData, 0, data, i, 3);
        }


    }

    public enum PixelDataType {
        RED, GREEN, BLUE, ALPHA
    }
}
