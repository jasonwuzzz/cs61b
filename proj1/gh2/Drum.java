package gh2;

import edu.princeton.cs.algs4.StdRandom;

public class Drum extends GuitarString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = 1.0; // energy decay factor


    public Drum(double frequency) {
        super(frequency);
    }

    /**
     * Flipping the sign of a new value with probability 0.5 before
     * enqueueing it in tic() will produce a drum sound
     */
    @Override
    public void tic() {
        double front = buffer.removeFirst();
        double second = buffer.get(0);
        int prob = StdRandom.uniform(0, 2);
        if (prob == 0){
            buffer.addLast(DECAY * (front + second) * 0.5);
        } else {
            buffer.addLast(- DECAY * (front + second) * 0.5);
        }
    }
}
