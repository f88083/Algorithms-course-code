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

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Basic codes of Linked-List were taken from
 * https://algs4.cs.princeton.edu/13stacks/LinkedStack.java.html
 * By Robert Sedgewick and Kevin Wayne
 * */

public class Deque<Item> implements Iterable<Item> {
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
        // New node with the item
        Node newNode = new Node();
        newNode.item = item;
        if (n == 0) {
            // Point to the same single node
            head = newNode;
            tail = newNode;
        }
        else if (n == 1) {
            head = newNode; // Add and point to the new item
            head.next = tail; // Link the head to the tail
            // Tail's pointer does not change
            tail.previous = head; // Link the tail to the head
        }
        else { // When n >= 2
            Node prevHeadNode = head; // Previous head node
            head = newNode; // Add and point to the new item
            head.next = prevHeadNode; // Link to the previous head
            prevHeadNode.previous = head; // Link to the new head
        }
        ++n;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node newNode = new Node();
        newNode.item = item;
        if (n == 0) {
            // Point to the same single node
            head = newNode;
            tail = newNode;
        }
        else if (n == 1) {
            tail = newNode; // Add and point to the new item
            head.next = tail; // Link the head to the tail
            // head's pointer does not change
            tail.previous = head; // Link the tail to the head
        }
        else { // When n >= 2
            Node prevTailNode = tail; // Previous tail node
            tail = newNode; // Add and point to the new item
            tail.previous = prevTailNode; // Link to the previous tail
            prevTailNode.next = tail; // Link to the new tail
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        Item headItem = head.item;

        if (n == 1) {
            head = null;
            tail = null;
        }
        else if (n == 2) {
            head = head.next;
            head.next = null;
            tail.previous = null;
        }
        else {
            head = head.next; // Move to the new position
            head.previous = null; // Remove previous node
        }
        --n;
        return headItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item tailItem = tail.item; // Prepare return item
        if (n == 1) {
            head = null;
            tail = null;
        }
        else if (n == 2) {
            head.next = null; // Remove next item
            tail = tail.previous; // Point to the previous node
        }
        else {
            tail = tail.previous;
            tail.next = null;
        }
        --n;
        return tailItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node currNode = head;

        public boolean hasNext() {
            return currNode != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = currNode.item;
            currNode = currNode.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> q = new Deque<>();
        /* Add First */

        q.addLast(4);
        q.addLast(1);
        q.addFirst(2);
        q.addFirst(3);
        q.removeFirst();
        q.removeLast();
        q.removeFirst();
        q.removeLast();
        q.addFirst(11);
        StdOut.println(q.size());
        if (!q.isEmpty()) {
            StdOut.println("head: " + q.head.item);
            StdOut.println("tail: " + q.tail.item);
            StdOut.println("is Empty: " + q.isEmpty());
        }
        StdOut.println("----------------------PRINTING----------------------");
        // Iterate
        for (int num : q) {
            StdOut.println(num);
        }

    }

    // Linked-List helper inner class
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }
}
