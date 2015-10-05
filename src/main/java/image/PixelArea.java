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
            for (Integer x : map.get(y)) {
                border.add(new Coordinates(x.intValue(), y));
            }
        }
        return border;
    }

    public boolean inArea(int x, int y) {
        if (!map.containsKey(y)) {
            return false;
        }

        int amountLeftOfPoint = 0;
        int amountRightOfPoint = 0;

        for (Integer i : map.get(y)) {
            if (i == x) {
                //Point is on the border and therefore in the object
                return true;
            } else if (i < x) {
                amountLeftOfPoint++;
            } else {
                amountRightOfPoint++;
            }
        }

        //TODO Fix this (issue #1)
        return amountLeftOfPoint % 2 == 1 && amountRightOfPoint % 2 == 1;
    }

    public int getTopPixel() {
        return topPixel;
    }

    public int getBottomPixel() {
        return bottomPixel;
    }

    public int getRightMostPixel() {
        return rightMostPixel;
    }

    public int getLeftMostPixel() {
        return leftMostPixel;
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
