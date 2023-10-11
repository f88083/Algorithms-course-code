/* *****************************************************************************
 *  Name:              Shih-Che, Lai
 *  Coursera User ID:  123456
 *  Last modified:     2023/10/7
 **************************************************************************** */

/*
 * Your submission may not call library functions except those in StdIn, StdOut,
 * StdRandom, java.lang, java.util.Iterator, and java.util.NoSuchElementException
 * In particular, do not use either java.util.LinkedList or java.util.ArrayList.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Basic codes of Linked-List were taken from
 * https://algs4.cs.princeton.edu/13stacks/LinkedStack.java.html
 * By Robert Sedgewick and Kevin Wayne
 * */

public class Deque<Item> implements Iterable<Item> {
    private Item item;
    private int n;          // size of the deque
    private Node head;     // top of deque
    private Node tail;       // end of deque


    // construct an empty deque
    public Deque() {
        head = null; // Init. head to null
        tail = null; // Init. tail to null
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        /* Swap head from old to new*/
        Node oldHead = head;
        head = new Node();
        head.item = item;
        head.next = oldHead;
        n++; // Increase size
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node newTail = new Node();
        newTail.item = item;
        tail.next = newTail; // Add new item and append
        tail = tail.next; // Swap the tail node, now the tail is the new tail
        tail.next = null; // Next node of the tail is null
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        public boolean hasNext() {
            // TODO: Implementation
            return true;
        }

        public Item next() {
            // TODO: Implementation
            if (!hasNext()) throw new NoSuchElementException();
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

    // Linked-List helper inner class
    private class Node {
        private Item item;
        private Node next;
    }
}
