package node;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    /**
     * Prepares the server for execution.
     */
    public Server() {

        try {
            int serverPort = 4000;
            ServerSocket serverSocket = new ServerSocket(serverPort);

            System.out.println("Chess Server Started...");
            System.out.println(serverSocket.getLocalSocketAddress());

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
    String toClient;

    DataInputStream input;
    DataOutputStream output;
    Socket clientSocket;

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

    public void run() {

        boolean cont = true;

        while (cont) {

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                fromClient = in.readLine();
                System.out.println("Received: " + fromClient);

                if (fromClient.toLowerCase().equals("exit")) {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        // Closing failed.
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
