package gui.chess;

import gui.connect.ConnectionGUI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;
import node.Client;


public class GameGUI extends Application {


    private static double beforeXConversion;
    private static double beforeYConversion;
    private static Pane root;
    private static boolean turnFromThread;
    private static int turnNumberForUser=0;
    public static Label timer2;
    private static Tile[][] Chessboard = new Tile[8][8]; // TILE GameGUI.
    private static Group boards = new Group(); // GameGUI TILE.
    public static Group holdPieces = new Group(); // GameGUI PIECES SIT ON TOP OF TILE.
    private static PieceTypes UserPiecetype;
    /**
     * This is a method for the GameGUI! Starts the stage!
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(createChessStuff());

        primaryStage.setTitle("FSU Chess Client");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(GameGUI.class.getResource("GameGUI.css").toExternalForm());

        primaryStage.show();

    } // end start.

    /**
     * This method helps create the board for the GameGUI. With this method, tile coordinates are also set,
     * where the pieces from the PieceTypes and Piece class are created and placed on the board. This method
     * essentially just provides the initial board with the pieces when a client starts the game. Given the
     * X,Y coordinates, the pieces are placed on the board.
     *
     * @return
     */
    public static Parent createChessStuff() {

        root = new Pane();
        root.setPrefSize(8 * 100 + 30 , 8 * 100+60);
        Label usernameLabel = new Label("User: " + Main.getUserMakeAMove());
        Label userColor = new Label(UserColor.getUserColor());
        usernameLabel.setTranslateY(830);
        userColor.setTranslateY(830);
        userColor.setTranslateX(300);
        Label letters = new Label("A" + "                 " + "B" + "                  " + "C" + "                  " + "D" +
                "                  " + "E" + "                 " + "F" + "                 " + "G" + "                 " + "H");
        letters.setTranslateY(800);
        letters.setTranslateX(50);

        Label numbers = new Label("1" + "                 " + "2" + "                  " + "3" + "                  " + "4" +
                "                  " + "5" + "                 " + "6" + "                 " + "7" + "                   " + "8");
        numbers.setTranslateY(400);
        numbers.setTranslateX(467);
        numbers.setRotate(-90);

        timer2 = new Label("Timer: " + 0);
        timer2.setText(String.valueOf(Main.getCurrentTime()));
        timer2.setTranslateY(830);
        timer2.setTranslateX(600);
        root.getChildren().addAll(boards, holdPieces,usernameLabel,timer2,userColor, letters, numbers );

        if (Main.getSentPieceType() == null) {
            for (int y = 0; y < 8; y++) {
                for (int x = 0; x < 8; x++) {
                    Tile tile = new Tile((x + y) % 2 == 0, x, y);
                    Chessboard[x][y] = tile;
                    boards.getChildren().add(tile);
                    Piece piece = null;
                    if (y == 1 && x==0) {
                        piece = makePieces(PieceTypes.BLACK_PAWN1, x, y); //POPULATE TOP BOARD
                    } if (y == 1 && x==1) {
                        piece = makePieces(PieceTypes.BLACK_PAWN2, x, y); //POPULATE TOP BOARD
                    } if (y == 1 && x==2) {
                        piece = makePieces(PieceTypes.BLACK_PAWN3, x, y); //POPULATE TOP BOARD
                    } if (y == 1 && x==3) {
                        piece = makePieces(PieceTypes.BLACK_PAWN4, x, y); //POPULATE TOP BOARD
                    } if (y == 1 && x==4) {
                        piece = makePieces(PieceTypes.BLACK_PAWN5, x, y); //POPULATE TOP BOARD
                    } if (y == 1 && x==5) {
                        piece = makePieces(PieceTypes.BLACK_PAWN6, x, y); //POPULATE TOP BOARD
                    } if (y == 1 && x==6) {
                        piece = makePieces(PieceTypes.BLACK_PAWN7, x, y); //POPULATE TOP BOARD
                    } if (y == 1 && x==7) {
                        piece = makePieces(PieceTypes.BLACK_PAWN8, x, y); //POPULATE TOP BOARD

                    } if ((y == 0 && x == 0)) {
                        piece = makePieces(PieceTypes.BLACK_ROOK1, x, y); //POPULATE TOP BOARD
                    } if (y == 0 && x==7) {
                        piece = makePieces(PieceTypes.BLACK_ROOK2, x, y); //POPULATE TOP BOARD

                    } if ((y == 0 && x == 1)) {
                        piece = makePieces(PieceTypes.BLACK_KNIGHT1, x, y); //POPULATE TOP BOARD
                    } if (y == 0 && x==6) {
                        piece = makePieces(PieceTypes.BLACK_KNIGHT2, x, y); //POPULATE TOP BOARD

                    } if ((y == 0 && x == 2)) {
                        piece = makePieces(PieceTypes.BLACK_BISHOP1, x, y); //POPULATE TOP BOARD
                    } if (y == 0 && x==5) {
                        piece = makePieces(PieceTypes.BLACK_BISHOP2, x, y); //POPULATE TOP BOARD

                    } if (y == 0 && x == 3) {
                        piece = makePieces(PieceTypes.BLACK_QUEEN, x, y); //POPULATE TOP BOARD

                    } if (y == 0 && x == 4) {
                        piece = makePieces(PieceTypes.BLACK_KING, x, y); //POPULATE TOP BOARD
                    }

                    // WHITE PIECES PUT ON BOARD IN THE FOLLOWING X,Y COORDINATES BELOW.

                      if (y == 6 && x==0) {
                        piece = makePieces(PieceTypes.WHITE_PAWN1, x, y); //POPULATE TOP BOARD
                    } if (y == 6 && x==1) {
                        piece = makePieces(PieceTypes.WHITE_PAWN2, x, y); //POPULATE TOP BOARD
                    } if (y == 6 && x==2) {
                        piece = makePieces(PieceTypes.WHITE_PAWN3, x, y); //POPULATE TOP BOARD
                    } if (y == 6 && x==3) {
                        piece = makePieces(PieceTypes.WHITE_PAWN4, x, y); //POPULATE TOP BOARD
                    } if (y == 6 && x==4) {
                        piece = makePieces(PieceTypes.WHITE_PAWN5, x, y); //POPULATE TOP BOARD
                    } if (y == 6 && x==5) {
                        piece = makePieces(PieceTypes.WHITE_PAWN6, x, y); //POPULATE TOP BOARD
                    } if (y == 6 && x==6) {
                        piece = makePieces(PieceTypes.WHITE_PAWN7, x, y); //POPULATE TOP BOARD
                    } if (y == 6 && x==7) {
                        piece = makePieces(PieceTypes.WHITE_PAWN8, x, y); //POPULATE TOP BOARD
                    }
                     if (y == 7 && x == 3) {
                        piece = makePieces(PieceTypes.WHITE_QUEEN, x, y); //POPULATE TOP BOARD
                    }
                     if (y == 7 && x == 4) {
                        piece = makePieces(PieceTypes.WHITE_KING, x, y); //POPULATE TOP BOARD
                    }
                     if ((y == 7 && x == 2)) {
                        piece = makePieces(PieceTypes.WHITE_BISHOP1, x, y); //POPULATE TOP BOARD
                    }if (y == 7 && x==5) {
                        piece = makePieces(PieceTypes.WHITE_BISHOP2, x, y); //POPULATE TOP BOARD
                    }
                     if ((y == 7 && x == 0)) {
                        piece = makePieces(PieceTypes.WHITE_ROOK1, x, y); //POPULATE TOP BOARD
                    }if (y == 7 && x==7) {
                        piece = makePieces(PieceTypes.WHITE_ROOK2, x, y); //POPULATE TOP BOARD
                    }
                     if ((y == 7 && x == 1)) {
                        piece = makePieces(PieceTypes.WHITE_KNIGHT1, x, y); //POPULATE TOP BOARD
                    }if (y == 7 && x==6) {
                        piece = makePieces(PieceTypes.WHITE_KNIGHT2, x, y); //POPULATE TOP BOARD
                    }

                    if (piece != null) {
                        //tile.setPiece(piece);
                        holdPieces.getChildren().add(piece);
                    }
                }
            }
        }
        return root;

    } // end Parent.

