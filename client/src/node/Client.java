package node;

import gui.chess.GameGUI;
import gui.chess.Piece;
import gui.chess.PieceTypes;
import gui.chess.UserColor;
import gui.connect.ConnectionGUI;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import static gui.chess.GameGUI.holdPieces;

public class Client {

    private static Piece piece;
    DataOutputStream output;
    static DataOutputStream outputMove;

    String username;
    static PieceTypes UserPiecetype;
     String oldMousePressX;
    String oldMousePressY;
    static int numTurns;
    static int storeTime;


    /**
     * Prepares the client for execution.
     *
     * @param incomingUsername
     * @param incomingConnectedUsers
     */
    public Client(String incomingUsername, ObservableList<String> incomingConnectedUsers) {

        username = incomingUsername;

        try {

            Socket sock = new Socket("127.0.0.1", 4000);
            //Socket sock = new Socket("10.20.234.175", 4000);

            System.out.println("Chess Client Started...");

            // Create the receiver thread.
            new Connection(sock, incomingConnectedUsers, username);

            // Setup the output data stream.
            try {
                output = new DataOutputStream(sock.getOutputStream());
                setOutputStream(output);
                Main.setUsernameToSendForMove(username);

            } catch (IOException e) {
                System.out.println("Connection: " + e.getMessage());
            }

            output.writeBytes("--new " + username + "\n"); // Sends the username that was typed in the GUI to the server.

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    } // end Client.

    private void setOutputStream(DataOutputStream output) {
        outputMove=output;
    }

    public static DataOutputStream getMoveOutput(){
        return outputMove;
    }
    public void sendQuitToServer() {

        try {
            output.writeBytes("--exit " + username + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    } // end sendQuitToServer.

    public void sendGameRequestToServer(String usernameOfPlayerToJoin) {

        try {
            output.writeBytes("--join " + username + " " + usernameOfPlayerToJoin + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    } // end sendGameRequestToServer.

    /**
     * Takes in parameters based on user input and emits to the server, conditionally.
     *
     * @param accept true if the user accepted and false if the user rejected.
     * @param usernameOfRequestingPlayer the username of the player that initiated the request.
     */
    public void sendAcceptOrRejectToServer(boolean accept, String usernameOfRequestingPlayer) {

        UserColor.settingUserColor();
        if(UserColor.getUserColor().equals("WHITE") && (numTurns==1)){
            GameGUI.setTurn(true);
        }

        if (accept) {
            try {
                output.writeBytes("--accept " + username + " " + usernameOfRequestingPlayer + " " + UserColor.getUserColor() + "\n");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                output.writeBytes("--reject " + username + " " + usernameOfRequestingPlayer + "\n");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    } // end sendAcceptOrRejectToServer


    public static void sendUserMoveToServer( PieceTypes type, double oldMousePressX, double oldMousePressY){
        System.out.println("SENDING MOVE TO SERVER THAT IS : " + type + " " + oldMousePressX + " " + oldMousePressY);
        GameGUI.setTurn(false);
        Main.pause();
        holdPieces.setMouseTransparent(true);
        UserPiecetype=type;

        try {
            getMoveOutput().writeBytes("--move " + Main.getUserMakeAMove() + " " + type + " " + oldMousePressX + " " + oldMousePressY + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end sendUserMoveToServer

} // end class Client.

class Connection extends Thread {

    Socket serverSocket;
    String fromServer;
    Pane gameGUIRootForPieceMove;

    ObservableList<String> connectedUsers;

    String thisUser;
    private Piece piece;

    /**
     * Constructor for building the connection.
     * Prepares the client socken and the input/output stream.
     * Starts the thread.
     * @param incomingSocket the socket that connects to the server.
     * @param connectedUsersIn
     * @param thisUserIn
     */
    public Connection(Socket incomingSocket, ObservableList<String> connectedUsersIn, String thisUserIn) {
        Piece piece;
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
                    String[] command = fromServer.substring(2).split(" ");

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
                    } else if (command[0].equalsIgnoreCase("request")) {
                        System.out.println(command[1] + " would like to play chess with you.");
                        Main.setRequestingUser(command[1]);

                        updateUserWantsToPlayLabel();
                    } else if (command[0].equalsIgnoreCase("accept")) {
                        //Made a new array because I did not know if it would be confusing to use command
                        String[] otherUserColor = fromServer.substring(2).split(" ");
                        System.out.println("Game accepted. User is " + otherUserColor[1]);
                        launchChessGame(otherUserColor[1]);

                    } else if (command[0].equalsIgnoreCase("move")){
                        System.out.println("RECEIVED MOVE FROM SERVER " + thisUser);
                        System.out.println(command[1] + " " + command[2] + " " + command[3]);
                        Main.setMove(command[1], command[2] , command[3]);
                        GameGUI.setTurn(true);
                        Main.setTime(Main.getTimeAfterSendMove());
                        Main.start();
                        movePiecesInThisGUI();
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
     * This method updates the user's GameGUI when move from another user has been received.
     * If the type of the piece received = one of the pieces in the pieceGroup (which contains
     * all of the chess pieces), move that particular piece in the pieceGroup to the
     * coordinate received from the socket.
     */
    private void movePiecesInThisGUI() {
        if (Main.getSentPieceType() != null) {

            Platform.runLater(() -> {

                GameGUI.setTurnNum(GameGUI.getTurnNumber()+1);
                if( Main.getSentPieceType() != null){

                    holdPieces.setMouseTransparent(false);
                    for(int i=0; i<holdPieces.getChildren().size(); i++){
                        piece= (Piece) holdPieces.getChildren().get(i);

                        PieceTypes type =piece.getType();
                        if(type.equals(Main.getSentPieceType())){

                            int xCoordinate = Main.getXCoordinate();
                            if(!(piece.getScene().getX() ==xCoordinate) && !(piece.getScene().getY() ==Main.getYCoordinate())) {
                                    piece.move(type, xCoordinate, Main.getYCoordinate());

                            }

                        }
                    }
                }

            });


        }
    }// end movePiecesInThisGUI


    private void launchChessGame(String otherUserColor) {

        Platform.runLater(() -> {

            UserColor.setUserColor(otherUserColor);
            if(UserColor.getUserColor().equals("WHITE")){
                GameGUI.setTurn(true);
                Main.setTime(0);
            }else if(UserColor.getUserColor().equals("BLACK")){
                GameGUI.setTurn(false);
                Main.setLastTime(0);
            }

            new GameGUI().start(new Stage());
            ConnectionGUI.primaryStage.hide();

        });

    } // end launchChessGame.

    private void updateUserWantsToPlayLabel() {

        Platform.runLater(() -> {

            ConnectionGUI.playChessButton.setDisable(true);
            ConnectionGUI.wantToPlayChessLabel.setText(Main.getRequestingUser() + " wants to play chess with you!");
            ConnectionGUI.userDecisionYesSubmitButton.setDisable(false);
            ConnectionGUI.userDecisionNoSubmitButton.setDisable(false);

        });

    } // end updateUserWantsToPlayLabel.

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