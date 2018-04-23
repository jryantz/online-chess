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

            HashMap<String, Socket> Users = new HashMap<>();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println(clientSocket.getLocalSocketAddress());
                Connection c = new Connection(clientSocket, Users);

            }
        } catch (IOException e) {
            System.out.println("Listen: " + e.getMessage());
        }

    } // end Server.

} // end class Server.

class Connection extends Thread {

    String fromClient;
    DataOutputStream output;
    Socket clientSocket;

    int userSize = 1;

    HashMap<String, ArrayList> games = new HashMap<>();
    HashMap<String, Socket> users = new HashMap<>();

    /**
     * Constructor for building the connection.
     * Prepares the client socken and the input/output stream.
     * Starts the thread.
     *
     * @param incomingClientSocket
     * @param Users
     */
    public Connection(Socket incomingClientSocket, HashMap<String, Socket> Users) {

        try {
            clientSocket = incomingClientSocket;
            users = Users;

            System.out.println(clientSocket);

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

                    users.put(fromClient, clientSocket); //puts all those connected in the Hashmap
                    users = getRecentUsersConnections(users); //gets recent connected people
                    sendUserstoAllUsers(users); //method call to send clients only the names of all connected users

                    if (fromClient.equalsIgnoreCase("exit")) {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            // Closing failed.
                        }

                        cont = false;
                    }

                    if (fromClient.startsWith("--")) {
                        String[] command = fromClient.toLowerCase().substring(2).split(" ");

                        System.out.println("Command");
                        output.writeBytes("[Command] " + command[0] + "\n");

                        if (command[0].equalsIgnoreCase("new")) {

                        } else if (command[0].equalsIgnoreCase("list")) {

                        } else if (command[0].equalsIgnoreCase("join")) {

                        } else if (command[0].equalsIgnoreCase("leave")) {

                        } else if (command[0].equalsIgnoreCase("move")) {
                            // Format: --move uid piece from-location to-location
                            Move.valid(command[2], command[3], command[4]);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error.");
                e.printStackTrace();
            }

        }

        System.exit(0);

    } // end run.

    /**
     * This method gets an updated hashmap of the connected users
     *
     * @param users
     * @return
     */
    public HashMap<String, Socket> getRecentUsersConnections(HashMap<String, Socket> users) {
        return users;
    } //end getRecentUsersConnections

    /**
     * This method sends all of the connected users in the server the names of the connected users.
     * StringJoiner is used to send a full string of names to the clients. If there is only 1 person
     * in the hashmap, the server is put into a "waiting" state
     *
     * @param users
     */
    public void sendUserstoAllUsers(HashMap<String, Socket> users) {

        DataOutputStream output = null;

        ArrayList<Socket> storeClientInfo = new ArrayList<>();
        ArrayList<String> ListNames = new ArrayList<>();
        String names = null;

        try {

            for (String userName : users.keySet()) {
                String key = userName.toString();
                Socket value = users.get(userName);
                storeClientInfo.add(value);
                ListNames.add(key); //adds the connections only of the connected users to an array list
            }
            System.out.println(ListNames.toString());


            StringJoiner joinNames = new StringJoiner(",");  //string of connected users of all the clients
            for (int j = 0; j < ListNames.size(); j++) {

                names = ListNames.get(j);
                joinNames.add(names);
            }
            names = joinNames.toString();

            for (int i = 0; i < storeClientInfo.size(); i++) {
                Socket s = storeClientInfo.get(i);
                output = new DataOutputStream(s.getOutputStream());
                if (storeClientInfo.size() == 1) {
                    String waiting = "Waiting for others to connect";
                    output.writeBytes("--alert " + waiting + "\n");
                } else {
                    output.writeBytes("--names " + names + "\n"); //Sends the usernames of all those connected to the client
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    } //End method sendUserstoAllUsers

} // end class Connection.

