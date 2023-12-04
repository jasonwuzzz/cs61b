package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        /* Create an array of guitar strings. */
        GuitarString[] gs = new GuitarString[keyboard.length()];
        for (int i = 0; i < keyboard.length(); i += 1) {
            gs[i] = new GuitarString(440.0 * Math.pow(2, (i - 24) / 12.0));
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int i = keyboard.indexOf(key);
                if (i != -1) {
                    gs[i].pluck();
                    /* when a string is plucked, zero out all other strings in that group. */
                    for (int j = 6 * i / 6; j < 6 * (1 + i / 6) && j != i; j += 1) {
                        gs[j] = new GuitarString(440.0 * Math.pow(2, (i - 24) / 12.0));
                    }
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (GuitarString note : gs) {
                sample += note.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for(GuitarString note : gs) {
                note.tic();
            }
        }
    }
}

