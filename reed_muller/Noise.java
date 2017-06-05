package reed_muller;

import tools.PGM;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author ISELI Cyril & RODRIGUES Marco
 * @version 0.1
 * @date June 2017
 * @file Noise.java
 * <p>
 * Add noise in word or pgm file
 */
public class Noise {
    private static Random random = new Random();

    /**
     * each bit have probability to be flipped
     *
     * @param word        word to add noise
     * @param probability [0,1]
     * @return noised word
     */
    public static BigInteger add(BigInteger word, double probability) {
        for (int i = 0; i < word.bitLength(); i++) {
            if (random.nextDouble() < probability) {
                word = word.flipBit(i);
            }
        }
        return word;
    }

    /**
     * each bit of each pixel has a probability to be flipped
     *
     * @param pgm         PGM object to add noise
     * @param probability [0,1]
     */
    public static void add(PGM pgm, double probability) {
        for (int i = 0; i < pgm.getData().size(); i++) {
            pgm.getData().set(i, add(pgm.getData().get(i), probability));
        }
    }
}
