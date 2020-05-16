import java.util.ArrayList;


/* This class will have all the methods that the GUI requires
   to access data from DataFrame.
 */
public class Model {

    private DataLoader data;

    public Model(String fileName) {
        this.data = new DataLoader(fileName);
    }

    /* Uses Object[row][column].
       Makes Object[][] to pass to GUI to construct JTable.
       @return = 2D array with data from file.
     */
    // TODO: Make it so it only displays data for the columns in CHECKEDBOXES
    // TODO: IF CHECKEDBOXES == NULL make it so that it displays empty table.
    public Object[][] constructTable(ArrayList<String> checkedBoxes) {
        DataFrame library = data.getLibrary();
        int rowCount = library.getRowCount();
        Object[][] table = new Object[rowCount][20];
        try {
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < checkedBoxes.size(); j++) {
                    table[i][j] = library.getValue(checkedBoxes.get(j), i);
                }
            }
            return table;
        } catch (NullPointerException e) {
            return table;
        }
    }

    public ArrayList<String> getColumns() {
        DataFrame library = data.getLibrary();
        return library.getColumnNames();
    }

    /* Makes Object[] that contains all the column names.
       @returns = an array of all the headers.
     */
    // TODO: Make it so it only displays data for the columns in CHECKEDBOXES
    // TODO: IF CHECKEDBOXES == NULL make it so that it displays empty table with all headers.
    public Object[] constructHeaders(ArrayList<String> checkedBoxes) {
        DataFrame library = data.getLibrary();
        Object[] headers = new Object[checkedBoxes.size()];
        try {
            for (int i = 0; i < checkedBoxes.size(); i++) {
                headers[i] = checkedBoxes.get(i);
            }
            return headers;
        } catch (NullPointerException e) {
            ArrayList<String> columns = library.getColumnNames();
            for (int i = 0; i < columns.size(); i++) {
                headers[i] = columns.get(i);
            }
            return headers;
        }
    }

    /* Search function that searches for person in given file.
       @param = name : last name of the person we are looking for.
       @returns = the row of data corresponding to that person.
     */
    public String[][] searchFunction(String name) {
        ArrayList<String> targetData = new ArrayList<>();
        DataFrame library = this.data.getLibrary();
        Column last = library.searchForColumn("LAST");
        String[][] data = new String[2][20];
        int index = last.findRow(name);
        if (index == -1) {
            return null;
        }
        ArrayList<String> columnNames = library.getColumnNames();
        for (String columns : columnNames) {
            String value = library.getValue(columns, index);
            targetData.add(value);
        }
        for (int i = 0; i < 20; i++) {
            data[0][i] = columnNames.get(i);
            data[1][i] = targetData.get(i);
        }
        return data;
    }

    /* Function that finds the oldest person in the file.
       @returns = the row of data corresponding to oldest person.
     */
    public ArrayList<String> findOldest() {
        DataFrame library = this.data.getLibrary();
        Column birthDate = library.searchForColumn("BIRTH DATE");
        int oldestIndex = -1;
        double oldestAge = -1;
        for (int i = 0; i < birthDate.getRowCount(); i++) {
            String date = birthDate.getRowValue(i);
            double age = calculateAge(date);
            if (age > oldestAge) {
                oldestAge = age;
                oldestIndex = i;
            }
        }
        if (oldestIndex == -1) {
            return null;
        }
        else {
            ArrayList<String> targetData = getRow(oldestIndex);
            return targetData;
        }
    }

    // Helper function for findOldest that calculates age.
    private double calculateAge(String birthDate) {
        String[] data = birthDate.split("-");    // Split year-month-day into seperate parts.
        int year = Integer.parseInt(data[0]);
        int month = Integer.parseInt(data[1]);
        int day = Integer.parseInt(data[2]);
        double age = 2020 - year;
        age = age + month/12 + day/356;        // Age in units of year.
        return age;
    }

    // Helper function for findOldest that returns data at a certain index.
    private ArrayList<String> getRow(int index) {
        ArrayList<String> data = new ArrayList<>();
        DataFrame library = this.data.getLibrary();
        ArrayList<String> columnNames = library.getColumnNames();
        for (String name : columnNames) {
            String value = library.getValue(name, index);
            data.add(value);
        }
        return data;
    }
}
