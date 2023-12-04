package tester;

import org.junit.Test;
import static org.junit.Assert.*;
import student.StudentArrayDeque;
import edu.princeton.cs.algs4.StdRandom;

public class TestArrayDequeEC {

    @Test
    public void anonymousTest() {
        StudentArrayDeque<Integer> buggy = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();
        StringBuilder msg = new StringBuilder();

        int N = 100000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 5);
            if (operationNumber == 0) {
                msg.append("\naddFirst(" + i + ")");
                buggy.addFirst(i);
                correct.addFirst(i);
            } else if (operationNumber == 1) {
                msg.append("\naddLast(" + i + ")");
                buggy.addLast(i);
                correct.addLast(i);
            } else if (operationNumber == 2) {
                if (!correct.isEmpty()) {
                    msg.append("\nisEmpty()");
                    assertFalse(buggy.isEmpty());
                    msg.append("\nremoveFirst()");
                    Integer b = buggy.removeFirst();
                    Integer c = correct.removeFirst();
                    assertEquals(msg.toString(), c, b);
                }
            } else if (operationNumber == 3) {
                if (!correct.isEmpty()) {
                    msg.append("\nisEmpty()");
                    assertFalse(buggy.isEmpty());
                    msg.append("\nremoveLast()");
                    Integer b = buggy.removeLast();
                    Integer c = correct.removeLast();
                    assertEquals(msg.toString(), c, b);
                }
            } else if (operationNumber == 4) {
                msg.append("\nsize()");
                assertEquals(msg.toString(), correct.size(),  buggy.size());
                if (correct.size() > 0) {
                    int index = StdRandom.uniform(0, correct.size());
                    msg.append("\nget(" + index + ")");
                    assertEquals(msg.toString(), correct.get(index), buggy.get(index));
                }
            }
        }
    }
}
