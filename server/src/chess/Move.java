package chess;

public class Move {

    public static boolean valid(String piece, char from, char to) {

        switch(piece) {
            case "King":
                return kingMove(from, to);
            case "Queen":
                return queenMove(from, to);
            default:
                return false;
        }

    } // end valid.

    private static boolean kingMove(char from, char to) {
        return false;
    } // end kingMove.

    private static boolean queenMove(char from, char to) {
        return false;
    } // end queenMove.

    private static boolean checkCheck() {
        return false;
    } // end checkCheck.

} // end class Move.
