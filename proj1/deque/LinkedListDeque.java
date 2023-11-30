package deque;

public class LinkedListDeque<T> {
    /** Nested Class */
    private class TNode{
        public T item;
        public TNode prev;
        public TNode next;

        public TNode(T i, TNode p, TNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /** Instance Variables */
    int size;
    TNode sentinel;

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        size = 0;
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    ////////////////////////
    //         APIs       //
    ////////////////////////
    // 1. Add, Remove, and size operations take CONSTANT time.
    // 2. Iterating over the LinkedListDeque using a for-each
    // loop should take time proportional to the number of items.
    // 3. Do not maintain references to items that are no longer in the deque.

    /** Adds an item of type T to the back of the deque.
     * Assume that item is never null. */
    public void addFirst(T item) {
        TNode front = new TNode(item, sentinel, sentinel.next);
        sentinel.next.prev = front;
        sentinel.next = front;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque.
     * Assume that item is never null. */
    public void addLast(T item) {
       TNode back = new TNode(item, sentinel.prev, sentinel);
       sentinel.prev.next = back;
       sentinel.prev = back;
       size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.*/
    public void printDeque() {
        TNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
            T front = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            return front;
        }
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
            T back = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            return back;
        }
    }

    /** Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (isEmpty() || index >= this.size()) {
            return null;
        } else {
            TNode p = sentinel;
            for (int i = 0; i <= index; i += 1) {
                p = p.next;
            }
            return p.item;
        }
    }

    /** Return the item at the given index of a Naked TNode Linked List. */
    private T getHelper(TNode p, int index) {
       if (index > 0) {
           return p.item;
       } else {
           return getHelper(p.next, index - 1);
       }
    }

    public T getRecursive(int index) {
        if (isEmpty() || index > this.size()) {
            return null;
        } else {
            return getHelper(sentinel.next, index);
        }
    }

    /** The Deque objects we’ll make are iterable (i.e. Iterable<T>)
     * so we must provide this method to return an iterator.
    public Iterator<T> iterator() {

    }
    */

    /** Returns whether or not the parameter o is equal to the Deque.
     o is considered equal if it is a Deque and if it contains the same contents
     (as governed by the generic T’s equals method) in the same order.
    public boolean equals(Object o) {
        if (o instanceof LinkedListDeque) {
            if (o.size() == this.size()) {
                TNode a = o.sentinel.next;
                TNode b = this.sentinel.next;
                for (int i = 0; i < this.size(); i += 1) {
                    if (a.item != b.item) {
                        return false;
                    }
                }
                return true;
            }

        }
        return false;
    }
    */
}
