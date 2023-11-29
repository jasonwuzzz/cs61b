package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

    /** Adds the same value to both the correct and buggy AList implementations,
     * then checks that the results of three subsequent removeLast calls are the same.
     */
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> a = new AListNoResizing<>();
        BuggyAList<Integer> b = new BuggyAList<>();
        int[] ints = {4, 5, 6};
        for (int i : ints) {
            a.addLast(i);
            b.addLast(i);
        }
        for (int i : ints) {
            assertEquals(a.removeLast(), b.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> M = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                M.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size1 = L.size();
                int size2 = M.size();
                assertEquals(size1, size2);
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() > 0) {
                    int back1 = L.getLast();
                    int back2 = M.getLast();
                    assertEquals(back1, back2);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() > 0) {
                    int back1 = L.removeLast();
                    int back2 = M.removeLast();
                    assertEquals(back1, back2);
                }
            }
        }
    }
}
