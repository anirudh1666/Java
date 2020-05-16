package uk.ac.ucl.model;

import uk.ac.ucl.dataFrame.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.toUpperCase;

public class Model
{
  // The example code in this class should be replaced by your Model class code.
  // The data should be stored in a DataFrame.
  // private DataFrame frame = ...

  private DataFrame frame;

  /* Loads the file into a DataFrame structure.
     @params = file : .csv file that should be loaded in.
   */
  public Model(File file) {
    DataLoader dataBase = new DataLoader(file);
    frame = dataBase.getLibrary();
  }

  /* Function that returns full names of everyone in database.
     This function can be used if you want to display all the patients on one page.
   */
  public List<String> getPatientNames() {
    List<String> patientNames = new ArrayList<>();
    Column firstNames = frame.searchForColumn("FIRST");
    Column lastNames = frame.searchForColumn("LAST");
    for (int i = 0; i < frame.getRowCount(); i++) {
      String name = firstNames.getRowValue(i) + " " + lastNames.getRowValue(i);
      patientNames.add(name);
    }
    return patientNames;
  }


  /* This function uses first name to search for the desired person.
     @params = firstName : first name of desired person
     @returns = List of strings containing all the info about person.
   */
  public List<String> searchFor(String firstName)
  {
    List<String> data = new ArrayList<>();
    Column firstNames = frame.searchForColumn("FIRST");
    int index = firstNames.findRow(firstName);
    if (index == -1 ) {
      return data;
    }
    ArrayList<String> columnNames = frame.getColumnNames();
    for (String columns : columnNames) {
      Column column = frame.searchForColumn(columns);
      String value = column.getRowValue(index);
      data.add(value);
    }
    return data;
  }

  public List<String> getColumnNames() {
    return (List<String>) frame.getColumnNames();
  }

  /* Function that is used to get all the patients that have their
     last name start with a certain letter. Used to display names alphabetically.
     @params = letter : letter that last names should start with
     @returns = List of patients with desired last names. In format LAST NAME FIRST NAME.
   */
  public List<String> getLastNames(Character letter) {
    letter = toUpperCase(letter);
    List<String> patients = new ArrayList<>();
    Column firstNames = frame.searchForColumn("FIRST");
    Column lastNames = frame.searchForColumn("LAST");
    for (int i = 0; i < lastNames.getRowCount(); i++) {
      String lastName = lastNames.getRowValue(i);
      if (lastName.charAt(0) == letter) {
        String name = lastNames.getRowValue(i) + " " + firstNames.getRowValue(i);
        patients.add(name);
      }
    }
    return patients;
  }
}