    /**
     * This method sets the turn from true (meaning the timer will tick)
     * or false (timer wont tick)
     * @param turn
     */
    public static void  setTurn(boolean turn){
        turnFromThread=turn;
    }

    /**
     * This method gets the turn that is set in the setTurn method above.
     * If the method determines if the timer will stop or keep going if
     * it is someones turn
     * @return
     */
    public static boolean getTurn(){
        return turnFromThread;
    }//end getTurn

    public static void  setTurnNum(int turnNumber){
        turnNumberForUser=turnNumber;
    }//end setTurnNum

    public static int getTurnNumber(){
        return turnNumberForUser;
    }//end getTurnNumber


    /**
     * This method is for making pieces on the board and also for movement! The switch statement below gets teh
     * result of the new movement. It defines what happens when a piece has a "normal movement". When a piece is killed
     * (defined in the movement method in MoveResults above, this method specifies what happens when such killing occurs.
     * The piece should probably disappear. When ever a piece moves, the type of the piece and its new coordinates
     * are sent to the client, which will then send that information to the server!
     * @param type
     * @param x
     * @param y
     * @return
     */
    public static Piece makePieces(PieceTypes type, int x, int y) {

        String color = UserColor.getUserColor();
        Boolean turn=false;
        turn=GameGUI.getTurn();
        Piece piece = new Piece(turn,color,type, x, y);
            GameGUI.setTurn(true);
            piece.setOnMouseClicked(e -> {
                int afterXConversion = convertToPixelsOnGameBoard(piece.getLayoutX()); // When user moves piece, the layout of the board is changed.
                int afterYConversion = convertToPixelsOnGameBoard(piece.getLayoutY());
                AfterMove typeOfMove = controlMovements(piece, afterXConversion, afterYConversion); //Check if the piece can move to the new
                if (typeOfMove.getType().equals(TypeOfChessMove.CANCEL)) {
                    piece.cancelMove();
                }else if(typeOfMove.getType().equals(TypeOfChessMove.MOVEPIECE)) {
                    beforeXConversion = convertToPixelsOnGameBoard(piece.getLayoutX()) * 100;
                    beforeYConversion = convertToPixelsOnGameBoard(piece.getLayoutY()) * 100;
                    piece.move(type, afterXConversion, afterYConversion); // Get new coords for moved piece.
                    setUserMove(type, afterXConversion, afterYConversion); //set user move to this piece type and coordinate
                    //to send to server
                    Client.sendUserMoveToServer(type, beforeXConversion, beforeYConversion);
                    GameGUI.setTurn(false);
                    Main.pause();
                    holdPieces.setMouseTransparent(true);
                }
            });

        return piece;

    } // end makePieces.


