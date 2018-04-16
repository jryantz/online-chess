package node;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import chess.*;

public class Server {

    /**
     * Prepares the server for execution.
     */
    public Server() {

        try {
            int serverPort = 4000;
            ServerSocket serverSocket = new ServerSocket(serverPort);

            System.out.println("Chess Server Started...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println(clientSocket.getLocalSocketAddress());
                Connection c = new Connection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen: " + e.getMessage());
        }

    } // end Server.

} // end class Server.

class Connection extends Thread {

    String fromClient;

    DataInputStream input;
    DataOutputStream output;
    Socket clientSocket;

    HashMap<String, ArrayList> games = new HashMap<>();

    /**
     * Constructor for building the connection.
     * Prepares the client socken and the input/output stream.
     * Starts the thread.
     *
     * @param incomingClientSocket
     */
    public Connection (Socket incomingClientSocket) {

        try {
            clientSocket = incomingClientSocket;

            input = new DataInputStream(clientSocket.getInputStream());
            output = new DataOutputStream(clientSocket.getOutputStream());

            this.start();
        } catch (IOException e) {
            System.out.println("Connection: " + e.getMessage());
        }

    } // end Connection.

    /**
     * Begin execution of the receiver thread.
     */
    public void run() {

        boolean cont = true;

        while (cont) {

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                fromClient = in.readLine();

                if (fromClient != null) {
                    System.out.println("Received: " + fromClient);
                    output.writeBytes("[Resend] " + fromClient);

                    if (fromClient.equalsIgnoreCase("exit")) {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            // Closing failed.
                        }

                        cont = false;
                    }

                    cont = false;
                }
            } catch (IOException e) {
                //System.out.println("IO: " + e.getMessage());
                e.printStackTrace();
            }

        }

        System.exit(0);

    } // end run.

} // end class Connection.
