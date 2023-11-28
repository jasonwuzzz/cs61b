package lec5_lists2;

import org.junit.Test;
import static org.junit.Assert.*;

public class SLListTest {

    /** Test the new constructor.*/
    @Test
    public void testConstructor() {
        SLList L = new SLList(new int[]{1, 2, 3, 4});
        assertEquals(L.getFirst(), 1);
        assertEquals(L.size(), 4);
    }

    /** Test the deleteFirst method. */
    @Test
    public void testDeleteFirst() {
        SLList L = new SLList(new int[]{1, 2, 3, 4});
        L.deleteFirst();
        assertEquals(L.getFirst(), 2);
    }

}
