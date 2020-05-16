import javax.swing.*;

/* This program reads in .csv files and displays
   them in a GUI to the user.
   There are different operations that the user can
   do on the data such as searching and copying data.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}
