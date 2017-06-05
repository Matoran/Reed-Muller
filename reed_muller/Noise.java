package reed_muller;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by matoran on 6/5/17.
 */
public class Noise {
    private static Random random = new Random();
    public static BigInteger add(BigInteger word, double probability){
        for (int i = 0; i < word.bitCount(); i++) {
            if(random.nextDouble() < probability){
                word = word.flipBit(i);
            }
        }
        return word;
    }
}
