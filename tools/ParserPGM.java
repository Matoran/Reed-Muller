package tools;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by matoran on 6/5/17.
 */
public class ParserPGM {
    public static PGM read(String filename){
        try {
            Scanner sc = new Scanner(new File(filename));
            sc.nextLine();
            sc.nextLine();
            PGM pgm = new PGM(sc.nextInt(), sc.nextInt(), sc.nextInt());
            while(sc.hasNextBigInteger()){
                pgm.add(sc.nextBigInteger());
            }
            return pgm;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void save(String fileName, PGM pgm) {
        try{
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
