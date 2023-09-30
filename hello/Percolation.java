/* *****************************************************************************
 *  Name:              Shih-Che, Lai
 *  Coursera User ID:  123456
 *  Last modified:     2023/9/29
 **************************************************************************** */

// Reference and learn from: https://medium.com/nerd-for-tech/monte-carlo-simulation-estimate-percolation-threshold-in-java-7f253641bbdd

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF gridMap;
    private boolean[] grid;
    private final int size;
    private final int top;
    private final int bottom;
    private int openCellCount;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n is smaller or equals to 0, invaild init.");
        }

        gridMap = new WeightedQuickUnionUF(n * n + 2); // Extra 2 for top and bottom
        grid = new boolean[n * n + 2];

        this.size = n; // size * size is the size of the grid
        top = 0;
        bottom = n * n + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkBound(row, col); // Check if exceeded boundary
        int currCellIndex = indexOf(row, col); // Get current index of the cell
        grid[currCellIndex] = true; // Open it
        this.openCellCount++; // Increase open cell count
        // Union the cell with the top
        if (row == 1) {
            gridMap.union(currCellIndex, top);
        }
        else if (row == this.size) { // Union the cell with the bottom
            gridMap.union(currCellIndex, bottom);
        }

        // Check upper cell of the current cell
        if (row > 1 && isOpen(row - 1, col)) {
            gridMap.union(currCellIndex, currCellIndex - size);
        }

        // Check down cell of the current cell (row < this.size bcz so that
        // it has a cell down below current cell)
        if (row < this.size && isOpen(row + 1, col)) {
            gridMap.union(currCellIndex, currCellIndex + size);
        }

        // Check left cell of the current cell
        if (col > 1 && isOpen(row, col - 1)) {
            gridMap.union(currCellIndex, currCellIndex - 1);
        }

        // Check right cell of the current cell
        if (col < this.size && isOpen(row, col + 1)) {
            gridMap.union(currCellIndex, currCellIndex + 1);
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkBound(row, col); // Check if exceeded boundary
        return grid[indexOf(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkBound(row, col); // Check if exceeded boundary
        // Check the cell is open or not
        if (!isOpen(row, col)) {
            return false;
        }

        // Check their canonical element
        return gridMap.find(indexOf(row, col)) == gridMap.find(top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openCellCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return gridMap.find(top) == gridMap.find(bottom);
    }

    private void checkBound(int row, int col) {
        if (row < 1 || row > this.size || col < 1 || col > this.size) {
            throw new IllegalArgumentException("Out of bound");
        }
    }

    // Convert 2d index (from 1, 1) to 1d index (from 0)
    private int indexOf(int row, int col) {
        checkBound(row, col);
        return ((row - 1) * this.size) + col;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
