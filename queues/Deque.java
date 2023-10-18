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
        Node newTail = new Node();
        newTail.item = item;
        if (n >= 1) {
            tail.next = newTail; // Add new item and append
            newTail.previous = tail; // Link original tail to newTail's previous node
            tail = tail.next; // Swap the tail node, now the tail is the new tail
            tail.next = null; // Next node of the tail is null
        }
        else {
            tail = newTail;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = head.item; // Prepare return item
        head = head.next; // Point to the next node of the head
        if (n >= 2) head.previous = null; // Unlink the link to the original head node
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = tail.item; // Prepare return item
        tail = tail.previous; // Previous node as the new tail
        tail.next = null; // Unlink the link to the original tail node
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
            return head.item; // FIXME
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> q = new Deque<>();
        q.addFirst(1);
        q.addFirst(2);
        q.removeFirst();
        /* FIXME: removeLast might cause the problem, when there is
         *  only 1 element last, should combine head and tail
         * */
        q.addLast(1);
        System.out.println(q.size());
    }

    // Linked-List helper inner class

}
