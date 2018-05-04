package gui.chess;

/**
 * This class specifies the AfterMove of a piece (used in the Main class).
 */
public class AfterMove {

    private TypeOfChessMove type;
    private Piece piece;

    public TypeOfChessMove getType() {

        return type;

    } // end getType.

    public AfterMove(TypeOfChessMove type, Piece piece) {

        this.type = type;
        this.piece = piece;

    } // end AfterMove.

    public AfterMove(TypeOfChessMove type) {

        this(type, null); // When things do not move or when a normal move and the piece is missing.

    } // end AfterMove.


} // end class AfterMove.
