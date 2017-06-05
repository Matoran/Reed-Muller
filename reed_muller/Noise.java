package reed_muller;

import tools.PGM;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by matoran on 6/5/17.
 */
public class Noise {
    private static Random random = new Random();
    public static BigInteger add(BigInteger word, double probability, int length){
        for (int i = 0; i < length; i++) {
            if(random.nextDouble() < probability){
                word = word.flipBit(i);
            }
        }
        return word;
    }

    public static void add(PGM pgm, double probability, int length){
        for (int i = 0; i < pgm.getData().size(); i++) {
            pgm.getData().set(i, add(pgm.getData().get(i), probability, length));
        }
    }
}
