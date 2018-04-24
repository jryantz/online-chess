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

public class Client {
private String Username;
    DataOutputStream output;
    String connectedClients;
    ObservableList<String> connectedUseres;


    /**
     * Prepares the client for execution.
     * @param
     * @param username
     * @param connectedUseres
     */
    public Client(String username, ObservableList<String> connectedUseres) { //Takes in username from GUI
       this.connectedUseres=connectedUseres;

        try {

            Socket sock = new Socket("127.0.0.1", 4000);


            System.out.println("Chess Client Started...");


            // Create the receiver thread.
            new Connection(sock,connectedUseres);



            // Setup the output data stream.
            try {
                output = new DataOutputStream(sock.getOutputStream());

            } catch (IOException e) {
                System.out.println("Connection: " + e.getMessage());
            }


            // Scanner for debug testing (passing test messages to the server).
          //  Scanner s = new Scanner(new InputStreamReader(System.in));

           //while(s.hasNextLine()) {
               output.writeBytes(username+ "\n"); //Sends the username that was typed in the GUI to the server
          //  }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    } // end Client.



} // end class Client.

class Connection extends Thread {

    Socket serverSocket;
    String fromServer;
    ObservableList<String> connectedUseres;

    /**
     * Constructor for building the connection.
     * Prepares the client socken and the input/output stream.
     * Starts the thread.
     *  @param
     * @param incomingSocket the socket that connects to the server.
     * @param connectedUseres
     */
    public Connection(Socket incomingSocket, ObservableList<String> connectedUseres) {

        serverSocket = incomingSocket;
        this.connectedUseres=connectedUseres;


        this.start();

    } // end Connection.

    /**
     * Begin execution of the receiver thread.
     */
    public void run() {
        BufferedReader in;
        boolean cont = true;

        try {
            while (cont) {

              in= new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

                fromServer = in.readLine();

                if (fromServer.startsWith("--")) {
                    String[] command = fromServer.toLowerCase().substring(2).split(" ");

                    System.out.println("Command");

                    if (command[0].equalsIgnoreCase("alert")) {
                        // Do nothing, just print...
                        System.out.println(fromServer.substring(8));
                    } else if (command[0].equalsIgnoreCase("names")) {
                        Main.setNames(fromServer.substring(8));

                        //update GUI of newly connected clients
                     getConnectedClients(Main.getNames(), connectedUseres);
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
     * @param fromClient
     * @param connectedUseres
     * @return
     */
    public static void getConnectedClients(String fromClient, ObservableList<String> connectedUseres) {
        if(Main.getNames()!=null) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if(Main.getNames()!=null) {
                        connectedUseres.removeAll(connectedUseres);
                        String str = Main.getNames();
                        List<String> namesList = Arrays.asList(str.split(",")); //splits the received names
                        for(int i=0; i<namesList.size(); i++){
                            str= (String) namesList.get(i);
                            connectedUseres.add(str); //adds them to the list
                        }
                    }
                }
            });

            System.out.println("I AM THE GOD: " + Main.getNames());
        }
    } // end getConnectedClients.

} // end class Connection.