package main;

import gui.chess.GameGUI;
import gui.chess.Piece;
import gui.chess.PieceTypes;
import gui.chess.Tile;
import gui.connect.ConnectionGUI;
import javafx.application.Application;
import javafx.scene.layout.Pane;

public class Main {

    private static String names;
    private static String userWantsToPlay;
    private static String userMakeAMove;
    private static PieceTypes sentPieceType;
    private static int pieceXCoordinate;
    private static int  pieceYCoordinate;



    /**
     * Main method that begins execution of the program.
     *
     * @param args any arguments that need to be supplied to start the program.
     */
    public static void main(String[] args) {

        Application.launch(ConnectionGUI.class);


    } // end main.

    public static void setNames(String in) {

        names = in;

    } // end setNames.

    public static String getNames() {

        return names;

    } // end getNames.

    public static void setRequestingUser(String user) {

        userWantsToPlay = user;

    } // end setUserWantsToPlay.

    public static String getRequestingUser() {

        return userWantsToPlay;

    } // end getUserWhoPlay.

    public static void setUsernameToSendForMove(String username){
        userMakeAMove=username;
    }

    public static String getUserMakeAMove(){
        return userMakeAMove;
    }


    public static void setMove(String pieceType, String xCoordinate, String yCoordinate) {
        Piece.convertStringToPieceTypes(pieceType);
        sentPieceType=Piece.getPieceTypeAfterConversion();
        pieceXCoordinate = Float.valueOf(xCoordinate.toString()).intValue();
        Piece.setPieceXCoordinate(pieceXCoordinate);
        pieceYCoordinate = Float.valueOf(yCoordinate.toString()).intValue();
        Piece.setPieceYCoordinate(pieceYCoordinate);
        //GameGUI.MovePieces(sentPieceType, pieceXCoordinate, pieceYCoordinate);
    }

    public static PieceTypes getSentPieceType(){
        return sentPieceType;
    }

    public static int getXCoordinate(){
        return pieceXCoordinate;
    }

    public static int getYCoordinate(){
        return pieceYCoordinate;
    }



} // end class Main.
