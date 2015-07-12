package image;

/**
 * Created by Daniel on 2015-07-12.
 */
public class Circle {
    private Coordinates coordinates;
    private double radius;

    public Circle(Coordinates coordinates, double radius) {
        this.coordinates = coordinates;
        this.radius = radius;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Circle circle = (Circle) o;

        if (Double.compare(circle.radius, radius) != 0) return false;
        if (coordinates != null ? !coordinates.equals(circle.coordinates) : circle.coordinates != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = coordinates != null ? coordinates.hashCode() : 0;
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "coordinates=" + coordinates +
                ", radius=" + radius +
                '}';
    }
}
