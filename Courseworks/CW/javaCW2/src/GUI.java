import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*  This class builds the GUI that will display data
    being read in from DataLoader. It uses Model to
    access the data and perform operations like search.
 */
public class GUI extends JFrame {

    private Model model;
    private String fileName;

    // State of checkboxes. If true, GUI will show that column.
    private boolean id = false;
    private boolean birthdate = false;
    private boolean deathdate = false;
    private boolean ssn = false;
    private boolean drivers = false;
    private boolean passport = false;
    private boolean prefix = false;
    private boolean first = false;
    private boolean last = false;
    private boolean suffix = false;
    private boolean maiden = false;
    private boolean marital = false;
    private boolean race = false;
    private boolean ethnicity = false;
    private boolean gender = false;
    private boolean birthplace = false;
    private boolean address = false;
    private boolean city = false;
    private boolean state = false;
    private boolean zip = false;

    // List of all JComponents
    private final String TITLE = "Java CW2";
    private JPanel mainPanel;
    private JLabel helloLabel;
    private JScrollPane scroller;
    private JTable table;
    private JPanel leftPanel;
    private JButton searchButton;
    private JButton loadData;
    private JTextField fileAsker;
    private JButton fileButton;
    private JButton findOldest;

    private JPanel rightPanel;
    private JCheckBox idBox;
    private JCheckBox birthBox;
    private JCheckBox deathBox;
    private JCheckBox ssnBox;
    private JCheckBox driversBox;
    private JCheckBox passportBox;
    private JCheckBox prefixBox;
    private JCheckBox firstBox;
    private JCheckBox lastBox;
    private JCheckBox suffixBox;
    private JCheckBox maidenBox;
    private JCheckBox maritalBox;
    private JCheckBox raceBox;
    private JCheckBox ethnicityBox;
    private JCheckBox genderBox;
    private JCheckBox birthplaceBox;
    private JCheckBox addressBox;
    private JCheckBox cityBox;
    private JCheckBox stateBox;
    private JCheckBox zipBox;

    public GUI() {
        createGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createGUI() {
        setTitle(TITLE);
        setSize(1000,900);
        createMainPanel();
        createLeftPanel();
    }

    // Create the main Panel that will contain the data.
    // When data is loaded hello message is removed from this panel.
    private void createMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        helloLabel = new JLabel();
        helloLabel.setText("                                      Hello! Click load file to view data");
        mainPanel.add(helloLabel, BorderLayout.CENTER);
        add(mainPanel);
    }

