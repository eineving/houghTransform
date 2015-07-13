/**
 * Created by Daniel on 2015-07-12.
 */
public class AlphaPixel extends Pixel {
    int alpha;

    public AlphaPixel(int red, int green, int blue, int alpha) {
        super(red, green, blue);
        this.alpha = alpha;
    }

    public int getAlpha() {
        return alpha;
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
        return "AlphaPixel{" +
                "alpha=" + alpha +
                ", red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}
