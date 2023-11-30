package deque;

public class ArrayDeque<T> {
    private T[] items;
    /** Keeps track of deque's front, the index of the first item is
     * (front + 1) % items.length. Front is the position to add first.
     */
    private int front;
    /** Keeps track of deque's back, the index of the last item is
     * (back - 1 + items.length) % items.length. back is the position to add last.
     */
    private int back;
    private int size;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        front = 0;
        back = 1;
        size = 0;
    }

    ////////////////////////
    //         APIs       //
    ////////////////////////

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int  i = (front + 1) % items.length, j = 1; j < size + 1; i = (i + 1) % items.length, j += 1) {
            a[j] = items[i];
        }
        front = 0;
        back = size + 1;
        items = a;
    }

    /** Adds an item of type T to the back of the deque.
     * Assume that item is never null. */
    public void addFirst(T item) {
        if (size() == items.length) {
            resize(items.length * 2);
        }

        items[front] = item;
        front = (front - 1 + items.length) % items.length;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque.
     * Assume that item is never null. */
    public void addLast(T item) {
        if (size() == items.length) {
            resize(items.length * 2);
        }

        items[back] = item;
        back = (back + 1) % items.length;
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size() == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.*/
    public void printDeque() {
        for (int i = (front + 1) % items.length; i < back; i = (i + 1) % items.length) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    /** For arrays of length 16 or more, your usage factor should always be at least 25%.
     * This means that before performing a remove operation that will bring the number of
     * elements in the array under 25% the length of the array, you should resize the size
     * of the array down. For smaller arrays, your usage factor can be arbitrarily low. */

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (size() >= 16 && size * 1.0 / items.length <= 0.25) {
            resize((int) (items.length * 0.5));
        }
        if (isEmpty()) {
            return null;
        }
        int fPos = (front + 1) % items.length;
        T item = items[fPos];
        items[fPos] = null;
        front = fPos;
        size -= 1;
        return item;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (size >= 16 && size * 1.0 / items.length <= 0.25) {
            resize((int) (items.length * 0.5));
        }
        if (isEmpty()) {
            return null;
        }
        int bPos = (back - 1 + items.length) % items.length;
        T item = items[bPos];
        items[bPos] = null;
        back = bPos;
        size -= 1;
        return item;
    }

    /** Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index < size() && index >= 0) {
            // Transform user-perspective index to real array implementation index.
            int transIndex = (front + 1 + index) % items.length;
            return items[transIndex];
        }
        return null;
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
         return false;
     }
     */
}
