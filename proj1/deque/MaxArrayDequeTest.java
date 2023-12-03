package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;


public class MaxArrayDequeTest {
    private static class stringCmp implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }

    private static class absCmp implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Math.abs(o1) - Math.abs(o2);
        }
    }

    private static class Dog {
        String name;
        int weight;

        public Dog(String n, int w) {
            name = n;
            weight = w;
        }
    }

    private static class dogCmp implements Comparator<Dog> {
        @Override
        public int compare(Dog o1, Dog o2) {
            return o1.weight - o2.weight;
        }
    }

    @Test
    public void maxTest1() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(new stringCmp());
        mad.addFirst("To");
        mad.addFirst("get");
        mad.addFirst("practice");
        mad.addFirst("using");
        assertEquals(mad.max(), "practice");
    }

    @Test
    public void maxTest2() {
        MaxArrayDeque<Dog> mad = new MaxArrayDeque<>(new dogCmp());
        mad.addFirst(new Dog("Josh", 10));
        mad.addFirst(new Dog("Emma", 20));
        mad.addFirst(new Dog("Denero", 40));
        assertEquals(mad.max().name, "Denero");
    }
}
