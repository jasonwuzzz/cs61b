package deque;

import java.util.Iterator;


public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
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

    /** RULES
     * 1. add and remove must take constant time, except during resizing operations.
     * 2. get and size must take constant time.
     * 3. The starting size of your array should be 8.
     * 4. The amount of memory that your program uses at any given time must be
     * proportional to the number of items.
     *      - For arrays of length 16 or more, your usage factor should always be at
     *        least 25%.
     *      - For smaller arrays, your usage factor can be arbitrarily low.
     */

    public ArrayDeque() {
        items = (T[]) new Object[8];
        front = 0;
        back = 1;
        size = 0;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int i = (front + 1) % items.length;
        for (int j = 1; j < size + 1; j += 1) {
            a[j] = items[i];
            i = (i + 1) % items.length;
        }
        front = 0;
        back = size + 1;
        items = a;
    }

    @Override
    public void addFirst(T item) {
        if (size() == items.length) {
            resize(items.length * 2);
        }

        items[front] = item;
        front = (front - 1 + items.length) % items.length;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size() == items.length) {
            resize(items.length * 2);
        }

        items[back] = item;
        back = (back + 1) % items.length;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = (front + 1) % items.length; i < back; i = (i + 1) % items.length) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        /**
         * Before performing a remove operation that will bring the number
         * of elements in the array under 25% the length of the array, you
         * should resize the size of the array down.
         */
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

    @Override
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

    @Override
    public T get(int index) {
        if (index < size() && index >= 0) {
            // Transform user-perspective index to real array implementation index.
            int transIndex = (front + 1 + index) % items.length;
            return items[transIndex];
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos = front + 1;

        @Override
        public boolean hasNext() {
            return pos < back;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T result = items[pos];
                pos = (pos + 1) % items.length;
                return result;
            }
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (other.size() != size()) {
            return false;
        }
        for (int i = 0; i < size(); i += 1) {
            if (!other.get(i).equals(get(i))) {
                return false;
            }
        }
        return true;
    }
}
