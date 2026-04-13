package scene;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Responsible for drawing the main menu screen.
 *
 * @author Meiqi Zhao
 * @version 2026
 */
public class MenuDrawer {

    /**
     * Draws the full menu screen including background, title, buttons, and description box.
     *
     * @param gc            the GraphicsContext used for drawing
     * @param width         the width of the canvas
     * @param height        the height of the canvas
     * @param hoveredButton the index of the currently hovered button, or -1 if none
     */
    public void drawMenu(final GraphicsContext gc, final double width, final double height,
                         final int hoveredButton) {
        gc.setFill(Color.rgb(10, 15, 24));
        gc.fillRect(0, 0, width, height);

        gc.setFill(Color.rgb(220, 160, 20));
        gc.setFont(Font.font("Courier New", FontWeight.BOLD, 52));
        gc.fillText("DEADLINE", width / 2 - textWidth("DEADLINE", 52) / 2, height * 0.22);
        gc.fillText("DEFENDER", width / 2 - textWidth("DEFENDER", 52) / 2, height * 0.33);

        drawButton(gc, width / 2 - 110, height * 0.50, 180, 44, "Start",
                hoveredButton == 0, Color.rgb(220, 160, 20));
        drawButton(gc, width / 2 + 110, height * 0.50, 180, 44, "Quit",
                hoveredButton == 1, Color.rgb(220, 160, 20));

        double boxX = width / 2 - 230;
        double boxY = height * 0.63;
        double boxWidth = 460;
        double boxHeight = 110;

        gc.setFill(Color.rgb(200, 140, 10));
        gc.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 8, 8);

        gc.setFill(Color.rgb(10, 15, 5));
        gc.setFont(Font.font("Courier New", FontWeight.BOLD,13));
        String line1 = "Match the color of the falling task with your launcher.";
        String line2 = "Use ← → to move, SPACE to shoot, Z to switch color.";
        String line3 = "Miss too many tasks and it's game over. Good luck!";
        gc.fillText(line1, boxX + 18, boxY + 35);
        gc.fillText(line2, boxX + 18, boxY + 60);
        gc.fillText(line3, boxX + 18, boxY + 85);
    }

    /**
     * Draws a single button with pixel-style border and label.
     *
     * @param gc           the GraphicsContext used for drawing
     * @param centerX      the center x-coordinate of the button
     * @param centerY      the center y-coordinate of the button
     * @param buttonWidth  the width of the button
     * @param buttonHeight the height of the button
     * @param label        the text label displayed on the button
     * @param hovered      whether the button is currently hovered
     * @param baseColor    the base color of the button
     */
    public void drawButton(final GraphicsContext gc, final double centerX, final double centerY,
                           final double buttonWidth, final double buttonHeight, final String label,
                           final boolean hovered, final Color baseColor) {
        double buttonX = centerX - buttonWidth / 2;
        double buttonY = centerY - buttonHeight / 2;

        gc.setFill(Color.rgb(0, 0, 0, 0));
        gc.fillRoundRect(buttonX, buttonY, buttonWidth, buttonHeight, 6, 6);

        gc.setStroke(hovered ? Color.WHITE : baseColor);
        gc.setLineWidth(hovered ? 3 : 2);
        gc.strokeRoundRect(buttonX, buttonY, buttonWidth, buttonHeight, 6, 6);

        gc.setFill(hovered ? Color.WHITE : baseColor);
        gc.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        double labelWidth = textWidth(label, 16);
        gc.fillText(label, centerX - labelWidth / 2, centerY + 6);
    }
    /**
     * Returns the Y bounds of each button for hit-testing.
     *
     * @param height the height of the canvas
     * @return a 2D array of [startY, endY] per button
     */
    public double[][] buttonBounds(final double height) {
        double buttonHeight = 44;
        return new double[][] {
                { height * 0.50 - buttonHeight / 2, height * 0.50 + buttonHeight / 2 },
                { height * 0.50 - buttonHeight / 2, height * 0.50 + buttonHeight / 2 }
        };
    }

    /**
     * Returns the X bounds of each button for hit-testing.
     *
     * @param width the width of the canvas
     * @return a 2D array of [startX, endX] per button
     */
    public double[][] buttonXBounds(final double width) {
        double buttonWidth = 180;
        return new double[][] {
                { width / 2 - 110 - buttonWidth / 2, width / 2 - 110 + buttonWidth / 2 },
                { width / 2 + 110 - buttonWidth / 2, width / 2 + 110 + buttonWidth / 2 }
        };
    }

    /**
     * Approximates the rendered width of a string at the given font size.
     *
     * @param text the text to measure
     * @param size the font size
     * @return the approximate width as a double
     */
    private double textWidth(final String text, final double size) {
        return text.length() * size * 0.58;
    }
}