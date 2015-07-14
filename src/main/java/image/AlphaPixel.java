package image;

/**
 * Created by Daniel on 2015-07-12.
 */
public class AlphaPixel extends Pixel {
    byte alpha;

    public AlphaPixel(byte red, byte green, byte blue, byte alpha) {
        super(red, green, blue);
        this.alpha = alpha;
    }

    public byte getAlpha() {
        return alpha;
    }

    public void setAlpha(byte alpha) {
        this.alpha = alpha;
    }

    @Override
    public int getRGB() {
        int value = super.getRGB();
        value += (((int) alpha & 0xff) << 24 );
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AlphaPixel that = (AlphaPixel) o;

        if (alpha != that.alpha) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + alpha;
        return result;
    }

    @Override
    public String toString() {
        return "image.AlphaPixel{" +
                "alpha=" + alpha +
                ", red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}
