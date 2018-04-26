package main;

import gui.connect.ConnectionGUI;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;

public class Main {

    private static String names;
    private static ObservableValue<? extends String> observeNames;

    /**
     * Main method that begins execution of the program.
     *
     * @param args any arguments that need to be supplied to start the program.
     */
    public static void main(String[] args) {

        //Application.launch(GUI.class);

        Application.launch(ConnectionGUI.class);

        //Client c = new Client();

    } // end main.

    public static void setNames(String in) {

        names = in;

    } // end setNames.

    public static String getNames() {

        return names;

    } // end getNames.

} // end class Main.
