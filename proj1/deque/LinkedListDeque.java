package deque;

import java.util.Iterator;


public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class TNode {
        private T item;
        private TNode prev;
        private TNode next;

        TNode(T i, TNode p, TNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private int size;
    private TNode sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /** RULES
     * 1. Add, Remove, and size operations take CONSTANT time, not
     * involving any loop or recursion.
     * 2. get must use iteration, not recursion.
     * 3. Iterating over the LinkedListDeque using a for-each loop
     * should take time proportional to the number of items.
     * 4. Do not maintain references to items that are no longer in the deque.
     */

    @Override
    public void addFirst(T item) {
        TNode front = new TNode(item, sentinel, sentinel.next);
        sentinel.next.prev = front;
        sentinel.next = front;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        TNode back = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = back;
        sentinel.prev = back;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        TNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
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

    @Override
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

    @Override
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

    /** Returns the item at given index, beginning from p. */
    private T getHelper(TNode p, int index) {
        if (index == 0) {
            return p.item;
        } else {
            return getHelper(p.next, index - 1);
        }
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (isEmpty() || index > this.size()) {
            return null;
        } else {
            return getHelper(sentinel.next, index);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < size();
        }

        @Override
        public T next() {
            if (hasNext()) {
                T result = get(pos);
                pos += 1;
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
        if (other.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size(); i += 1) {
            if (!get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }
}
