package chess;

public class Move {

    public static boolean valid(String piece, String from, String to) {

        switch(piece) {
            case "King":
                return kingMove(from, to);
            case "Queen":
                return queenMove(from, to);
            default:
                return false;
        }

    } // end valid.

    private static boolean kingMove(String from, String to) {
        return false;
    } // end kingMove.

    private static boolean queenMove(String from, String to) {
        return false;
    } // end queenMove.

    private static boolean checkCheck() {
        return false;
    } // end checkCheck.

} // end class Move.
