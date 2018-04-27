package node;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

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

            HashMap<String, Socket> userList = new HashMap<>();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println(clientSocket.getLocalSocketAddress());
                new Connection(clientSocket, userList);
            }

        } catch (IOException e) {
            System.out.println("Listen: " + e.getMessage());
        }

    } // end Server.

} // end class Server.

class Connection extends Thread {

    String fromClient;
    Socket clientSocket;
    DataOutputStream output;

    HashMap<String, Socket> users = new HashMap<>();

    /**
     * Constructor for building the connection.
     * Prepares the client socken and the input/output stream.
     * Starts the thread.
     *
     * @param incomingClientSocket the socket that will be used to communicate with various users.
     * @param incomingUserList the list of all users that can be communicated with.
     */
    public Connection(Socket incomingClientSocket, HashMap<String, Socket> incomingUserList) {

        try {

            clientSocket = incomingClientSocket;
            users = incomingUserList;

            //System.out.println(clientSocket); // Print the added client socket.

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

        BufferedReader in;
        boolean cont = true;

        while (cont) {

            try {

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                fromClient = in.readLine();

                if (fromClient != null) {
                    System.out.println("Received: " + fromClient);

                    if (fromClient.startsWith("--")) {
                        String[] command = fromClient.substring(2).split(" ");

                        //System.out.println("Command");
                        //output.writeBytes("[Command] " + command[0] + "\n");

                        if (command[0].equalsIgnoreCase("exit")) {
                            System.out.println("Exit user: " + command[1]);

                            users.remove(command[1]);
                            output.writeBytes("--exit\n");

                            System.out.print("Remaining users: ");

                            for (int i = 0; i < users.size(); i++) {
                                System.out.print(users.keySet().toArray()[i]);
                            }

                            System.out.print("\n");
                            emitUserList(users);
                        } else if (command[0].equalsIgnoreCase("new")) {
                            System.out.println("Add new user: " + command[1]);
                            users.put(command[1], clientSocket); // Puts the new user in the hashmap.
                            emitUserList(users); // Emit the list of all users to every connected sockets.
                        } else if (command[0].equalsIgnoreCase("join")) {
                            System.out.println(command[1] + " wants to play chess with " + command[2] + ".");

                            DataOutputStream requestPlay = new DataOutputStream(users.get(command[2]).getOutputStream());
                            requestPlay.writeBytes("--request " + command[1] + "\n");
                        } else if (command[0].equalsIgnoreCase("accept")) {

                        } else if (command[0].equalsIgnoreCase("reject")) {

                        } else if (command[0].equalsIgnoreCase("leave")) {

                        } else if (command[0].equalsIgnoreCase("move")) {
                            // Format: --move uid piece from-location to-location
                            Move.valid(command[2], command[3], command[4]);
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

        }

        System.exit(0);

    } // end run.

    /**
     * This method sends all of the connected users in the server the names of the connected users.
     * StringJoiner is used to send a full string of names to the clients. If there is only 1 person
     * in the hashmap, the server is put into a "waiting" state
     *
     * @param activeUserList
     */
    public void emitUserList(HashMap<String, Socket> activeUserList) {

        ArrayList<Socket> clientSockets = new ArrayList<>();
        ArrayList<String> clientNames = new ArrayList<>();

        String names;

        try {

            for (String username : activeUserList.keySet()) {

                String userKey = username.toString();
                Socket userSocket = activeUserList.get(username);

                clientNames.add(userKey); // Adds the connections only of the connected users to an array list.
                clientSockets.add(userSocket);

            }

            System.out.println(clientNames.toString());

            StringJoiner nameList = new StringJoiner(",");  // String of connected users of all the clients.

            for (int j = 0; j < clientNames.size(); j++) {

                String current = clientNames.get(j);
                nameList.add(current);

            }

            names = nameList.toString();

            for (int i = 0; i < clientSockets.size(); i++) {

                Socket s = clientSockets.get(i);
                DataOutputStream sender = new DataOutputStream(s.getOutputStream());

                System.out.println(s);

                if (clientSockets.size() == 1) {
                    String waiting = "Waiting for other users to connect.";
                    sender.writeBytes("--alert " + waiting + "\n");
                } else {
                    System.out.println("--names " + names);
                    sender.writeBytes("--names " + names + "\n"); // Sends the usernames of all those connected to the client
                    sender.flush();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    } // end emitUserList.

} // end class Connection.

