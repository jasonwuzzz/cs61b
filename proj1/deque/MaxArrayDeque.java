package deque;

import java.util.Comparator;


public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator<T> cmp;

    /** Creates a MaxArrayDeque with the given Comparator. */
    public MaxArrayDeque(Comparator<T> c) {
        cmp = c;
    }

    /** Returns the maximum element in the deque as governed by the previously
     *  given Comparator. If the MaxArrayDeque is empty, simply return null.
     */
    public T max() {
        return max(cmp);
    }

    /** Returns the maximum element in the deque as governed by the parameter
     * Comparator c. If the MaxArrayDeque is empty, simply return null. */
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        } else {
            T largest = get(0);
            for (int i = 0; i < size(); i += 1) {
                if (c.compare(largest, get(i)) < 0) {
                    largest = get(i);
                }
            }
            return largest;
        }
    }
}
