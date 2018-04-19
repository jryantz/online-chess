package chess;

/**
 * This class specifies the MoveResult of a piece (used in the Main class).
 */
public class MoveResult {

    private MoveType type;
    private Piece piece;

    public MoveType getType() {

        return type;

    } // end getType.

    public MoveResult(MoveType type, Piece piece) {

        this.type = type;
        this.piece = piece;

    } // end MoveResult.

    public MoveResult(MoveType type) {

        this(type, null); // When things do not move or when a normal move and the piece is missing.

    } // end MoveResult.

    public Piece getPiece() {

        return piece;

    } // end getPiece.

} // end class MoveResult.
