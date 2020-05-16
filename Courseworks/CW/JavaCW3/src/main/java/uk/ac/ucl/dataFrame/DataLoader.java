package uk.ac.ucl.dataFrame;

import java.io.*;
import java.util.ArrayList;

public class DataLoader {

    private DataFrame library = new DataFrame();
    private File file;

    public DataLoader(File file) {
        try {
            this.file = file;
            initialiseColumns();
            loadData();
        } catch (IOException e) {
            System.out.println("File was unable to be read.");
        }
    }

    // Initialises all the column headings.
    private void initialiseColumns() {
        this.library.addColumn("ID");
        this.library.addColumn("BIRTH DATE");
        this.library.addColumn("DEATH DATE");
        this.library.addColumn("SSN");
        this.library.addColumn("DRIVERS");
        this.library.addColumn("PASSPORT");
        this.library.addColumn("PREFIX");
        this.library.addColumn("FIRST");
        this.library.addColumn("LAST");
        this.library.addColumn("SUFFIX");
        this.library.addColumn("MAIDEN");
        this.library.addColumn("MARITAL");
        this.library.addColumn("RACE");
        this.library.addColumn("ETHNICITY");
        this.library.addColumn("GENDER");
        this.library.addColumn("BIRTHPLACE");
        this.library.addColumn("ADDRESS");
        this.library.addColumn("CITY");
        this.library.addColumn("STATE");
        this.library.addColumn("ZIP");
    }

    /* Inserts a whole new row of data into each column.
       @param = data : Array that contains all the information to be put
                       in the new row.
     */
    private void insertRow(String[] data) {
        ArrayList<String> names = library.getColumnNames();
        for (int i = 0; i < data.length; i++) {
            this.library.putValue(names.get(i), data[i]);
        }
    }

    public DataFrame getLibrary() {
        return this.library;
    }

    /* Splits the line between ",". If the ZIP is blank then the last value
       isn't recorded into the ZIP column. To combat this we first check if
       String[] has length 20. If not we add extra "," which enters blank entry
       for ZIP.
       @throws = IOException if file was unable to be read.
     */
    private void loadData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                if (data.length != 20) {
                    StringBuilder temp = new StringBuilder(line);
                    temp.append(" ,");
                    String line2 = temp.toString();
                    String[] data2 = line2.split(",");
                    insertRow(data2);
                    line = reader.readLine();
                }
                else {
                    insertRow(data);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }
}

