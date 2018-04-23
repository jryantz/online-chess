package node;

import ConnectGUI.FindaGame;
import javafx.application.Application;
import main.Main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
private String Username;
    DataOutputStream output;
    String connectedClients;


    /**
     * Prepares the client for execution.
     *
     * @param
     * @param username
     */
    public Client(String username) { //Takes in username from GUI


        try {

            Socket sock = new Socket("127.0.0.1", 4000);


            System.out.println("Chess Client Started...");


            // Create the receiver thread.
            new Connection(sock);



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
    public String connectedClients;

    /**
     * Constructor for building the connection.
     * Prepares the client socken and the input/output stream.
     * Starts the thread.
     *
     * @param incomingSocket the socket that connects to the server.
     * @param
     */
    public Connection(Socket incomingSocket) {

        serverSocket = incomingSocket;



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
                        System.out.println("I AM THE GOD: " + Main.getNames());
                    }
                }

                //System.out.print(fromServer + " ");

            }
        } catch (IOException e) {
            System.out.println("Error.");
            e.printStackTrace();
        }

        System.exit(0);

    } // end run.

    /**
     * A method for returning the names of clients.
     * @param fromClient
     * @return
     */
    public String getConnectedClients(String fromClient) {

        connectedClients=fromClient;
        return connectedClients;

    } // end getConnectedClients.

} // end class Connection.