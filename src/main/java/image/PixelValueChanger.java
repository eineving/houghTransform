package image;

/**
 * Class to apply a decided change to designated pixel
 */
public interface PixelValueChanger {

    /**
     * Changes the pixel values to designated value
     *
     * @param blue blue byte value
     * @param green green byte value
     * @param red red byte value
     * @return byte array of the BGR value
     */
    public byte[] changePixelValues(byte blue, byte green, byte red);
}
