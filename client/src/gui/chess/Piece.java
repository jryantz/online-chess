package gui.chess;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import static gui.chess.GameGUI.TILE_SIZE;

/**
 * The piece class is super important for GameGUI and movement stuff. The Pieces in the piece class are defined based
 * on the enum PieceType. How each piece looks is set in the Piece constructor below. This class also
 * gets the mouse presses (old clicks to new clicks) from the board.
 */
public class Piece extends StackPane {

    private PieceTypes type;
    private static String colorforCheck;
    private double MousepressX, MousepressY; // REMEMBER MOUSE CLICK COORDINATES.
    private double OldMousePressX, OldMousePressY; // Old coordinates.

    private double OriginalPlaceX, OriginalPlaceY;

    public PieceTypes getType() {

        return type;

    } // end getType.

    public double getOldMousePressX() {

        return OldMousePressX;

    } // end getOldMousePressX.

    public double getOldMousePressY() {

        return OldMousePressY;

    } // end getOldMousePressY.

    /**
     * Helps design certain pieces based on type. This also gets the movement.
     *
     * @param color
     * @param type
     * @param x
     * @param y
     */
    public Piece(String color, PieceTypes type, int x, int y) {


        this.type = type;

        if (type == PieceTypes.BLACK_PAWN) {
            Image img = new Image("/images/tronpawn3.png");
            move(PieceTypes.BLACK_PAWN, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        if (type == PieceTypes.BLACK_ROOK) {
            Image img = new Image("/images/tronrook2.png");
            move(PieceTypes.BLACK_ROOK, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        if (type == PieceTypes.BLACK_KNIGHT) {
            Image img = new Image("/images/tronknight2.png");
            move(PieceTypes.BLACK_KNIGHT, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        if (type == PieceTypes.BLACK_BISHOP) {
            Image img = new Image("/images/tronbishop2.png");
            move(PieceTypes.BLACK_BISHOP, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        if (type == PieceTypes.BLACK_QUEEN) {
            Image img = new Image("/images/tronqueen2.png");
            move(PieceTypes.BLACK_QUEEN, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        if (type == PieceTypes.BLACK_KING) {
            Image img = new Image("/images/tronking2.png");
            move(PieceTypes.BLACK_KING, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        // WHITE PIECES CONFIGURED BELOW IN TERMS OF LOOKS.

        if (type == PieceTypes.WHITE_PAWN) {
            Image img = new Image("/images/tronpawn2.png");

            move(PieceTypes.WHITE_PAWN, x, y);

            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        if (type == PieceTypes.WHITE_QUEEN) {
            Image img = new Image("/images/tronqueen.png");
            move(PieceTypes.WHITE_QUEEN, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        if (type == PieceTypes.WHITE_KING) {
            Image img = new Image("/images/tronking.png");
            move(PieceTypes.WHITE_KING, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        if (type == PieceTypes.WHITE_BISHOP) {
            Image img = new Image("/images/tronbishop.png");
            move(PieceTypes.WHITE_BISHOP, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        if (type == PieceTypes.WHITE_ROOK) {
            Image img = new Image("/images/tronrook.png");
            move(PieceTypes.WHITE_ROOK, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        if (type == PieceTypes.WHITE_KNIGHT) {
            Image img = new Image("/images/tronknight.png");
            move(PieceTypes.WHITE_KNIGHT, x, y);

            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

            //SET PIECE TYPE COLOR
            setPieceColorType(type);

                setOnMousePressed(e -> {
                    MousepressX = e.getSceneX();
                    MousepressY = e.getSceneY();
                    System.out.println(type + " ORIGINAL MOUSE PRESS BEFORE MOVE X" + OldMousePressX + " ORIGINAL MOUSE PRESS BEFORE MOVE Y" + OldMousePressY);
                });


            //GET PIECE TYPE COLOR THAT THE USER CLICKS ON BEFORE DRAG AND SEE IF IT MATCHES USER COLOR
                if(color.equals(getPieceColorType())) {
                    setOnMouseDragged(e -> {
                        relocate(e.getSceneX() - MousepressX + OldMousePressX, e.getSceneY() - MousepressY + OldMousePressY);

                    });
                }
    } // end Piece.


    /**
     * Method for moving pieces based on mouse clicks.
     *
     * @param x
     * @param y
     */
    public void move(PieceTypes type, int x, int y) {


            OldMousePressX = x * TILE_SIZE;
            OldMousePressY = y * TILE_SIZE;
            System.out.println(type + " AFTER PIECE HAS MOVED X" + OldMousePressX + " AFTER PIECE HAS MOVED Y" + OldMousePressY);
            relocate(OldMousePressX, OldMousePressY);


    } // end move.


    public  void setPieceColorType(PieceTypes pieceType){

        if(pieceType.equals(PieceTypes.BLACK_BISHOP) || pieceType.equals(PieceTypes.BLACK_KING) || pieceType.equals(PieceTypes.BLACK_KNIGHT)||
                pieceType.equals(PieceTypes.BLACK_ROOK) || pieceType.equals(PieceTypes.BLACK_QUEEN) || pieceType.equals(PieceTypes.BLACK_PAWN)){
            colorforCheck = "BLACK";
        }else{
            colorforCheck="WHITE";
        }
    }


    public static String getPieceColorType(){
        return  colorforCheck;
    }

    /**
     * This method relcoates the old coordinates if the user cancels their move.
     */
    public void cancelMove() {

        relocate(OldMousePressX, OldMousePressY);

    } // end cancelMove.

} // end class Piece.