    /* Creates JTable using methods from Model to build Object[][] for data and Object[] for headers.
       @param = fileName : name of file being opened.
     */
    private void createTable(String fileName) {
        ArrayList<String> whatToDisplay = checkBoxes();
        model = new Model(fileName);
        Object[][] data = model.constructTable(whatToDisplay);
        Object[] headers = model.constructHeaders(whatToDisplay);
        DefaultTableModel tableModel = new DefaultTableModel(data, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable();
        table.setModel(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scroller = new JScrollPane(table);
        table.setGridColor(Color.orange);
        mainPanel.remove(fileAsker);
        mainPanel.remove(fileButton);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(scroller);
        setVisible(true);
    }

    // Creates button panel on the left. Contains buttons for operations on data.
    private void createLeftPanel() {
        leftPanel = new JPanel(new GridLayout(10, 10, 10, 5));
        loadData = new JButton();
        loadData.setText("Load file");
        loadData.addActionListener((ActionEvent e) -> {             // Load data
            askForFile();
        });
        searchButton = new JButton();
        searchButton.setText("Search");
        searchButton.addActionListener((ActionEvent e) -> {        // Search for person
            new Search();
        });
        findOldest = new JButton();
        findOldest.setText("Find oldest");
        findOldest.addActionListener((ActionEvent e) -> {
            new Oldest();
        });
        leftPanel.add(loadData);
        leftPanel.add(searchButton);
        leftPanel.add(findOldest);
        mainPanel.add(leftPanel, BorderLayout.WEST);
    }

    // Asks for file that should be read then creates JTable for that file.
    private void askForFile() {
        checkForFile();
        fileAsker = new JTextField(10);
        fileButton = new JButton();
        fileButton.setText("SUBMIT");
        fileAsker.setText("Enter the name of the file: ");
        mainPanel.remove(helloLabel);
        mainPanel.add(fileAsker, BorderLayout.CENTER);
        mainPanel.add(fileButton, BorderLayout.EAST);
        fileButton.addActionListener((ActionEvent e) -> {
            String file = fileAsker.getText();
            file = file.substring(28);
            fileName = file;
            createTable(file);
            createRightPanel();
        });
        setVisible(true);
    }

    // Helper method that deletes any table already created when loading in a file.
    private void checkForFile() {
        try{
            mainPanel.remove(scroller);
            mainPanel.remove(rightPanel);
        } catch (NullPointerException e) {
            return;
        }
    }

    private void displayCheckedData() {
        try {
            mainPanel.remove(scroller);
        } catch (NullPointerException e) {
            return;
        }
    }

    private void createRightPanel() {
        rightPanel = new JPanel(new GridLayout(10,10,10,10));
        initialiseCheckBoxes();
        mainPanel.add(rightPanel, BorderLayout.EAST);
        setVisible(true);
    }

    private void initialiseCheckBoxes() {
        idBox = new JCheckBox("ID");
        idBox.addActionListener((ActionEvent e) -> {
            id = id != true;
            displayCheckedData();
            createTable(fileName);
        });
        birthBox = new JCheckBox("BIRTH DATE");
        birthBox.addActionListener((ActionEvent e) -> {
            birthdate = birthdate != true;
            displayCheckedData();
            createTable(fileName);
        });
        deathBox = new JCheckBox("DEATH DATE");
        deathBox.addActionListener((ActionEvent e) -> {
            deathdate = deathdate != true;
            displayCheckedData();
            createTable(fileName);
        });
        ssnBox = new JCheckBox("SSN");
        ssnBox.addActionListener((ActionEvent e) -> {
            ssn = ssn != true;
            displayCheckedData();
            createTable(fileName);
        });
        driversBox = new JCheckBox("DRIVERS");
        driversBox.addActionListener((ActionEvent e) -> {
            drivers = drivers != true;
            displayCheckedData();
            createTable(fileName);
        });
        passportBox = new JCheckBox("PASSPORT");
        passportBox.addActionListener((ActionEvent e) -> {
            passport = passport != true;
            displayCheckedData();
            createTable(fileName);
        });
        prefixBox = new JCheckBox("PREFIX");
        prefixBox.addActionListener((ActionEvent e) -> {
            prefix = prefix != true;
            displayCheckedData();
            createTable(fileName);
        });
        firstBox = new JCheckBox("FIRST");
        firstBox.addActionListener((ActionEvent e) -> {
            first = first != true;
        });
        lastBox = new JCheckBox("LAST");
        lastBox.addActionListener((ActionEvent e) -> {
            last = last != true;
            displayCheckedData();
            createTable(fileName);
        });
        suffixBox = new JCheckBox("SUFFIX");
        suffixBox.addActionListener((ActionEvent e) -> {
            suffix = suffix != true;
            displayCheckedData();
            createTable(fileName);
        });
        maidenBox = new JCheckBox("MAIDEN");
        maidenBox.addActionListener((ActionEvent e) -> {
            maiden = maiden != true;
            displayCheckedData();
            createTable(fileName);
        });
        maritalBox = new JCheckBox("MARITAL");
        maritalBox.addActionListener((ActionEvent e) -> {
            marital = marital != true;
            displayCheckedData();
            createTable(fileName);
        });
        raceBox = new JCheckBox("RACE");
        raceBox.addActionListener((ActionEvent e) -> {
            race = race != true;
            displayCheckedData();
            createTable(fileName);
        });
        ethnicityBox = new JCheckBox("ETHNICITY");
        ethnicityBox.addActionListener((ActionEvent e) -> {
            ethnicity = ethnicity != true;
            displayCheckedData();
            createTable(fileName);
        });
        genderBox = new JCheckBox("GENDER");
        genderBox.addActionListener((ActionEvent e) -> {
            gender = gender != true;
            displayCheckedData();
            createTable(fileName);
        });
        birthplaceBox = new JCheckBox("BIRTHPLACE");
        birthplaceBox.addActionListener((ActionEvent e) -> {
            birthplace = birthplace != true;
            displayCheckedData();
            createTable(fileName);
        });
        addressBox = new JCheckBox("ADDRESS");
        addressBox.addActionListener((ActionEvent e) -> {
            address = address != true;
            displayCheckedData();
            createTable(fileName);
        });
        cityBox = new JCheckBox("CITY");
        cityBox.addActionListener((ActionEvent e) -> {
            city = city != true;
            displayCheckedData();
            createTable(fileName);
        });
        stateBox = new JCheckBox("STATE");
        stateBox.addActionListener((ActionEvent e) -> {
            state = state != true;
            displayCheckedData();
            createTable(fileName);
        });
        zipBox = new JCheckBox("ZIP");
        zipBox.addActionListener((ActionEvent e) -> {
            zip = zip != true;
            displayCheckedData();
            createTable(fileName);
        });
        rightPanel.add(idBox);
        rightPanel.add(birthBox);
        rightPanel.add(deathBox);
        rightPanel.add(ssnBox);
        rightPanel.add(driversBox);
        rightPanel.add(passportBox);
        rightPanel.add(prefixBox);
        rightPanel.add(firstBox);
        rightPanel.add(lastBox);
        rightPanel.add(suffixBox);
        rightPanel.add(maidenBox);
        rightPanel.add(maritalBox);
        rightPanel.add(raceBox);
        rightPanel.add(ethnicityBox);
        rightPanel.add(genderBox);
        rightPanel.add(birthplaceBox);
        rightPanel.add(addressBox);
        rightPanel.add(cityBox);
        rightPanel.add(stateBox);
        rightPanel.add(zipBox);
    }

    private ArrayList<String> checkBoxes() {
        ArrayList<String> checkedBoxes = new ArrayList<>();
        if (id) {
            checkedBoxes.add("ID");
        }
        if (birthdate) {
            checkedBoxes.add("BIRTH DATE");
        }
        if (deathdate) {
            checkedBoxes.add("DEATH DATE");
        }
        if (ssn) {
            checkedBoxes.add("SSN");
        }
        if (drivers) {
            checkedBoxes.add("DRIVERS");
        }
        if (passport) {
            checkedBoxes.add("PASSPORT");
        }
        if (prefix){
            checkedBoxes.add("PREFIX");
        }
        if (first) {
            checkedBoxes.add("FIRST");
        }
        if (last) {
            checkedBoxes.add("LAST");
        }
        if (suffix) {
            checkedBoxes.add("SUFFIX");
        }
        if (maiden) {
            checkedBoxes.add("MAIDEN");
        }
        if (marital) {
            checkedBoxes.add("MARITAL");
        }
        if (race) {
            checkedBoxes.add("RACE");
        }
        if (ethnicity) {
            checkedBoxes.add("ETHNICITY");
        }
        if (gender) {
            checkedBoxes.add("GENDER");
        }
        if (birthplace) {
            checkedBoxes.add("BIRTHPLACE");
        }
        if (address) {
            checkedBoxes.add("ADDRESS");
        }
        if (city) {
            checkedBoxes.add("CITY");
        }
        if (state) {
            checkedBoxes.add("STATE");
        }
        if (zip) {
            checkedBoxes.add("ZIP");
        }
        return checkedBoxes;
    }

    /* Nested class that opens a small pop-up where you can search for
       people using their names.
     */
    class Search  {

        private JTextArea results;
        private JPanel mainPanel;
        private JButton submit;
        private JDialog popUp;
        private Label text;
        private JTextField searchBar;
        private JButton copyButton;


        // Creates the dialog box which contains JTable, search box, submit button and text.
        public Search() {
            popUp = new JDialog(SwingUtilities.getWindowAncestor(searchButton));
            text = new Label("Type in the last name of person: ");
            searchBar = new JTextField(10);
            submit = new JButton();
            submit.setText("Submit");
            submit.addActionListener((ActionEvent e) -> {
                String lastName = searchBar.getText();
                search(lastName);
            });
            mainPanel = new JPanel();
            popUp.setSize(500, 200);
            mainPanel.setSize(150,150);
            popUp.add(mainPanel);
            mainPanel.add(text);
            mainPanel.add(searchBar);
            mainPanel.add(submit);
            popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            popUp.setVisible(true);
        }

        /* Displays JTable with information about person being searched for.
           @param = lastName : last name of the person being searched.
           @catches = NullPointerException if the person isn't found.
         */
        private void search(String lastName) {
            try {
                String[][] data = model.searchFunction(lastName);
                results = new JTextArea();
                copyButton = new JButton("Copy");
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < 20; i++) {
                    temp.append(data[0][i] + ": " + data[1][i] + "\n");
                }
                results.setText(temp.toString());
                copyButton.addActionListener((ActionEvent e) -> {
                    StringSelection string = new StringSelection(temp.toString());
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(string, null);
                });
                mainPanel.add(copyButton, BorderLayout.EAST);
                mainPanel.add(results, BorderLayout.SOUTH);
                popUp.pack();
                popUp.setVisible(true);
            } catch (NullPointerException e) {
                Label errorSearch = new Label("This person was unable to be found.");
                mainPanel.add(errorSearch, BorderLayout.SOUTH);
                popUp.setVisible(true);
            }
        }
    }

    /* Nested class that displays the information of the oldest person
       in a pop-up window.
     */
    class Oldest {

        private JDialog popUp;
        private JTextArea results;
        private JPanel mainPanel;
        private JButton copyButton;

        public Oldest() {
            try {
                popUp = new JDialog(SwingUtilities.getWindowAncestor(findOldest));
                mainPanel = new JPanel();
                results = new JTextArea();
                copyButton = new JButton("Copy");
                popUp.setSize(300, 350);
                String oldest = findOldest();
                results.setText(oldest);
                copyButton.addActionListener((ActionEvent e) -> {
                    StringSelection string = new StringSelection(oldest);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(string, null);
                });
                mainPanel.add(copyButton, BorderLayout.NORTH);
                mainPanel.add(results);
                popUp.add(mainPanel);
                popUp.pack();
                popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                popUp.setVisible(true);
            } catch (NullPointerException e) {
                Label error = new Label("Oldest person was unable to be found.");
                mainPanel.add(error, BorderLayout.CENTER);
                popUp.add(mainPanel);
                popUp.setVisible(true);
            }
        }

        /*  Function that communicates with Model to obtain data about oldest person.
            @returns = string with data of oldest person.
            @throws = NullPointerException if the oldest person is not found.
         */
        private String findOldest() throws NullPointerException {
            ArrayList<String> columns = model.getColumns();
            StringBuilder result = new StringBuilder();
            ArrayList<String> data = model.findOldest();
            Object[] headers = model.constructHeaders(columns);
            for (int i = 0; i < data.size(); i++) {
                result.append(headers[i] + " : " + data.get(i) + "\n");
            }
            return result.toString();
        }
    }
}
