/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/*
 * Basic codes of Array were taken from
 * https://algs4.cs.princeton.edu/13stacks/ResizingArrayStack.java.html
 * By Robert Sedgewick and Kevin Wayne
 * */


public class RandomizedQueue<Item> implements Iterable<Item> {
    // initial capacity of underlying resizing array
    private static final int INIT_CAPACITY = 8;
    private int n;          // size of this queue
    private Item[] a;         // array of items

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        // Array is full
        if (n == a.length) {
            resize(a.length * 2); // Double the size
        }

        // Add the item
        a[n] = item;
        ++n;
    }

    // remove and return a random item
    public Item dequeue() {
        if (n == 0) {
            throw new java.util.NoSuchElementException();
        }
        // Random index
        int rand = StdRandom.uniformInt(0, n);
        // Prepare return item
        Item ret = a[rand];
        // fill the space with the last item
        a[rand] = a[n - 1];
        // Remove the last item
        a[n - 1] = null;
        // Shrink the size
        --n;
        return ret;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (n == 0) {
            throw new java.util.NoSuchElementException();
        }
        return a[StdRandom.uniformInt(0, n)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;

        public RandomizedQueueIterator() {
            i = 0; // Start from index 1
            // Shuffle the array, linear time
            StdRandom.shuffle(a, 0, n);
        }

        public boolean hasNext() {
            return i != n;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return a[i++]; // Return next and increase i
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = a[i];
        }
        a = copy;

        // alternative implementation
        // a = java.util.Arrays.copyOf(a, capacity);
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Test enqueue
        RandomizedQueue<Integer> q = new RandomizedQueue<>();

        System.out.println("q empty? " + q.isEmpty());

        for (int i = 0; i < 8; i++) {
            q.enqueue(i);
        }

        // for (int i = 0; i < 7; i++) {
        //     System.out.println("Removed: " + q.dequeue());
        // }

        System.out.println("q size? " + q.size());
        System.out.println("---------");
        // Print the items
        for (int a : q) {
            System.out.println(a);
        }
        System.out.println();
        for (int b : q) {
            System.out.println(b);
        }


    }

}
