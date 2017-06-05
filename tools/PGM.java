package tools;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @author ISELI Cyril & RODRIGUES Marco
 * @version 0.1
 * @date June 2017
 * @file PGM.java
 * <p>
 * PGM class, store a pgm file
 */
public class PGM {
    private int lines, columns, highValue;
    private ArrayList<BigInteger> data;

    /**
     * constructor
     * @param lines number of lines in the image
     * @param columns number of columns in the image
     * @param highValue high grey value in the image
     */
    PGM(int lines, int columns, int highValue) {
        this.lines = lines;
        this.columns = columns;
        this.highValue = highValue;
        data = new ArrayList<>(lines*columns);
    }

    void add(BigInteger val){
        data.add(val);
    }

    //--------------------Getters-------------------------------
    int getLines() {
        return lines;
    }

    int getColumns() {
        return columns;
    }

    int getHighValue() {
        return highValue;
    }

    public ArrayList<BigInteger> getData() {
        return data;
    }
}
