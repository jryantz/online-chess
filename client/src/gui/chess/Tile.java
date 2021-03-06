package gui.chess;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This is a class that helps define the tiles parameters for the GameGUI. Nothing should really
 * be changed in here unless we are changing the board colors. Used in the main class.
 */
public class Tile extends Rectangle {

    private Piece piece;

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Takes in parameters of coordinates!
     *
     * @param light
     * @param x
     * @param y
     */
    public Tile(boolean light, int x, int y) {
        setWidth(100);
        setHeight(100);
        relocate(x * 100, y * 100);

        //sets board light color and dark color 5F5B5F 0F0D0D
        setFill(light ? Color.valueOf("666") : Color.valueOf("333"));
    }

} // end class Tile.
