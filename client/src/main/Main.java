package main;

import gui.chess.GameGUI;
import gui.chess.Piece;
import gui.chess.PieceTypes;
import gui.connect.ConnectionGUI;
import javafx.application.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static String names;
    private static String userWantsToPlay;
    private static String userMakeAMove;
    private static PieceTypes sentPieceType;
    private static int pieceXCoordinate;
    private static int  pieceYCoordinate;
    private static String firstMove = "FALSE";
    private static float moveTime =0;
    private static int minuteTime =0;
    private static int hourTime =0;
    private static boolean pauseTime;
    private static ExecutorService service = Executors.newCachedThreadPool();
    private static float elapsedTime;


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

    /**
     * This method sets the move and convers the piece type to a string once to send it across
     * the socket
     * @param pieceType
     * @param xCoordinate
     * @param yCoordinate
     */
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

    /**
     * This method gets the x coordinate to send across the socket
     * @return
     */
    public static int getXCoordinate(){
        return pieceXCoordinate;
    }

    /**
     * This method gets the Y coordinate to send across the socket
     * @return
     */
    public static int getYCoordinate(){
        return pieceYCoordinate;
    }

    /**
     * This method sets the time for the timer which keeps increasing.
     * @param currentTime
     */
    public static void setTime(float currentTime){
        if(GameGUI.getTurn()==true) {
            moveTime = currentTime;
            service.submit(() -> {
                while (!pauseTime) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    moveTime++;
                    if(moveTime==60){
                        moveTime=moveTime/60;
                        minuteTime++;
                    }
                    if(minuteTime==60){
                        minuteTime=minuteTime/60;
                        hourTime++;
                    }
                   GameGUI.updateTimer(hourTime, (int) moveTime, minuteTime);
                }
                setLastTime(moveTime);
            });
        }
    }

    /**
     * This method gets the current time
     * @return
     */
    public static float getCurrentTime(){
        return moveTime;
    }

    /**
     * This method starts the thread timer when it's  the
     * persons turn
     */
    public static void start(){
        pauseTime=false;
    }

    /**
     * This method pauses the thread timer when it's not
     * the persons turn
     */
    public static void pause(){
        pauseTime=true;
    }

    /**
     * This method keeps track of the number where the timer
     * has left off after the person ends their turn
     * @return
     */
    public static float getTimeAfterSendMove(){
        System.out.println("TIME ELAPSED " + elapsedTime);
        return elapsedTime;
    }

    /**
     * This method sets the timer of what the number of time
     * it left off on before the person ends their turn
     * @param moveTime
     */
    public static void setLastTime(float moveTime) {
        elapsedTime=moveTime;
    }

} // end class Main.
