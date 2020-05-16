import java.util.ArrayList;

/* Class that creates a data structure that holds all the data
   that is read by DataLoader class. Contains an arraylist of
   columns and has methods to manipulate data structure.
 */
public class DataFrame {

    private ArrayList<Column> columns = new ArrayList<>();

    // @param = Name : name of new column.
    public void addColumn(String name) {
        Column aColumn = new Column(name);
        columns.add(aColumn);
    }

    // @return = arraylist of column names in the order they were added.
    public ArrayList<String> getColumnNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Column element : columns) {
            names.add(element.getName());
        }
        return names;
    }

    /* @return = the size of first Column.
       All columns have the same size so only need first one.
     */
    public int getRowCount() {
        if (!columns.isEmpty()) {
            Column first = columns.get(0);
            return first.getRowCount();
        } else {
            return 0;
        }
    }

    /* @params = Name : name of columnm, row : index of the row that contains the value.
       @return = Returns the value at given index in given column.
     */
    public String getValue(String name, int row) {
        Column aColumn = searchForColumn(name);
        if (aColumn != null) {
            return aColumn.getRowValue(row);
        }
        else {
            System.out.println("This column was not found.");
            return null;
        }
    }

    /* Adds a value into a certain column.
       @params - Name of column and value that should be entered into row.
     */
    public void putValue(String name, String value) {
        Column aColumn = searchForColumn(name);
        if (aColumn != null) {
            aColumn.addRowValue(value);
        }
        else {
            System.out.println("Column was not found.");
        }
    }

    /* Helper method to search for column in columns.
       @param = Name : name of column.
       @return = column that should be found or null if not found.
     */
    public Column searchForColumn(String name) {
        for (Column element : columns) {
            if (element.getName().equals(name)) {
                return element;
            }
        }
        return null;
    }
}
