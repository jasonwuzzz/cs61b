package lec5_lists2;

import org.junit.Test;
import static org.junit.Assert.*;

public class IntListTest {
    @Test
    public void testAddFirst1() {
        IntList L = IntList.of(1, 2, 3);
        L.addFrist(0);
        assertEquals(L.toString(), "0 -> 1 -> 2 -> 3");
    }

    @Test
    public void testAddAdjacent1() {
        IntList L = IntList.of(1, 1, 2, 3);
        L.addAdjacent();
        assertEquals(L.toString(), "4 -> 3");
    }

    @Test
    public void testAddAdjacent2() {
        IntList L = IntList.of(1, 1, 2, 4, 8, 3);
        L.addAdjacent();
        assertEquals(L.toString(), "16 -> 3");
    }

    @Test
    public void testSquareList1() {
       IntList L = IntList.of(1, 2);
       L.squareList(5);
       assertEquals(L.toString(), "1 -> 1 -> 2 -> 4 -> 5");
    }

    @Test
    public void testSquareList2() {
        IntList L = IntList.of(1, 1, 2, 4, 5);
        L.squareList(7);
        assertEquals(L.toString(), "1 -> 1 -> 1 -> 1 -> 2 -> 4 -> 4 -> 16 -> 5 -> 25 -> 7");
    }

    @Test
    public void testSquareList3() {
        IntList L = IntList.of(2);
        L.squareList(5);
        assertEquals(L.toString(), "2 -> 4 -> 5");
    }
}
