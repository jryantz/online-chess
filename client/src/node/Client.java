package node;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    DataOutputStream output;

    /**
     * Prepares the client for execution.
     */
    public Client() {

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
            Scanner s = new Scanner(new InputStreamReader(System.in));
            while(s.hasNextLine()) {
                output.writeBytes(s.nextLine() + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    } // end Client.

} // end class Client.

class Connection extends Thread {

    Socket serverSocket;
    String fromClient;

    /**
     * Constructor for building the connection.
     * Prepares the client socken and the input/output stream.
     * Starts the thread.
     *
     * @param incomingSocket the socket that connects to the server.
     */
    public Connection (Socket incomingSocket) {

        serverSocket = incomingSocket;

        this.start();

    } // end Connection.

    /**
     * Begin execution of the receiver thread.
     */
    public void run() {

        boolean cont = true;

        try {
            while (cont) {
                BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

                if((fromClient = in.readLine()) != null) {
                    System.out.println(fromClient);
                }
            }
        } catch (IOException e) {
            System.out.println("Error.");
            e.printStackTrace();
        }

        System.exit(0);

    } // end run.

} // end class Connection.