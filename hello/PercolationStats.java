/* *****************************************************************************
 *  Name:              Shih-Che, Lai
 *  Coursera User ID:  123456
 *  Last modified:     2023/9/30
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private double[] fractionTotal; // sum of (open sites / total sites)
    private final int trials;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Illegal n or trials");
        }

        this.fractionTotal = new double[trials];
        this.trials = trials;

        Percolation zone;

        // zone = new Percolation(2); // Init. a grid zone for the experiment

        // Start the experiment
        for (int i = 0; i < trials; i++) {
            zone = new Percolation(n); // Init. a grid zone for the experiment
            // Keep until percolates
            while (!zone.percolates()) {
                int randRow = StdRandom.uniformInt(1, n + 1);
                int randCol = StdRandom.uniformInt(1, n + 1);
                if (!zone.isOpen(randRow, randCol)) {
                    zone.open(randRow, randCol);
                }
            }
            // When percolates
            fractionTotal[i] = (double) zone.numberOfOpenSites() / (n * n);
        } // End of the experiment

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractionTotal);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractionTotal);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        // Init. experiment
        PercolationStats exp = new PercolationStats(n, trials);

        String confidence = "[" + exp.confidenceLo() + ", " + exp.confidenceHi() + "]";
        StdOut.println("mean                    = " + exp.mean());
        StdOut.println("stddev                  = " + exp.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}
