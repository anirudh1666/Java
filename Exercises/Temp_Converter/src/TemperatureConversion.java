import java.util.ArrayList;

public class TemperatureConversion {

    private final int min;
    private final int max;
    private String header = "Temperature Conversion";
    private String celsiusHeader = "C";
    private String fahrenheitHeader = "F";
    private int numColumns = 5;
    private int columnWidth = 4;
    private int spacesBetweenColumns = 5;

    private ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

    TemperatureConversion(int min, int max) {
        if (min >= max) throw new IllegalArgumentException("ERROR: Lower bound cannot be larger than or equal to upper bound");
        this.min = min;
        this.max = max;
    }

    public String getHeader() {
        return this.header;
    }

    public int getNumColumns() {
        return this.numColumns;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    public int getSpacesBetweenColumns() {
        return this.spacesBetweenColumns;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setCelsiusHeader(String celsiusHeader) {
        this.celsiusHeader = celsiusHeader;
    }

    public void setFahrenheitHeader(String fahrenheitHeader) {
        this.fahrenheitHeader = fahrenheitHeader;
    }

    public void setNumColumns(int numColumns) {
        if (numColumns < 1) throw new IllegalArgumentException("ERROR: Number of columns cannot be less than 1");
        this.numColumns = numColumns;
    }

    public void setColumnWidth(int columnWidth) {
        this.columnWidth = columnWidth;
    }

    public void setSpacesBetweenColumns(int spacesBetweenColumns) {
        if (spacesBetweenColumns < 0) throw new IllegalArgumentException("ERROR: Space between columns cannot be less than 0");
        this.spacesBetweenColumns = spacesBetweenColumns;
    }

    private int toFahrenheit(int celsius) {
        return (int)(celsius * 9.0/5.0 - 32);
    }

    private ArrayList<String> generateHeader() {
        ArrayList<String> header = new ArrayList<String>();
        String dashes = "";
        header.add(this.header);
        for (int i = 0; i < this.header.length(); i++) dashes += "–";
        header.add(dashes);
        return header;
    }

    private ArrayList<String> generateColumns() {
        ArrayList<String> tableColumnArray = new ArrayList<String>();
        String singleColumnString = "";
        singleColumnString += this.celsiusHeader;
        for (int i = 0; i < this.columnWidth - this.celsiusHeader.length(); i++)
            singleColumnString += " ";
        singleColumnString += this.fahrenheitHeader;
        for (int i = 0; i < columnWidth - this.fahrenheitHeader.length(); i++)
            singleColumnString += " ";
        for (int i = 0; i < this.numColumns; i++) tableColumnArray.add(singleColumnString);
        return tableColumnArray;
    }

    private ArrayList<String> generateContents() {
        ArrayList<String> tableContentArray = new ArrayList<String>();
        for (int i = this.min; i <= this.max; i++) {
            String temperatureString = "";
            String celsiusString = Integer.toString(i);
            temperatureString += celsiusString;
            for (int j = 0; j < this.columnWidth - celsiusString.length(); j++)
                temperatureString += " ";
            String fahrenheitString = Integer.toString(toFahrenheit(i));
            temperatureString += fahrenheitString;
            for (int j = 0; j < this.columnWidth - fahrenheitString.length(); j++)
                temperatureString += " ";
            tableContentArray.add(temperatureString);
        }
        return tableContentArray;
    }

    public void generateTable() {
        this.table.add(generateHeader());
        this.table.add(generateColumns());
        this.table.add(generateContents());
    }

    public ArrayList< ArrayList<String> > getTable() {
        return table;
    }
}
