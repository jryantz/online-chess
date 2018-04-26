package node;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import main.Main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {

    DataOutputStream output;

    String username;
    ObservableList<String> connectedUsers;

    /**
     * Prepares the client for execution.
     *
     * @param
     * @param incomingUsername
     * @param incomingConnectedUsers
     */
    public Client(String incomingUsername, ObservableList<String> incomingConnectedUsers) { //Takes in username from GUI

        username = incomingUsername;
        connectedUsers = incomingConnectedUsers;

        try {

            Socket sock = new Socket("127.0.0.1", 4000);

            System.out.println("Chess Client Started...");

            // Create the receiver thread.
            new Connection(sock, connectedUsers, username);

            // Setup the output data stream.
            try {
                output = new DataOutputStream(sock.getOutputStream());
            } catch (IOException e) {
                System.out.println("Connection: " + e.getMessage());
            }

            output.writeBytes("--new " + username + "\n"); // Sends the username that was typed in the GUI to the server.

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    } // end Client.

    public void sendQuitToServer() {

        try {
            output.writeBytes("--exit " + username + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    } // end sendQuitToServer.

    public void sendGameRequestToServer(String usernameOfPlayerToJoin) {

        try {
            output.writeBytes("--join " + username + " " + usernameOfPlayerToJoin);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    } // end sendGameRequestToServer.

} // end class Client.

class Connection extends Thread {

    Socket serverSocket;
    String fromServer;

    ObservableList<String> connectedUsers;
    String thisUser;

    /**
     * Constructor for building the connection.
     * Prepares the client socken and the input/output stream.
     * Starts the thread.
     *
     * @param
     * @param incomingSocket the socket that connects to the server.
     * @param connectedUsersIn
     */
    public Connection(Socket incomingSocket, ObservableList<String> connectedUsersIn, String thisUserIn) {

        serverSocket = incomingSocket;
        connectedUsers = connectedUsersIn;
        thisUser = thisUserIn;

        this.start();

    } // end Connection.

    /**
     * Begin execution of the receiver thread.
     */
    public void run() {

        BufferedReader in;
        boolean cont = true;

        System.out.println(serverSocket);

        try {
            while (cont) {

                in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

                fromServer = in.readLine();

                System.out.println(fromServer);

                if (fromServer.startsWith("--")) {
                    String[] command = fromServer.toLowerCase().substring(2).split(" ");

                    //System.out.println("Command");

                    if (command[0].equalsIgnoreCase("alert")) {
                        // Do nothing, just print...
                        System.out.println(fromServer.substring(8));
                    } else if (command[0].equalsIgnoreCase("exit")) {
                        System.exit(0);
                    } else if (command[0].equalsIgnoreCase("names")) {
                        System.out.println("Names: " + fromServer.substring(8));
                        Main.setNames(fromServer.substring(8));

                        getConnectedClients(connectedUsers, thisUser); // Update the GUI to show all the clients.
                    }
                }


            }

        } catch (IOException e) {
            System.out.println("Error.");
            e.printStackTrace();
        }

        System.exit(0);

    } // end run.

    /**
     * A method for setting the names of clients inside the GUI! This
     * method is constantly called in the thread to update the GUI properly.
     *
     * @param connectedUsers
     * @return
     */
    public static void getConnectedClients(ObservableList<String> connectedUsers, String thisUser) {

        if (Main.getNames() != null) {

            Platform.runLater(() -> {

                if (Main.getNames() != null) {

                    connectedUsers.removeAll(connectedUsers);
                    String str = Main.getNames();
                    List<String> namesList = Arrays.asList(str.split(",")); // Splits the received names.

                    for (int i = 0; i < namesList.size(); i++) {
                        str = namesList.get(i);

                        if (str.equalsIgnoreCase(thisUser)) {
                            // Do not add to the list.
                        } else {
                            connectedUsers.add(str); // Adds the names to the list.
                        }
                    }

                }

            });

            //System.out.println("SUPER TEST: " + Main.getNames());

        }

    } // end getConnectedClients.

} // end class Connection.