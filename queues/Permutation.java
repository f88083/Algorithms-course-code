/* *****************************************************************************
 *  Name:              Shih-Che, Lai
 *  Coursera User ID:  123456
 *  Last modified:     2023/10/7
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        // Read k
        int k = Integer.parseInt(args[0]);
        // Queue for storing inputs
        RandomizedQueue<String> q = new RandomizedQueue<>();
        // Read in data
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readString());
        }
        // Print the data
        for (int i = 0; i < k; i++) {
            StdOut.println(q.dequeue());
        }
    }
}
