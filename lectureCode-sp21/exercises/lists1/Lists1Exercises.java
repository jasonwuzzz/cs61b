import jh61b.junit.In;

public class Lists1Exercises {
    /** Returns an IntList identical to L, but with
      * each element incremented by x. L is not allowed
      * to change. */
    public static IntList incrList(IntList L, int x) {
        /* Your code here. */
        IntList M = new IntList(L.first + x, L.rest);
        IntList p = M;

        while (p.rest.rest != null) {
            p.rest = new IntList(p.rest.first + x, p.rest.rest);
            p = p.rest;
        }
        p.rest = new IntList(p.rest.first + x, null);
        return M;

    }

    /** Returns an IntList identical to L, but with
      * each element incremented by x. Not allowed to use
      * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        /* Your code here. */
        IntList p = L;
        while (p.rest != null) {
            p.first += x;
            p = p.rest;
        }
        return L;
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.rest = new IntList(7, null);
        L.rest.rest = new IntList(9, null);

        // System.out.println(L.size());
        // System.out.println(L.iterativeSize());

        // Test your answers by uncommenting. Or copy and paste the
        // code for incrList and dincrList into IntList.java and
        // run it in the visualizer.
        int i = 1;
        System.out.println(L.get(i));
        // IntList M = incrList(L, 4);
        // System.out.println(M.get(i));
        // System.out.println(dincrList(L, 3));
        IntList N = dincrList(L, 4);
        System.out.println(N.get(i));
    }
}