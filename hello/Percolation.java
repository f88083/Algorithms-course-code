/* *****************************************************************************
 *  Name:              Shih-Che, Lai
 *  Coursera User ID:  123456
 *  Last modified:     2023/9/29
 **************************************************************************** */

/* Ex. Use all the cells
    1 1 1 1
    1 1 1 1
    1 1 1 1
    1 1 1 1
 */

/*
-1: Blocked
0: Empty open
1: Full open
 */

public class Percolation {
    int[][] grid;
    int size;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n is smaller or equals to 0, invaild init.");
        }
        this.grid = new int[n][n];

        // Fill the grid with blocked sites
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = -1;
            }
        }

        this.size = n;
    }

    private void checkBound(int row, int col) {
        if (row - 1 > size || col - 1 > size) {
            throw new IllegalArgumentException("Out of bound");
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkBound(row, col); // Check if exceeded boundary
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkBound(row, col); // Check if exceeded boundary
        return grid[row - 1][col - 1] != -1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkBound(row, col); // Check if exceeded boundary
        return true;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return true;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation test = new Percolation(3);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.println(test.grid[row][col]);
            }
        }
    }
}
