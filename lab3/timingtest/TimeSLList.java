package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> ns = new AList<>();
        AList<Double> ts = new AList<>();
        AList<Integer> ops = new AList<>();

        int[] n1 = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        int[] n2 = {10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000};

        for (int i = 0; i < n1.length; i = i + 1) {
            ns.addLast(n1[i]);
            ops.addLast(n2[i]);
        }

        for (int i = 0; i < ns.size(); i = i + 1) {
            // Create an SLList.
            SLList<Integer> sList = new SLList<>();
            // Add N items to the SLList.
            for (int j = 0; j < ns.get(i); j = j + 1) {
                sList.addLast(0);
            }
            // Start the timer.
            Stopwatch sw = new Stopwatch();
            // Perform M getLast operations on the SLList.
            for (int j = 0; j < ops.get(i); j = j + 1) {
                sList.getLast();
            }
            // Check the timer. This gives the total time to complete all M operations.
            ts.addLast(sw.elapsedTime());
        }
        printTimingTable(ns, ts, ops);
    }

}
