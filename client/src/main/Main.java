package main;

import gui.connect.ConnectionGUI;
import javafx.application.Application;

public class Main {

    private static String names;
    private static String userWantsToPlay;

    /**
     * Main method that begins execution of the program.
     *
     * @param args any arguments that need to be supplied to start the program.
     */
    public static void main(String[] args) {

        //Application.launch(GameGUI.class);

        Application.launch(ConnectionGUI.class);


        //Client c = new Client();

    } // end main.

    public static void setNames(String in) {

        names = in;

    } // end setNames.

    public static String getNames() {

        return names;

    } // end getNames.

    public static void setUserWantsToPlay(String user) {

        userWantsToPlay = user;

    } // end setUserWantsToPlay.

    public static String getUserWhoPlay() {

        return userWantsToPlay;

    } // end getUserWhoPlay.


} // end class Main.
