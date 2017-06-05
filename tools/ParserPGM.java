package tools;

import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author ISELI Cyril & RODRIGUES Marco
 * @version 0.1
 * @date June 2017
 * @file ParserPGM.java
 * <p>
 * PGM helper, can read or write a pgm file
 */
public class ParserPGM {
    /**
     * create a pgm object from a file
     *
     * @param filename file to parse
     * @return PGM object
     */
    public static PGM read(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            //ignore first and second line
            sc.nextLine();
            sc.nextLine();
            //read size(line, column) and max grey
            PGM pgm = new PGM(sc.nextInt(), sc.nextInt(), sc.nextInt());
            //read all pixels
            while (sc.hasNextBigInteger()) {
                pgm.add(sc.nextBigInteger());
            }
            return pgm;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * save a pgm object into a file
     *
     * @param fileName of the file
     * @param pgm      PGM object
     */
    public static void save(String fileName, PGM pgm) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            writer.println("P2");
            writer.println("# created by ParserPGM.java");
            writer.println(pgm.getLines() + " " + pgm.getColumns());
            writer.println(pgm.getHighValue());
            for (BigInteger val : pgm.getData()) {
                writer.print(val + " ");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
