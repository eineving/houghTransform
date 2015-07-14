package image;

/**
 * Created by Daniel on 2015-07-12.
 */
public class Pixel {
    byte red, green, blue;

    public Pixel(byte red, byte green, byte blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public byte getRed() {
        return red;
    }

    public byte getGreen() {
        return green;
    }

    public byte getBlue() {
        return blue;
    }

    public void setRed(byte red) {
        this.red = red;
    }

    public void setGreen(byte green) {
        this.green = green;
    }

    public void setBlue(byte blue) {
        this.blue = blue;
    }

    public int getRGB(){

        int value= ((int) blue & 0xff);
        value += (((int) green & 0xff) << 8);
        value += (((int) red & 0xff) << 16);
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pixel pixel = (Pixel) o;

        if (blue != pixel.blue) return false;
        if (green != pixel.green) return false;
        if (red != pixel.red) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = red;
        result = 31 * result + green;
        result = 31 * result + blue;
        return result;
    }

    @Override
    public String toString() {
        return "image.Pixel{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}
