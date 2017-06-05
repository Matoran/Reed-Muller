package reed_muller;

import tools.PGM;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by matoran on 6/5/17.
 */
public class ReedMuller {
    private int r;
    private int[][] G;
    private ArrayList<BigInteger> words;
    private int length;
    public ReedMuller(int r) {
        this.r = r;
        length = (int) Math.pow(2, r);
        G = new int[r + 1][length];

        String valBin;
        String reverse;
        for (int column = 0; column < length; column++) {
            valBin = Integer.toBinaryString(column);
            reverse = new StringBuffer(valBin).reverse().toString();
            for (int row = 0; row < r; row++) {
                if (row <= reverse.length() - 1) {
                    G[row][column] = Integer.parseInt(String.valueOf(reverse.charAt(row)));
                } else {
                    G[row][column] = 0;
                }
            }
        }
        for (int column = 0; column < length; column++) {
            G[r][column] = 1;
        }

        words = new ArrayList<>();
        int max = (int)Math.pow(2, r+1);
        for(int x = 0; x < max; x++){
            words.add(encode(BigInteger.valueOf(x)));
        }
        words.forEach((data)-> System.out.println(data.toString(2)));
    }

    public BigInteger encode(BigInteger word) {
        BigInteger wordEncoded = new BigInteger("0");


        int sum = 0;
        for (int column = 0; column < G[0].length; column++) {
            for (int row = 0; row < r + 1; row++) {
                int bit = word.testBit(row) ? 1 : 0;
                sum += bit * G[row][column];
            }
            sum %= 2;

            if (sum == 1) {
                wordEncoded = wordEncoded.setBit(column);
            }
            sum = 0;
        }

        return wordEncoded;

        //System.out.println(wordEncoded.intValue());

        /*//Display G
        for(int row = 0; row < r+1; row++){
            for(int column = 0; column < length; column++){
                System.out.print(G[row][column]);
            }
            System.out.println();
        }*/


    }

    public BigInteger decode(BigInteger wordEncoded) {
        BigInteger wordDecoded = new BigInteger("0");

        if (wordEncoded.testBit(0)) {
            wordEncoded = wordEncoded.not();
            wordDecoded = wordDecoded.setBit(r);
        }

        for (int i = 0; i < r; i++) {
            wordDecoded = wordEncoded.testBit((int) Math.pow(2, i)) ? wordDecoded.setBit(i) : wordDecoded.clearBit(i);
        }
        return wordDecoded;

    }



    public BigInteger denoise(BigInteger word) {
        String binaryWord = word.toString(2);
        while (binaryWord.length() < Math.pow(2,r)){
            binaryWord = "0" + binaryWord;
        }

        int[] F = new int[binaryWord.length()];
        for (int i = 0; i < binaryWord.length(); i++) {
            if(binaryWord.charAt(i) == '0'){
                F[i] = 1;
            }else{
                F[i] = -1;
            }
        }

        BigInteger valBin;
        for (int i = 0; i < r-1; i++) {
            int[] newF = new int[(int)Math.pow(2,r-1)];
            for(int k = 0; k < Math.pow(2,r-1); k++){
                valBin = new BigInteger(Integer.toString(k));
                if(valBin.testBit(i)){
                    valBin.clearBit(i);
                    newF[k] = F[k] - F[valBin.intValue()];
                }else{
                    valBin.setBit(i);
                    newF[k] = F[k] + F[valBin.intValue()];
                }
            }
            F = newF;
        }

        ArrayList<Integer> FAbs = new ArrayList<Integer>();
        for (int i = 0; i < F.length; i++) {
            FAbs.add(Math.abs(F[i]));
        }

        int maxF = Collections.max(FAbs);

        int indexMaxF = FAbs.indexOf(maxF);

        BigInteger wordDenoise;

        if(F[indexMaxF] < 0){
            wordDenoise = new BigInteger(Integer.toString((indexMaxF + (int)Math.pow(2,r))));
        }else{
            wordDenoise = new BigInteger(Integer.toString(indexMaxF));
        }

        return wordDenoise;
    }

    public int hamming(BigInteger word1, BigInteger word2){
        int size = Integer.max(word1.bitLength(), word2.bitLength());
        int distance = 0;
        for (int i = 0; i < size; i++) {
            if(word1.testBit(i) != word2.testBit(i)){
                distance++;
            }
        }
        return distance;
    }

    public BigInteger denoiseSemiExhaustive(BigInteger word){
        int closer = -1;
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < words.size(); i++) {
            int newDistance = hamming(word, words.get(i));
            if(newDistance < distance){
                closer = i;
                distance = newDistance;
            }
            if(length-newDistance < distance){
                distance = length-newDistance;
                closer = i + length;
            }
        }
        return  words.get(closer);
    }

    public void encode(PGM pgm) {
        for (int i = 0; i < pgm.getData().size(); i++) {
            pgm.getData().set(i, encode(pgm.getData().get(i)));
        }
    }

    public void decode(PGM pgm) {
        for (int i = 0; i < pgm.getData().size(); i++) {
            pgm.getData().set(i, decode(pgm.getData().get(i)));
        }
    }

    public void denoise(PGM pgm) {
        for (int i = 0; i < pgm.getData().size(); i++) {
            pgm.getData().set(i, denoise(pgm.getData().get(i)));
        }
    }
}
