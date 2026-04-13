package enums;

import javafx.scene.paint.Color;

/**
 * Represents the possible colors of a game ball.
 *
 * @author Meiqi Zhao
 * @version 2026
 */
public enum BallColor {
    /**
     * Red ball color.
     */
    RED,
    /**
     * Yellow ball color.
     */
    YELLOW,
    /**
     * Green ball color.
     */
    GREEN;

    /**
     * Returns the JavaFX Color corresponding to this BallColor.
     *
     * @return the color as a Color
     */
    public Color toFxColor() {
        return switch (this) {
            case RED -> Color.web("#E63946");
            case YELLOW -> Color.web("#DCA014");
            case GREEN -> Color.web("#1F8A4C");
        };
    }

    /**
     * Returns the next BallColor in the sequence, wrapping around to the first.
     *
     * @return the next BallColor
     */
    public BallColor next() {
        BallColor[] vals = values();
        return vals[(ordinal() + 1) % vals.length];
    }
}