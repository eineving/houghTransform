package image;

import java.util.*;

/**
 * Class to represent an collection of adjacent pixels of the same values.
 */
public class PixelArea {
    //Y is the key
    private Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
    private int topPixel, bottomPixel, rightMostPixel, leftMostPixel;

    public PixelArea(List<Coordinates> coordinates) {
        for (Coordinates coor : coordinates) {
            if (topPixel == 0 || topPixel > coor.getY()) {
                topPixel = coor.getY();
            }
            if (bottomPixel < coor.getY()) {
                bottomPixel = coor.getY();
            }
            if (leftMostPixel == 0 || leftMostPixel > coor.getX()) {
                leftMostPixel = coor.getX();
            }
            if (rightMostPixel < coor.getX()) {
                rightMostPixel = coor.getX();
            }


            int y = coor.getY();
            if (map.containsKey(y)) {
                List<Integer> xCor = map.get(y);

            } else {
                List<Integer> xCor = new ArrayList<Integer>();
                xCor.add(coor.getX());
                xCor.sort(new IntegerCompare());
            }
        }
    }

    public List<Coordinates> getBorder() {
        List<Coordinates> border = new ArrayList<Coordinates>();
        for (int y = topPixel; y <= bottomPixel; y++) {
            for(Integer x : map.get(y)){
                border.add(new Coordinates(x.intValue(), y));
            }
        }
        return border;
    }


    private class IntegerCompare implements Comparator {

        public int compare(Object o1, Object o2) {
            Integer a = (Integer) o1;
            Integer b = (Integer) o2;

            if (a > b) {
                return 1;
            } else if (a < b) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
