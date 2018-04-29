package gui.chess;

import gui.connect.ConnectionGUI;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;
import node.Client;


public class GameGUI extends Application {

    public static final int TILE_SIZE = 100; // GameGUI TILE.
    public static final int WIDTH = 8; // FOR GameGUI. DEFINES WIDTH AND HEIGHT FOR TILE.
    public static final int HEIGHT = 8; // GameGUI TILE.


    private static double OldMousePressX;
    private static double OldMousePressY;
    private static PieceTypes UserPiecetype;
    private static Pane root;

    private static Tile[][] board = new Tile[WIDTH][HEIGHT]; // TILE GameGUI.
    private static Group tileGroup = new Group(); // GameGUI TILE.
    public static Group pieceGroup = new Group(); // GameGUI PIECES SIT ON TOP OF TILE.

    /**
     * This method helps create the board for the GameGUI. With this method, tile coordinates are also set,
     * where the pieces from the PieceTypes and Piece class are created and placed on the board. This method
     * essentially just provides the initial board with the pieces when a client starts the game. Given the
     * X,Y coordinates, the pieces are placed on the board.
     *
     * @return
     */
    public static Parent createContent() {

            root = new Pane();

            root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
            root.getChildren().addAll(tileGroup, pieceGroup);
        if (Main.getSentPieceType() == null) {
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {

                    Tile tile = new Tile((x + y) % 2 == 0, x, y);
                    board[x][y] = tile;
                  //  Main.setTileStuff(tile);
                    tileGroup.getChildren().add(tile);

                    Piece piece = null;

                    if (y == 1 && x==0) {
                        piece = createPieces(PieceTypes.BLACK_PAWN1, x, y); //POPULATE TOP BOARD
                    } if (y == 1 && x==1) {
                        piece = createPieces(PieceTypes.BLACK_PAWN2, x, y); //POPULATE TOP BOARD
                    } if (y == 1 && x==2) {
                        piece = createPieces(PieceTypes.BLACK_PAWN3, x, y); //POPULATE TOP BOARD
                    } if (y == 1 && x==3) {
                        piece = createPieces(PieceTypes.BLACK_PAWN4, x, y); //POPULATE TOP BOARD
                    } if (y == 1 && x==4) {
                        piece = createPieces(PieceTypes.BLACK_PAWN5, x, y); //POPULATE TOP BOARD
                    } if (y == 1 && x==5) {
                        piece = createPieces(PieceTypes.BLACK_PAWN6, x, y); //POPULATE TOP BOARD
                    } if (y == 1 && x==6) {
                        piece = createPieces(PieceTypes.BLACK_PAWN7, x, y); //POPULATE TOP BOARD
                    } if (y == 1 && x==7) {
                        piece = createPieces(PieceTypes.BLACK_PAWN8, x, y); //POPULATE TOP BOARD

                    } if ((y == 0 && x == 0)) {
                        piece = createPieces(PieceTypes.BLACK_ROOK1, x, y); //POPULATE TOP BOARD
                    } if (y == 0 && x==7) {
                        piece = createPieces(PieceTypes.BLACK_ROOK2, x, y); //POPULATE TOP BOARD

                    } if ((y == 0 && x == 1)) {
                        piece = createPieces(PieceTypes.BLACK_KNIGHT1, x, y); //POPULATE TOP BOARD
                    } if (y == 0 && x==6) {
                        piece = createPieces(PieceTypes.BLACK_KNIGHT2, x, y); //POPULATE TOP BOARD

                    } if ((y == 0 && x == 2)) {
                        piece = createPieces(PieceTypes.BLACK_BISHOP1, x, y); //POPULATE TOP BOARD
                    } if (y == 0 && x==5) {
                        piece = createPieces(PieceTypes.BLACK_BISHOP2, x, y); //POPULATE TOP BOARD

                    } if (y == 0 && x == 3) {
                        piece = createPieces(PieceTypes.BLACK_QUEEN, x, y); //POPULATE TOP BOARD

                    } if (y == 0 && x == 4) {
                        piece = createPieces(PieceTypes.BLACK_KING, x, y); //POPULATE TOP BOARD
                    }

                    // WHITE PIECES PUT ON BOARD IN THE FOLLOWING X,Y COORDINATES BELOW.

                      if (y == 6 && x==0) {
                        piece = createPieces(PieceTypes.WHITE_PAWN1, x, y); //POPULATE TOP BOARD
                    } if (y == 6 && x==1) {
                        piece = createPieces(PieceTypes.WHITE_PAWN2, x, y); //POPULATE TOP BOARD
                    } if (y == 6 && x==2) {
                        piece = createPieces(PieceTypes.WHITE_PAWN3, x, y); //POPULATE TOP BOARD
                    } if (y == 6 && x==3) {
                        piece = createPieces(PieceTypes.WHITE_PAWN4, x, y); //POPULATE TOP BOARD
                    } if (y == 6 && x==4) {
                        piece = createPieces(PieceTypes.WHITE_PAWN5, x, y); //POPULATE TOP BOARD
                    } if (y == 6 && x==5) {
                        piece = createPieces(PieceTypes.WHITE_PAWN6, x, y); //POPULATE TOP BOARD
                    } if (y == 6 && x==6) {
                        piece = createPieces(PieceTypes.WHITE_PAWN7, x, y); //POPULATE TOP BOARD
                    } if (y == 6 && x==7) {
                        piece = createPieces(PieceTypes.WHITE_PAWN8, x, y); //POPULATE TOP BOARD
                    }
                     if (y == 7 && x == 3) {
                        piece = createPieces(PieceTypes.WHITE_QUEEN, x, y); //POPULATE TOP BOARD
                    }
                     if (y == 7 && x == 4) {
                        piece = createPieces(PieceTypes.WHITE_KING, x, y); //POPULATE TOP BOARD
                    }
                     if ((y == 7 && x == 2)) {
                        piece = createPieces(PieceTypes.WHITE_BISHOP1, x, y); //POPULATE TOP BOARD
                    }if (y == 7 && x==5) {
                        piece = createPieces(PieceTypes.WHITE_BISHOP2, x, y); //POPULATE TOP BOARD
                    }
                     if ((y == 7 && x == 0)) {
                        piece = createPieces(PieceTypes.WHITE_ROOK1, x, y); //POPULATE TOP BOARD
                    }if (y == 7 && x==7) {
                        piece = createPieces(PieceTypes.WHITE_ROOK2, x, y); //POPULATE TOP BOARD
                    }
                     if ((y == 7 && x == 1)) {
                        piece = createPieces(PieceTypes.WHITE_KNIGHT1, x, y); //POPULATE TOP BOARD
                    }if (y == 7 && x==6) {
                        piece = createPieces(PieceTypes.WHITE_KNIGHT2, x, y); //POPULATE TOP BOARD
                    }

                    if (piece != null) {
                        //tile.setPiece(piece);
                        pieceGroup.getChildren().add(piece);
                    }
                }
            }
        }
        return root;

    } // end Parent.



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
    public static Piece createPieces(PieceTypes type, int x, int y) {


        String color = UserColor.getUserColor();

        Piece piece = new Piece(color,type, x, y);

        piece.setOnMouseClicked(e -> {
            int newCoordinateX = toBoard(piece.getLayoutX()); // When user moves piece, the layout of the board is changed.
            int newCoordinateY = toBoard(piece.getLayoutY());

            MoveResult result = tryMove(piece, newCoordinateX, newCoordinateY); //Check if the piece can move to the new
            //coordinates

           int OldCoordinateX = toBoard(piece.getOldMousePressX());
           int OldCoordinateY = toBoard(piece.getOldMousePressY());

            switch (result.getType()) {
                case NONE:
                    piece.cancelMove();
                    break;
                case NORMAL:
                    OldMousePressX=newCoordinateX*TILE_SIZE;
                    OldMousePressY=newCoordinateY*TILE_SIZE;

                            piece.move(type, newCoordinateX, newCoordinateY); // Get new coords for moved piece.
                            System.out.println(type + " AFTER PIECE HAS MOVED X: " + newCoordinateX + " AFTER PIECE HAS MOVED Y: " + newCoordinateY);
                            setUserMove(type, newCoordinateX, newCoordinateY); //set user move to this piece type and coordinate
                            //to send to server
                            Client.sendUserMoveToServer(root, type, OldMousePressX, OldMousePressY);
                            board[OldCoordinateX][OldCoordinateY].setPiece(null);
                            board[newCoordinateX][newCoordinateY].setPiece(piece);
                            break;
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
       // pieceTypeString= Piece.converToPieceType(type);
        UserPiecetype=type;
        OldMousePressX = XMove;
        OldMousePressY=YMove;

    }


    /**
     * This method defines how pieces can move "normally", if potentially a move causes a
     * piece to kill can also be put here. This method is important because it defines the movement of the pieces!!
     * Given X and Y direction, this method can also control the specified pieces if necessary. Instead of having a
     * "NORMAL" move type in the MoveResult class, A "NORMAL PAWN" movement can be specified! Remember, it needs
     * to be declared in the MoveType.java file and then can have its X and Y coordinates specified below!
     * MoveDirection is specified in PieceTypes.java, where black and white pieces are given a -1 and 1 to allow them
     * to move forward or downward only on the board (this movement is not enforced at this point).
     *
     * @param piece
     * @param newX
     * @param newY
     * @return
     */
    private static MoveResult tryMove(Piece piece, int newX, int newY) {

        // If there is a piece to move, move it.
        if (board[newX][newY].hasPiece()) {
            return new MoveResult(MoveType.NONE);
        }
        //int x0 = toBoard(piece.getOldMousePressX());  Not used yet! Can enforce X-axis movement.
        int y0 = toBoard(piece.getOldMousePressY());
        // This if statement allows the pieces to move 7 spaces per move (not specified for the rules yet).
        if (newY - y0 == 0 || newY - y0 <= 7) { // Move Direction checks to see how the pieces are moving (down or up).
            return new MoveResult(MoveType.NORMAL);
        }
        // If player has not done anything, do not move anything.
        return new MoveResult(MoveType.NONE);

    } // end tryMove.

    /**
     * Pixel to Board Coordinates. This is used in the MoveResult Method above and is for
     * the placement (as in, when the player moves a piece) when the piece moves to its new position. This method
     * centers the piece when it's moved to a new position! That is it.
     *
     * @param pixel
     * @return
     */
    private static int toBoard(double pixel) {

        return (int) (pixel + TILE_SIZE / 2) / TILE_SIZE; // FOR WHEN PIECE MOVES, THE PIECE WILL BE RECENTERED.

    } // end toBoard.

    /**
     * This is a method for the GameGUI! Starts the stage!
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(createContent());

        primaryStage.setTitle("FSU Chess Client");
        primaryStage.setScene(scene);

        primaryStage.show();

    } // end start.

    @Override
    public void stop() {

        ConnectionGUI.primaryStage.close();

    } // end stop.

} // end class Start.
