package deque;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {
        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    public void randomizedComparisonTest() {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        ArrayDeque<Integer> M = new ArrayDeque<>();

        int N = 1000000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            if (operationNumber == 0) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                L.addFirst(randVal);
                M.addFirst(randVal);
                assertEquals(L.getRecursive(0), M.get(0));
            } else if (operationNumber == 1) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                M.addLast(randVal);
                assertEquals(L.get(L.size() - 1), M.get(M.size() - 1));
            } else if (operationNumber == 2) {
                // removeFirst
                assertEquals(L.removeFirst(), M.removeFirst());
            } else if (operationNumber == 3) {
                // removeLast
                assertEquals(L.removeLast(), M.removeLast());
            } else if (operationNumber == 4) {
                assertEquals(L.getRecursive(i), M.get(i));
            } else if (operationNumber == 5) {
                assertEquals(L.size(), M.size());
            }
        }
    }

    @Test
    public void hasNextTest() {
        LinkedListDeque<Integer> ad1 = new LinkedListDeque<>();
        ad1.addFirst(0);
        Iterator<Integer> iter = ad1.iterator();
        assertTrue(iter.hasNext());
    }

    @Test
    /* Test enhanced for-each loop syntax */
    public void nextTest() {
        LinkedListDeque<Integer> ad1 = new LinkedListDeque<>();
        for (int i = 49; i >= 0; i -= 1) {
            ad1.addFirst(i);
        }
        for (int i = 50; i < 100; i += 1) {
            ad1.addLast(i);
        }
        int counter = 0;
        Iterator<Integer> iter = ad1.iterator();
        while (iter.hasNext()) {
            assertEquals(counter, (int) iter.next());
            counter += 1;
        }
    }

    @Test
    public void equalTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        for (int i = 0; i < 100; i++) {
            lld1.addLast(i);
            lld2.addLast(i);
        }
        assertTrue(lld1.equals(lld2));
        assertTrue(lld1.equals(lld1));
        lld2.removeLast();
        assertFalse(lld1.equals(lld2));
        lld2.addLast(1);
        assertFalse(lld1.equals(lld2));
    }

    /** Time Testing */

    private static void printTimingTable(LinkedListDeque<Integer> Ns, LinkedListDeque<Double> times, LinkedListDeque<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAddFirst();
        // timeAddLast();
        // timeRemoveFirst();
        // timeRemoveLast();
        // timeSize();
         timeIteratingOver();
    }

    public static void timeAddFirst() {
        LinkedListDeque<Integer> ns = new LinkedListDeque<>();
        LinkedListDeque<Double> ts = new LinkedListDeque<>();
        ns.addLast(1000);
        ns.addLast(2000);
        ns.addLast(4000);
        ns.addLast(8000);
        ns.addLast(16000);
        ns.addLast(32000);
        ns.addLast(64000);
        ns.addLast(128000);
        for (int i = 0; i < ns.size(); i = i + 1) {
            LinkedListDeque<Integer> lld = new LinkedListDeque<>();
            int size = ns.get(i);
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < size; j = j + 1) {
                lld.addLast(j);
            }
            ts.addLast(sw.elapsedTime());
        }
        System.out.println("AddFirst:");
        printTimingTable(ns, ts, ns);
    }

    public static void timeIteratingOver() {
        LinkedListDeque<Integer> ns = new LinkedListDeque<>();
        LinkedListDeque<Double> ts = new LinkedListDeque<>();
        ns.addLast(1000);
        ns.addLast(2000);
        ns.addLast(4000);
        ns.addLast(8000);
        ns.addLast(16000);
        ns.addLast(32000);
        ns.addLast(64000);
        for (int i = 0; i < ns.size(); i = i + 1) {
            LinkedListDeque<Integer> lld = new LinkedListDeque<>();
            int size = ns.get(i);
            for (int j = 0; j < size; j = j + 1) {
                lld.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < size; j = j + 1) {
                lld.get(lld.size() - 1);
            }
            ts.addLast(sw.elapsedTime());
        }
        System.out.println("Iterating Over the LinkedListDeque using for-each loop should take time proportional to the number of items:");
        printTimingTable(ns, ts, ns);
        }
}