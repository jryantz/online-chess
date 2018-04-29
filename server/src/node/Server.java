package node;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

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

    private static String userColor;

    HashMap<String, Socket> users = new HashMap<>();
    private static HashMap<HashMap, HashMap> usersInGame = new HashMap<>();
    private static ArrayList<String> usernames = new ArrayList<>();
    private static ArrayList<Socket> sockets = new ArrayList<>();

    /**
     * Constructor for building the connection.
     * Prepares the client socken and the input/output stream.
     * Starts the thread.
     *
     * @param incomingClientSocket the socket that will be used to communicate with various users.
     * @param incomingUserList     the list of all users that can be communicated with.
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

                            usernames.add(command[1]);
                            sockets.add(clientSocket);

                            DataOutputStream requestPlay = new DataOutputStream(users.get(command[2]).getOutputStream());
                            requestPlay.writeBytes("--request " + command[1] + "\n");
                        } else if (command[0].equalsIgnoreCase("accept")) {
                            String[] getColor = fromClient.substring(2).split(" "); // Getting the color out of the response.

                            String color = getColor[3];
                            System.out.println(command[1] + " is color : " + getColor[3] + " and accepted to play a game with " + command[2] + ".");
                            DataOutputStream acceptPlay = new DataOutputStream(users.get(command[2]).getOutputStream());

                            if (color.startsWith("BL")) { //Had to do startsWith because it was not recognizing black for some reason
                                userColor = "WHITE";
                            } else {
                                userColor = "BLACK";
                            }

                            putUserPairsIntoHash(usernames,sockets, command[1], command[2], clientSocket); //puts user pairs together

                            System.out.println(usersInGame);
                            acceptPlay.writeBytes("--accept " + userColor + "\n");

                        } else if (command[0].equalsIgnoreCase("reject")) {
                            System.out.println(command[1] + " rejected the game with " + command[2] + ".");

                            DataOutputStream acceptPlay = new DataOutputStream(users.get(command[2]).getOutputStream());
                            acceptPlay.writeBytes("--reject\n");
                        } else if (command[0].equalsIgnoreCase("leave")) {

                        }else if (command[0].equalsIgnoreCase("move")) {
                            // Format: --move uid piece from-location to-location
                            // Move.valid(command[2], command[3], command[4]);
                                 DataOutputStream sendMove = null;
                                 sendMove = new DataOutputStream(findUserToSendMoveTo(command[1], usersInGame).getOutputStream()); //outputs socket that was returned here
                                 sendMove.writeBytes("--move " + command[2] + " " + command[3] + " " + command[4] + "\n");
                             }
                        }
                    }
                } catch (IOException e) {
                e.printStackTrace();
            }

           }
        System.exit(0);
        } // end run.

    /**
     * This method takes the username that just made the move and the HashMap that contains all the users
     * who are paired together/ playing a game together. This method finds the partner/other player of the client
     * who just made a move. Once the other player is found in the map, their socket is returned and the move is
     * sent to them only!
     * @param userWhoMadeMove
     * @param usersInGame
     * @return
     */
    private static Socket findUserToSendMoveTo(String userWhoMadeMove, HashMap<HashMap,HashMap> usersInGame) {
        System.out.println(userWhoMadeMove + " made a move, now send it");
        Socket sockettoSend = null;

        for (HashMap miniHash: usersInGame.keySet()) {

            HashMap valueHash = usersInGame.get(miniHash); //get value hashmaps (user2 stored values)

                for(Object user1Key: miniHash.keySet()){//loop goes over hashMap1 inside a HashMap<hashMap1, hashMap2>
                    String usernames = user1Key.toString();
                    Socket socketValues = (Socket) miniHash.get(user1Key); //values of hashMap2
                    if(usernames.equals(userWhoMadeMove)) {
                        sockettoSend= getUserToSendMoveAsValue(miniHash); //User=key, need other user=value
                    }
                }
                for(Object user2InValue: valueHash.keySet()){//loop goes over hashMap2 inside a HashMap<hashMap1, hashMap2>
                String usernames = user2InValue.toString();
                Socket socketValues = (Socket) valueHash.get(user2InValue); //values of hashMap2
                    if(usernames.equals(userWhoMadeMove)) {
                        sockettoSend=  getUserToSendMoveAsKey(valueHash);
                    }
                }
        }
        System.out.println("SENDING COMMANDS TO THIS SOCKET " + sockettoSend);
            return sockettoSend;
    }

    /**
     * This method iterates over the hashmap and returns the socket that is needed to send
     * the move.
     * @param valueHash
     * @return
     */
    private static Socket getUserToSendMoveAsKey(HashMap valueHash) {
        Socket sendToThisSocket = null;
        HashMap<String, Socket> temp = null;
        for(Object o: usersInGame.keySet()){
            if(usersInGame.get(o).equals(valueHash)){
                System.out.println(o + " is the key");
                temp= (HashMap<String, Socket>) o;
            }
        }
        for(Object userSocket: temp.keySet()){
            Socket socketValues=temp.get(userSocket);
            sendToThisSocket=socketValues;
        }
        return sendToThisSocket;
    }

    /**
     *
     * @param user1Key
     * @return
     */
    private static Socket getUserToSendMoveAsValue(HashMap user1Key)  {
            Socket sendToThisSocket = null;
            HashMap<String, Socket> temp;
            System.out.println( "The value is : " + usersInGame.get(user1Key));
            temp=usersInGame.get(user1Key);
        for(Object userSocket: temp.keySet()){//get the socket out of this map!
            Socket socketValues = temp.get(userSocket); //values of hashMap2
            sendToThisSocket=socketValues;
            }

            return sendToThisSocket;
    }

    /**
     * This method puts all the users who are going to play chess in pairs. The users are stored in arrayLists (1 for
     * username, the other for socket). The method calls another method, which pairs the users together appropriately
     * in the hashmap usersInGame<HashMap(user1), HashMap(user2)>. The key of usersInGame is a hashmap of <username,socket> of 1 user,
     * and the value of usersInGame is another hashmap<username, socket> of the other use playing a game with them.
     * @param clientSocket
     */
    private void putUserPairsIntoHash(ArrayList usernames, ArrayList sockets, String userNotInList, String userInListAlready, Socket clientSocket) {
        usernames.add(userNotInList);
        sockets.add(clientSocket);

        putUsernamesAndSocketsIntoHash(userInListAlready, usersInGame, usernames, sockets, userNotInList);//calls putUsernamesAndSocketsIntoHash
    }

    /**
     * This method pairs all the users who are going to play together.This method takes in the usernames/sockets, 1 who requests to play and
     * the other who accepts, and both user informations are put into 2 hashmaps. 1 hashmap acts as key, the other as value <player1, player2>
     * @param userInListAlready
     * @param usersInGame
     * @param usernames
     * @param sockets
     * @param userNotInList
     */
    private void putUsernamesAndSocketsIntoHash(String userInListAlready, HashMap<HashMap, HashMap> usersInGame, ArrayList<String> usernames, ArrayList<Socket> sockets, String userNotInList) {
        List<Object> storeUserInfo = new ArrayList<>();
        HashMap<String, Socket> temp = new HashMap<>();
        HashMap<String, Socket> temp2 = new HashMap<>();
        String name=null;
        Socket userSock=null;

        if(!storeUserInfo.contains(userNotInList)  && !storeUserInfo.contains(userInListAlready) ) {
            for (int i = 0; i < usernames.size(); i++) {
                name = usernames.get(i);
                if (name.equals(userInListAlready)) {
                    userSock = sockets.get(i);
                    temp.put(name, userSock);
                    storeUserInfo.add(name);
                    storeUserInfo.add(userSock);
                }if(name.equals(userNotInList)){
                    userSock = sockets.get(i);
                    storeUserInfo.add(name);
                    storeUserInfo.add(userSock);
                    temp2.put(name, userSock);
                }
            }
        }
        usersInGame.put(temp, temp2);
    }

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



