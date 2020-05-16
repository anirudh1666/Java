package uk.ac.ucl.dataFrame;

import java.util.ArrayList;

/* This is the column class. It holds the name of a column and contains a certain number of rows. */

public class Column {

    private String name;
    private ArrayList<String> rows;

    // Constructor  that makes new column.
    public Column(String name) {
        this.name = name;
        rows = new ArrayList<>();
    }

    // @return = name of Column.
    public String getName() {
        return this.name;
    }

    // @return = row value at given index.
    public String getRowValue(int index) {
        return this.rows.get(index);
    }

    // @param = Value : Value that should be set at index. Index : index of that row.
    public void setRowValue(String value, int index) {
        this.rows.set(index, value);
    }

    // @param = Value : value of new row
    public void addRowValue(String value) {
        this.rows.add(value);
    }

    // @return = the number of rows in column.
    public int getRowCount() {
        return this.rows.size();
    }

    /* @param = Value : Value to be found.
       @return = index of the row that contains value.
     */
    public int findRow(String value) {
        return rows.indexOf(value);
    }
}
