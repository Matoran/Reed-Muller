package tools;

import java.util.ArrayList;

/**
 * Created by matoran on 6/5/17.
 */
public class PGM {
    private int lines, columns, highValue;
    private ArrayList<Integer> data;

    public PGM(int lines, int columns, int highValue) {
        this.lines = lines;
        this.columns = columns;
        this.highValue = highValue;
        data = new ArrayList<>();
    }

    public void add(int val){
        data.add(val);
    }

    public int getLines() {
        return lines;
    }

    public int getColumns() {
        return columns;
    }

    public int getHighValue() {
        return highValue;
    }

    public ArrayList<Integer> getData() {
        return data;
    }
}
