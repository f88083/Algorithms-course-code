/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class BruteCollinearPoints {
    private int numLineSeg; // Number of line segments

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        // Check item null and duplicates
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
            for (int j = i + 1; j < points.length; j++) {
                if (points[j] == null) throw new IllegalArgumentException();
                // Check duplicates
                if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException();
            }
        }

        numLineSeg = 0;
    }

    // the number of line segments
    public int numberOfSegments() {
        return numLineSeg;
    }

    // the line segments
    public LineSegment[] segments() {
        // TODO
        return new LineSegment[1];
    }

    public static void main(String[] args) {

    }
}