    /**
     * This method sets the type and coordinates of the piece after the user moves their piece. This
     * is used to send the move across the socket to the other player.
     * @param type
     * @param XMove
     * @param YMove
     */
    public static void setUserMove(PieceTypes type, double XMove, double YMove){
        UserPiecetype=type;
        beforeXConversion = XMove;
        beforeYConversion=YMove;
    }
    /**
     * Pixel to Board Coordinates. This is used in the AfterMove Method above and is for
     * the placement (as in, when the player moves a piece) when the piece moves to its new position. This method
     * centers the piece when it's moved to a new position! That is it.
     *
     * @param pixel
     * @return
     */
    private static int convertToPixelsOnGameBoard(double pixel) {
        int convertToBoard= (int) ((pixel+100/2)/100);
        return convertToBoard; // FOR WHEN PIECE MOVES, THE PIECE WILL BE RECENTERED.
    } // end toBoard.



    @Override
    public void stop() {
        ConnectionGUI.primaryStage.close();

    } // end stop.


    /**
     * This method defines how pieces can move "normally", if potentially a move causes a
     * piece to destroy can also be put here. This method is important because it defines the movement of the pieces!!
     * Given X and Y direction, this method can also control the specified pieces if necessary. Instead of having a
     * "NORMAL" move type in the AfterMove class, A " PAWN" movement can be specified! Remember, it needs
     * to be declared in the TypeOfChessMove.java file and then can have its X and Y coordinates specified below!
     * MoveDirection is specified in PieceTypes.java, where black and white pieces are given a -1 and 1 to allow them
     * to move forward or downward only on the board (this movement is not enforced at this point).
     *
     * @param piece
     * @param X
     * @param Y
     * @return
     */
    private static AfterMove controlMovements(Piece piece, int X, int Y) {
        // If there is a piece to move, move it.
        if (Chessboard[X][Y].hasPiece()) {
            return new AfterMove(TypeOfChessMove.CANCEL);
        }
        //int x0 = toBoard(piece.getOldMousePressX());  Not used yet! Can enforce X-axis movement.
        int y1 = convertToPixelsOnGameBoard(piece.getOldMousePressY());
        // This if statement allows the pieces to move 7 spaces per move (not specified for the rules yet).
        if (X - y1 == 0 || Y - y1 <= 7) { // Move Direction checks to see how the pieces are moving (down or up).
            return new AfterMove(TypeOfChessMove.MOVEPIECE);
        }
        // If player has not done anything, do not move anything.
        return new AfterMove(TypeOfChessMove.CANCEL);

    } // end tryMove.



    /**
     * This method updates the timer in the thread inside the Main method
     * @param hourTime
     * @param moveTime
     * @param minuteTime
     */
    public static void updateTimer(int hourTime, int moveTime, float minuteTime){
        Platform.runLater(() -> {
            timer2.setText(String.valueOf(hourTime) + "  " + String.valueOf(minuteTime) + "   " + String.valueOf(moveTime));
        });
    }


} // end class Start.
