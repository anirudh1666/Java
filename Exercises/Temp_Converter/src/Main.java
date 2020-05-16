import java.util.ArrayList;

public class Main {

    static TemperatureConversion temperatureRange;

    private static int getInt(String ask) {
        Input in = new Input();
        System.out.print(ask);
        return in.nextInt();
    }

    private static String getString(String ask) {
        Input in = new Input();
        System.out.print(ask);
        return in.nextLine();
    }

    private static void printInstructions() {
        System.out.println("1. Set Temperature Range");
        System.out.println("2. Set Table Header");
        System.out.println("3. Set Celsius Column Header (Default C)");
        System.out.println("4. Set Fahrenheit Column Header (Default F)");
        System.out.println("5. Set Number of Columns");
        System.out.println("6. Set Number of Spaces Between Columns");
        System.out.println("7. Output Temperature Table");
        System.out.println("8. Quit");
    }

    private static void setNewTemperatureRange(int lower, int upper) {
        try {
            temperatureRange = new TemperatureConversion(lower, upper);
        } catch (Exception invalidTemperatureRange) {
            System.out.println("ERROR : invalid Temperature Range.");
        }
    }

    private static void setTableHeader(String newTableHeader) {
        temperatureRange.setHeader(newTableHeader);
    }

    private static void setTableCelsiusColumnHeader(String newCelsiusColumnHeader) {
        temperatureRange.setCelsiusHeader(newCelsiusColumnHeader);
    }

    private static void setTableFahrenheitColumnHeader(String newFahrenheitColumnHeader) {
        temperatureRange.setFahrenheitHeader(newFahrenheitColumnHeader);
    }

    private static void setNumberOfColumns(int newNumberOfColumns) {
        try {
            temperatureRange.setNumColumns(newNumberOfColumns);
        } catch (Exception invalidNumberOfColumns) {
            System.out.println("ERROR : invalid Number Of Columns.");
        }
    }

    private static void setNumberOfSpacesBetween(int newNumberOfSpacesBetween) {
        try {
            temperatureRange.setSpacesBetweenColumns(newNumberOfSpacesBetween);
        } catch (Exception invalidNumberOfSapcesBetween) {
            System.out.println("ERROR : invalid Number of Spaces Between");
        }
    }

    private static void outputTemperatureTable(ArrayList<ArrayList<String>> table) {
        //System.out.println(table);
        ArrayList<String> header = table.get(0);
        ArrayList<String> columns = table.get(1);
        ArrayList<String> contents = table.get(2);
        int columnWidth = temperatureRange.getColumnWidth();
        int numberOfSpacesBetweenColumns = temperatureRange.getSpacesBetweenColumns();
        int numberOfColumns = temperatureRange.getNumColumns();
        int lineWidth = numberOfColumns * columnWidth * 2 + numberOfSpacesBetweenColumns * (numberOfColumns - 1);
        int headerOffset = (lineWidth - temperatureRange.getHeader().length()) / 2;
        for (String s : header) {
            for (int j = 0; j < headerOffset; j++) System.out.print(" ");
            System.out.println(s);
        }
        for (String column : columns) {
            System.out.print(column);
            for (int j = 0; j < numberOfSpacesBetweenColumns; j++)
                System.out.print(" ");
        }
        System.out.println();
        int currentColumn = 0;
        for (String content : contents) {
            System.out.print(content);
            for (int j = 0; j < numberOfSpacesBetweenColumns; j++)
                System.out.print(" ");
            currentColumn++;
            if (currentColumn == numberOfColumns) {
                System.out.println();
                currentColumn = 0;
            }
        }
        System.out.println();
    }

    public static void main (String[] args) {
        int choice = -1;
        while (choice != 8) {
            printInstructions();
            choice = getInt("");
            switch (choice) {
                case 1:
                    setNewTemperatureRange(getInt("Enter lower bound: "), getInt("Enter upper bound: "));
                    break;
                case 2:
                    setTableHeader(getString("Enter table header: "));
                    break;
                case 3:
                    setTableCelsiusColumnHeader(getString("Enter celsius column header: "));
                    break;
                case 4:
                    setTableFahrenheitColumnHeader(getString("Enter fahrenheit column header: "));
                    break;
                case 5:
                    setNumberOfColumns(getInt("Enter desired number of columns: "));
                    break;
                case 6:
                    setNumberOfSpacesBetween(getInt("Enter desired number of spaces between columns: "));
                    break;
                case 7:
                    temperatureRange.generateTable();
                    outputTemperatureTable(temperatureRange.getTable());
                    break;
            }
        }
    }
}