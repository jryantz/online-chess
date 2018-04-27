package gui.chess;

public enum PieceTypes {

    BLACK_KING(1), BLACK_QUEEN(1), BLACK_ROOK(1), BLACK_BISHOP(1), BLACK_KNIGHT(1), BLACK_PAWN(1),
    WHITE_KING(-1), WHITE_QUEEN(-1), WHITE_ROOK(-1), WHITE_BISHOP(-1), WHITE_KNIGHT(-1), WHITE_PAWN(-1);

    final int moveDirection;

    PieceTypes(int moveDirection){
        this.moveDirection = moveDirection;
    }


} // end class PieceTypes.
