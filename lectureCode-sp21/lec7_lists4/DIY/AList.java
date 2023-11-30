/** Array based list.
 *  @author Josh Hug
 */

public class AList {
    public int[] aList;
    public int last;

    /** Creates an empty list. */
    public AList() {
        aList = new int[2000000];
        last = 0;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        aList[last] = x;
        last += 1;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return aList[last - 1];
    }

    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return aList[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return last;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public int removeLast() {
        int back = getLast();
        last = last - 1;
        return back;
    }
} 