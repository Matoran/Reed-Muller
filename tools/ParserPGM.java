package tools;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by matoran on 6/5/17.
 */
public class ParserPGM {
    public static PGM read(String filename){
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            //first line P2
            br.readLine();
            //comments
            while ((line = br.readLine()) != null && line.charAt(0) == '#');
            if(line != null) {
                line = line.trim().replaceAll(" +", " ");
                String size[] =line.split(" ");
                int lines = Integer.valueOf(size[0]);
                int columns = Integer.valueOf(size[1]);

                int highValue = Integer.valueOf(br.readLine().trim());
                PGM pgm = new PGM(lines, columns, highValue);
                while ((line = br.readLine()) != null){
                    line = line.trim();
                    if(line.length() > 0){
                        pgm.add(Integer.valueOf(line.trim()));
                    }

                }
                return pgm;
            }
        } catch (IOException e) {
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
            for (Integer val : pgm.getData()) {
                writer.println(val);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
