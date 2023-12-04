package gh2;

public class Harp extends GuitarString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .999; // energy decay factor

    /**
     * Adjust the buffer sizes by a factor of two since the natural
     * resonance frequency is cut in half by the tic() change. */
    public Harp(double frequency) {
        super(0.5 * frequency);
    }

    /* Flipping the sign of the new value before enqueueing it
     * in tic() will change the sound from guitar-like to harp-like.
     */
    @Override
    public void tic() {
        double front = buffer.removeFirst();
        double second = buffer.get(0);
        buffer.addLast(- DECAY * (front + second) * 0.5);
    }
}
