package chess;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUI extends Application {

    public static final int TILE_SIZE = 100; // GUI TILE.
    public static final int WIDTH = 8; // FOR GUI. DEFINES WIDTH AND HEIGHT FOR TILE.
    public static final int HEIGHT = 8; // GUI TILE.

    private Tile[][] board = new Tile[WIDTH][HEIGHT]; // TILE GUI.
    private Group tileGroup = new Group(); // GUI TILE.
    private Group pieceGroup = new Group(); // GUI PIECES SIT ON TOP OF TILE.

    public GUI() {



    } // end GUI;

    /**
     * This method helps create the board for the GUI. With this method, tile coordinates are also set,
     * where the pieces from the PieceTypes and Piece class are created and placed on the board. This method
     * essentially just provides the initial board with the pieces when a client starts the game. Given the
     * X,Y coordinates, the pieces are placed on the board.
     *
     * @return
     */
    private Parent createContent() {

        Pane root = new Pane();

        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tileGroup, pieceGroup);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {

                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;
                tileGroup.getChildren().add(tile);

                Piece piece = null;

                if (y == 1) {
                    piece = makePieces(PieceTypes.BLACK_PAWN, x, y); //POPULATE TOP BOARD
                }
                if ((y == 0 && x == 0) || (y == 0 && x == 7)) {
                    piece = makePieces(PieceTypes.BLACK_ROOK, x, y); //POPULATE TOP BOARD
                }
                if ((y == 0 && x == 1) || (y == 0 && x == 6)) {
                    piece = makePieces(PieceTypes.BLACK_KNIGHT, x, y); //POPULATE TOP BOARD
                }
                if ((y == 0 && x == 2) || (y == 0 && x == 5)) {
                    piece = makePieces(PieceTypes.BLACK_BISHOP, x, y); //POPULATE TOP BOARD
                }
                if (y == 0 && x == 3) {
                    piece = makePieces(PieceTypes.BLACK_QUEEN, x, y); //POPULATE TOP BOARD
                }
                if (y == 0 && x == 4) {
                    piece = makePieces(PieceTypes.BLACK_KING, x, y); //POPULATE TOP BOARD
                }

                // WHITE PIECES PUT ON BOARD IN THE FOLLOWING X,Y COORDINATES BELOW.

                if (y == 6) {
                    piece = makePieces(PieceTypes.WHITE_PAWN, x, y); //POPULATE TOP BOARD
                }
                if (y == 7 && x == 3) {
                    piece = makePieces(PieceTypes.WHITE_QUEEN, x, y); //POPULATE TOP BOARD
                }
                if (y == 7 && x == 4) {
                    piece = makePieces(PieceTypes.WHITE_KING, x, y); //POPULATE TOP BOARD
                }
                if ((y == 7 && x == 2) || (y == 7 && x == 5)) {
                    piece = makePieces(PieceTypes.WHITE_BISHOP, x, y); //POPULATE TOP BOARD
                }
                if ((y == 7 && x == 0) || (y == 7 && x == 7)) {
                    piece = makePieces(PieceTypes.WHITE_ROOK, x, y); //POPULATE TOP BOARD
                }
                if ((y == 7 && x == 1) || (y == 7 && x == 6)) {
                    piece = makePieces(PieceTypes.WHITE_KNIGHT, x, y); //POPULATE TOP BOARD
                }

                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }

        return root;

    } // end Parent.

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
    private MoveResult tryMove(Piece piece, int newX, int newY) {

        if (board[newX][newY].hasPiece()) { // If there is a piece to move (sets to even pieces too).
            return new MoveResult(MoveType.NONE);
        }

        int x0 = toBoard(piece.getOldMousePressX()); // Not used yet! Can enforce X-axis movement.
        int y0 = toBoard(piece.getOldMousePressY());

        // This if statement allows the pieces to move 7 spaces per move (not specified for the rules yet).
        if (newY - y0 == piece.getType().moveDirection || newY - y0 == 0 || newY - y0 <= 7) { // Move Direction checks to see how the pieces are moving (down or up).
            return new MoveResult(MoveType.NORMAL);
        }

        return new MoveResult(MoveType.NONE); // If player has not done anything, do not move anything.

    } // end tryMove.

    /**
     * Pixel to Board Coordinates. This is used in the MoveResult Method above and is for
     * the placement (as in, when the player moves a piece) when the piece moves to its new position. This method
     * centers the piece when it's moved to a new position! That is it.
     *
     * @param pixel
     * @return
     */
    private int toBoard(double pixel) {

        return (int) (pixel + TILE_SIZE / 2) / TILE_SIZE; // FOR WHEN PIECE MOVES, THE PIECE WILL BE RECENTERED.

    } // end toBoard.

    /**
     * This is a method for the GUI! Starts the stage!
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

    /**
     * This method is for making pieces on the board and also for movement! The switch statement below gets teh
     * result of the new movement. It defines what happens when a piece is "killed" or has a "normal movement". All
     * pieces should have a normal movement, because they are just moved. When a piece is killed (defined in the movement
     * method in MoveResults above, this method specifies what happens when such killing occurs. The piece should probably
     * disappear.
     *
     * @param type
     * @param x
     * @param y
     * @return
     */
    private Piece makePieces(PieceTypes type, int x, int y) {

        Piece piece = new Piece(type, x, y);
        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX()); // When user moves piece, the layout of the board is changed.
            int newY = toBoard(piece.getLayoutY());
            MoveResult result = tryMove(piece, newX, newY);
            int x0 = toBoard(piece.getOldMousePressX());
            int y0 = toBoard(piece.getOldMousePressY());

            switch (result.getType()) {
                case NONE:
                    piece.cancelMove();
                    break;
                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    break;
                case KILL: // NOT IMPLEMENTED. PIECE DISAPPEARANCE SHOULD OCCUR.
                    break;
            }
        });

        return piece;

    } // end makePieces.

} // end class Start.