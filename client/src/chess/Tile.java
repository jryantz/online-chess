package chess;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This is a class that helps define the tiles parameters for the GUI. Nothing should really
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
        setWidth(GUI.TILE_SIZE);
        setHeight(GUI.TILE_SIZE);
        relocate(x * GUI.TILE_SIZE, y * GUI.TILE_SIZE);

        //sets board light color and dark color 5F5B5F 0F0D0D
        setFill(light ? Color.valueOf("666") : Color.valueOf("333"));
    }

} // end class Tile.
