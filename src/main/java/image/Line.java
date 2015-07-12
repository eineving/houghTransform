package image;

/**
 * Created by Daniel on 2015-07-12.
 */
public class Line {
    private Coordinates beginning, end;

    public Line(Coordinates beginning, Coordinates end) {
        this.beginning = beginning;
        this.end = end;
    }

    public Coordinates getBeginning() {
        return beginning;
    }

    public Coordinates getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (beginning != null ? !beginning.equals(line.beginning) : line.beginning != null) return false;
        if (end != null ? !end.equals(line.end) : line.end != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = beginning != null ? beginning.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Line{" +
                "beginning=" + beginning +
                ", end=" + end +
                '}';
    }
}
