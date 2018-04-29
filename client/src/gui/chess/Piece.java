package gui.chess;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import main.Main;

import static gui.chess.GameGUI.TILE_SIZE;

/**
 * The piece class is super important for GameGUI and movement stuff. The Pieces in the piece class are defined based
 * on the enum PieceType. How each piece looks is set in the Piece constructor below. This class also
 * gets the mouse presses (old clicks to new clicks) from the board.
 */
public class Piece extends StackPane {

    private static String pieceTypeString;
    private static PieceTypes convertPiece;
    private static  int socketXCoordinate;
    private static int socketYCoordinate;
    private PieceTypes type;


    private static String colorforCheck;

    private double MousepressX, MousepressY; // REMEMBER MOUSE CLICK COORDINATES.
    private double OldMousePressX, OldMousePressY; // Old coordinates.

    private double OriginalPlaceX, OriginalPlaceY;

    public static void setPieceXCoordinate(int x) {
        int convert= x/TILE_SIZE;
        socketXCoordinate=convert;

    }

    public static void setPieceYCoordinate(int y) {
        int convert= y/TILE_SIZE;
        socketYCoordinate=convert;
    }

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

        if (type == PieceTypes.BLACK_PAWN1) {
            Image img = new Image("/images/tronpawn3.png");
            move(PieceTypes.BLACK_PAWN1, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }if (type == PieceTypes.BLACK_PAWN2) {
            Image img = new Image("/images/tronpawn3.png");
            move(PieceTypes.BLACK_PAWN2, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }if (type == PieceTypes.BLACK_PAWN3) {
            Image img = new Image("/images/tronpawn3.png");
            move(PieceTypes.BLACK_PAWN3, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }if (type == PieceTypes.BLACK_PAWN4) {
            Image img = new Image("/images/tronpawn3.png");
            move(PieceTypes.BLACK_PAWN4, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }if (type == PieceTypes.BLACK_PAWN5) {
            Image img = new Image("/images/tronpawn3.png");
            move(PieceTypes.BLACK_PAWN5, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        } if (type == PieceTypes.BLACK_PAWN6) {
            Image img = new Image("/images/tronpawn3.png");
            move(PieceTypes.BLACK_PAWN6, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }if (type == PieceTypes.BLACK_PAWN7) {
            Image img = new Image("/images/tronpawn3.png");
            move(PieceTypes.BLACK_PAWN7, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }if (type == PieceTypes.BLACK_PAWN8) {
            Image img = new Image("/images/tronpawn3.png");
            move(PieceTypes.BLACK_PAWN8, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        if (type == PieceTypes.BLACK_ROOK1) {
            Image img = new Image("/images/tronrook2.png");
            move(PieceTypes.BLACK_ROOK1, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        } if (type == PieceTypes.BLACK_ROOK2) {
            Image img = new Image("/images/tronrook2.png");
            move(PieceTypes.BLACK_ROOK2, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        if (type == PieceTypes.BLACK_KNIGHT1) {
            Image img = new Image("/images/tronknight2.png");
            move(PieceTypes.BLACK_KNIGHT1, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        } if (type == PieceTypes.BLACK_KNIGHT2) {
            Image img = new Image("/images/tronknight2.png");
            move(PieceTypes.BLACK_KNIGHT2, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        if (type == PieceTypes.BLACK_BISHOP1) {
            Image img = new Image("/images/tronbishop2.png");
            move(PieceTypes.BLACK_BISHOP1, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        } if (type == PieceTypes.BLACK_BISHOP2) {
            Image img = new Image("/images/tronbishop2.png");
            move(PieceTypes.BLACK_BISHOP2, x, y);
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

        if (type == PieceTypes.WHITE_PAWN1) {
            Image img = new Image("/images/tronpawn2.png");
            move(PieceTypes.WHITE_PAWN1, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }if (type == PieceTypes.WHITE_PAWN2) {
            Image img = new Image("/images/tronpawn2.png");
            move(PieceTypes.WHITE_PAWN2, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }if (type == PieceTypes.WHITE_PAWN3) {
            Image img = new Image("/images/tronpawn2.png");
            move(PieceTypes.WHITE_PAWN3, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }if (type == PieceTypes.WHITE_PAWN4) {
            Image img = new Image("/images/tronpawn2.png");
            move(PieceTypes.WHITE_PAWN4, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }if (type == PieceTypes.WHITE_PAWN5) {
            Image img = new Image("/images/tronpawn2.png");
            move(PieceTypes.WHITE_PAWN5, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }if (type == PieceTypes.WHITE_PAWN6) {
            Image img = new Image("/images/tronpawn2.png");
            move(PieceTypes.WHITE_PAWN6, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }if (type == PieceTypes.WHITE_PAWN7) {
            Image img = new Image("/images/tronpawn2.png");
            move(PieceTypes.WHITE_PAWN7, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }if (type == PieceTypes.WHITE_PAWN8) {
            Image img = new Image("/images/tronpawn2.png");
            move(PieceTypes.WHITE_PAWN8, x, y);
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

        if (type == PieceTypes.WHITE_BISHOP1) {
            Image img = new Image("/images/tronbishop.png");
            move(PieceTypes.WHITE_BISHOP1, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }  if (type == PieceTypes.WHITE_BISHOP2) {
            Image img = new Image("/images/tronbishop.png");
            move(PieceTypes.WHITE_BISHOP2, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        if (type == PieceTypes.WHITE_ROOK1) {
            Image img = new Image("/images/tronrook.png");
            move(PieceTypes.WHITE_ROOK1, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }  if (type == PieceTypes.WHITE_ROOK2) {
            Image img = new Image("/images/tronrook.png");
            move(PieceTypes.WHITE_ROOK2, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        if (type == PieceTypes.WHITE_KNIGHT1) {
            Image img = new Image("/images/tronknight.png");
            move(PieceTypes.WHITE_KNIGHT1, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);

        } if (type == PieceTypes.WHITE_KNIGHT2) {
            Image img = new Image("/images/tronknight.png");
            move(PieceTypes.WHITE_KNIGHT2, x, y);
            Rectangle piece = new Rectangle(TILE_SIZE * 0.6325, TILE_SIZE * 0.80);
            piece.setFill(new ImagePattern(img));
            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.36 * 2) / 2);
            getChildren().addAll(piece);
        }

        //SET PIECE TYPE COLOR
        if(convertPiece == null && Main.getSentPieceType()==null) {
            setPieceColorType(type);
        }
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
        relocate(OldMousePressX, OldMousePressY);

    } // end move.

    /**
     * This method sets color of pieces which the user wil be using.
     * @param pieceType
     */
    public void setPieceColorType(PieceTypes pieceType) {

        if (pieceType.equals(PieceTypes.BLACK_BISHOP1) ||pieceType.equals(PieceTypes.BLACK_BISHOP2) ||
                pieceType.equals(PieceTypes.BLACK_KING) || pieceType.equals(PieceTypes.BLACK_KNIGHT1) ||
                pieceType.equals(PieceTypes.BLACK_KNIGHT2) || pieceType.equals(PieceTypes.BLACK_ROOK1) ||
                pieceType.equals(PieceTypes.BLACK_ROOK2) || pieceType.equals(PieceTypes.BLACK_QUEEN) ||
                pieceType.equals(PieceTypes.BLACK_PAWN1) || pieceType.equals(PieceTypes.BLACK_PAWN2) ||
                pieceType.equals(PieceTypes.BLACK_PAWN3) || pieceType.equals(PieceTypes.BLACK_PAWN4) ||
                pieceType.equals(PieceTypes.BLACK_PAWN5) || pieceType.equals(PieceTypes.BLACK_PAWN6) ||
                pieceType.equals(PieceTypes.BLACK_PAWN7) || pieceType.equals(PieceTypes.BLACK_PAWN8)) {
            colorforCheck = "BLACK";
        } else {
            colorforCheck = "WHITE";
        }

    } // end setPieceColorType

    /**
     * This method transforms the specific piece the user just moved into a string. This will
     * be sent across the socket in the form of a string (for checking purposes) and will
     * be transformed back when it is received by the other user.
     * @param userPiece
     */
    public static void convertStringToPieceTypes(String userPiece) {
        if (userPiece.equals(PieceTypes.BLACK_BISHOP1.toString())){
            setStringToPiece(PieceTypes.BLACK_BISHOP1);
        } else if(userPiece.equals(PieceTypes.BLACK_BISHOP2.toString())){
            setStringToPiece(PieceTypes.BLACK_BISHOP2);

        }else if(userPiece.equals(PieceTypes.BLACK_KING.toString())){
            setStringToPiece(PieceTypes.BLACK_KING);

        }else if (userPiece.equals(PieceTypes.BLACK_KNIGHT1.toString())){
            setStringToPiece(PieceTypes.BLACK_KNIGHT1);
        }else if (userPiece.equals(PieceTypes.BLACK_KNIGHT2.toString())){
            setStringToPiece(PieceTypes.BLACK_KNIGHT2);

        }else if(userPiece.equals(PieceTypes.BLACK_ROOK1.toString())){
             setStringToPiece(PieceTypes.BLACK_ROOK1);
        }else if(userPiece.equals(PieceTypes.BLACK_ROOK2.toString())){
            setStringToPiece(PieceTypes.BLACK_ROOK2);
        }else if(userPiece.equals(PieceTypes.BLACK_QUEEN.toString())){
            setStringToPiece(PieceTypes.BLACK_QUEEN);
        }else if(userPiece.equals(PieceTypes.BLACK_PAWN1.toString())){
            setStringToPiece(PieceTypes.BLACK_PAWN1);
        }else if(userPiece.equals(PieceTypes.BLACK_PAWN2.toString())){
            setStringToPiece(PieceTypes.BLACK_PAWN2);
        }else if(userPiece.equals(PieceTypes.BLACK_PAWN3.toString())){
            setStringToPiece(PieceTypes.BLACK_PAWN3);
        }else if(userPiece.equals(PieceTypes.BLACK_PAWN4.toString())){
            setStringToPiece(PieceTypes.BLACK_PAWN4);
        }else if(userPiece.equals(PieceTypes.BLACK_PAWN5.toString())){
            setStringToPiece(PieceTypes.BLACK_PAWN5);
        }else if(userPiece.equals(PieceTypes.BLACK_PAWN6.toString())){
            setStringToPiece(PieceTypes.BLACK_PAWN6);
        }else if(userPiece.equals(PieceTypes.BLACK_PAWN7.toString())){
            setStringToPiece(PieceTypes.BLACK_PAWN7);
        }else if(userPiece.equals(PieceTypes.BLACK_PAWN8.toString())){
            setStringToPiece(PieceTypes.BLACK_PAWN8);

        }else if(userPiece.equals(PieceTypes.WHITE_BISHOP1.toString())){
            setStringToPiece(PieceTypes.WHITE_BISHOP1);
        }else if(userPiece.equals(PieceTypes.WHITE_BISHOP2.toString())){
            setStringToPiece(PieceTypes.WHITE_BISHOP2);

        }else if(userPiece.equals(PieceTypes.WHITE_KING.toString())){
            setStringToPiece(PieceTypes.WHITE_KING);

        }else if(userPiece.equals(PieceTypes.WHITE_KNIGHT1.toString())){
            setStringToPiece(PieceTypes.WHITE_KNIGHT1);
        }else if(userPiece.equals(PieceTypes.WHITE_KNIGHT2.toString())){
            setStringToPiece(PieceTypes.WHITE_KNIGHT2);

        }else if(userPiece.equals(PieceTypes.WHITE_PAWN1.toString())){
            setStringToPiece(PieceTypes.WHITE_PAWN1);
        }else if(userPiece.equals(PieceTypes.WHITE_PAWN2.toString())){
            setStringToPiece(PieceTypes.WHITE_PAWN2);
        }else if(userPiece.equals(PieceTypes.WHITE_PAWN3.toString())){
            setStringToPiece(PieceTypes.WHITE_PAWN3);
        }else if(userPiece.equals(PieceTypes.WHITE_PAWN4.toString())){
            setStringToPiece(PieceTypes.WHITE_PAWN4);
        }else if(userPiece.equals(PieceTypes.WHITE_PAWN5.toString())){
            setStringToPiece(PieceTypes.WHITE_PAWN5);
        }else if(userPiece.equals(PieceTypes.WHITE_PAWN6.toString())){
            setStringToPiece(PieceTypes.WHITE_PAWN6);
        }else if(userPiece.equals(PieceTypes.WHITE_PAWN7.toString())){
            setStringToPiece(PieceTypes.WHITE_PAWN7);
        }else if(userPiece.equals(PieceTypes.WHITE_PAWN8.toString())){
            setStringToPiece(PieceTypes.WHITE_PAWN8);

        }else if( userPiece.equals(PieceTypes.WHITE_QUEEN.toString())){
            setStringToPiece(PieceTypes.WHITE_QUEEN);

        }else if(userPiece.equals(PieceTypes.WHITE_ROOK1.toString())) {
            setStringToPiece(PieceTypes.WHITE_ROOK1);
        }else if(userPiece.equals(PieceTypes.WHITE_ROOK2.toString())) {
            setStringToPiece(PieceTypes.WHITE_ROOK2);
        }
    }//end convertStringToPieceTypes

    /**
     * This method gets the piece color that the user will be controlling
     * @return
     */
    public static String getPieceColorType(){
        return  colorforCheck;

    } // end getPieceColorType.

    /**
     * This method sets the piece color of the user who already has their GUI started
     * @param type
     */
    public static void setStringToPiece(PieceTypes type){
        convertPiece=type;
    }

    /**
     * This method gets the piece Type after the piece has been
     * converted to a string (this is meant for the client who receives
     * the piece as a string.
     * @return
     */
    public static PieceTypes getPieceTypeAfterConversion(){
        return convertPiece;
    }

    /**
     * This method relcoates the old coordinates if the user cancels their move.
     */
    public void cancelMove() {

        relocate(OldMousePressX, OldMousePressY);

    } // end cancelMove.


} // end class Piece.
