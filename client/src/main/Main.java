package main;

import javafx.application.Application;
import chess.GUI;
import node.Client;

public class Main {

    /**
     * Main method that begins execution of the program.
     *
     * @param args any arguments that need to be supplied to start the program.
     */
    public static void main(String[] args) {

        Application.launch(GUI.class);
        Client c = new Client();

    } // end main.

} // end class Main.
