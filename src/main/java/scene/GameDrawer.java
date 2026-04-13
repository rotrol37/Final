package scene;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import objects.Ball;
import objects.Task;

import java.util.List;

/**
 * Draws all game visuals onto the canvas.
 *
 * @author Mittapap
 * @version 2026
 */
public class GameDrawer {

    private static final double TITLE_X = 180;
    private static final double TITLE_Y = 40;
    private static final int TITLE_FONT_SIZE = 32;
    private static final Color BACKGROUND_COLOR = Color.rgb(10, 15, 24);
    private static final Color TEXT_COLOR = Color.rgb(220, 160, 20);
    private static final double SCORE_X = 500;
    private static final double SCORE_Y = 40;
    private static final double HIGH_SCORE_Y = 70;
    private static final int SCORE_FONT_SIZE_ACTIVE = 28;
    private static final int SCORE_FONT_SIZE_IDLE = 20;
    private static final int HIGH_SCORE_FONT_SIZE = 16;
    private static final double SPEED_UP_FONT_SIZE = 24;
    private static final double GAME_OVER_FONT_SIZE = 48;
    private static final double LINE_WIDTH_THICK = 2;
    private static final double BOX_WIDTH = 300;
    private static final double BOX_HEIGHT = 120;
    private static final double BOX_RADIUS = 12;
    private static final double INPUT_BOX_X_OFFSET = 80;
    private static final double INPUT_BOX_Y_OFFSET = 50;
    private static final double INPUT_BOX_WIDTH = 200;
    private static final double INPUT_BOX_HEIGHT = 30;
    private static final double INPUT_BOX_RADIUS = 6;
    private static final double NAME_LABEL_X_OFFSET = 40;
    private static final double NAME_LABEL_Y_OFFSET = 65;
    private static final double NAME_TEXT_X_OFFSET = 150;
    private static final double NAME_TEXT_Y_OFFSET = 71;
    private static final double HINT_Y_OFFSET = 105;
    private static final double TITLE_BOX_Y_OFFSET = 20;
    private static final int NAME_LABEL_FONT_SIZE = 18;
    private static final int NAME_TEXT_FONT_SIZE = 16;
    private static final int HINT_FONT_SIZE = 12;

    /**
     * Draws the background and title of the game scene.
     *
     * @param gc the GraphicsContext used for drawing
     * @param canvasWidth the width of the canvas as a double
     * @param canvasHeight the height of the canvas as a double
     */
    public void draw(final GraphicsContext gc, final double canvasWidth, final double canvasHeight) {
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        gc.setFill(BACKGROUND_COLOR);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFill(TEXT_COLOR);
        gc.setFont(Font.font("Courier New", FontWeight.BOLD, TITLE_FONT_SIZE));
        gc.fillText("Deadline Defender", TITLE_X, TITLE_Y);
    }

    /**
     * Draws all game objects onto the canvas.
     *
     * @param gc the GraphicsContext used for drawing
     * @param tasks the list of tasks as a List
     * @param balls the list of balls as a List
     */
    public void drawObjects(final GraphicsContext gc, final List<Task> tasks, final List<Ball> balls) {
        for (Task task : tasks) {
            task.render(gc);
        }
        for (Ball ball : balls) {
            ball.render(gc);
        }
    }

    /**
     * Draws the bottom penalty line.
     *
     * @param gc the GraphicsContext used for drawing
     * @param sceneWidth the width of the scene as a double
     * @param bottomLine the y-coordinate of the bottom line as a double
     * @param isHighlighted true if a hit or penalty just occurred
     * @param isMatchColor true if the highlight is a match, false if penalty
     */
    public void drawBottomLine(final GraphicsContext gc, final double sceneWidth,
                               final double bottomLine, final boolean isHighlighted,
                               final boolean isMatchColor) {
        if (isHighlighted && isMatchColor) {
            gc.setStroke(Color.rgb(60, 180, 80));
        } else if (isHighlighted) {
            gc.setStroke(Color.rgb(220, 60, 60));
        } else {
            gc.setStroke(Color.rgb(255, 255, 255, 0.6));
        }
        gc.setLineWidth(LINE_WIDTH_THICK);
        gc.strokeLine(0, bottomLine, sceneWidth, bottomLine);
    }

    /**
     * Draws the score display, highlighted when a hit or penalty occurred.
     *
     * @param gc the GraphicsContext used for drawing
     * @param score the current score as an int
     * @param highScoreDisplay the high score display string as a String
     * @param isHighlighted true if the score should be highlighted
     * @param isMatchColor true if the highlight color is green, false for red
     */
    public void drawHighlightedScore(final GraphicsContext gc, final int score,
                                     final String highScoreDisplay, final boolean isHighlighted,
                                     final boolean isMatchColor) {
        if (isHighlighted) {
            if (isMatchColor) {
                gc.setFill(Color.rgb(60, 180, 80));
            } else {
                gc.setFill(Color.rgb(220, 60, 60));
            }
            gc.setFont(Font.font("Arial", SCORE_FONT_SIZE_ACTIVE));
        } else {
            gc.setFill(Color.WHITE);
            gc.setFont(Font.font("Arial", SCORE_FONT_SIZE_IDLE));
        }
        gc.fillText("Score: " + score, SCORE_X, SCORE_Y);
        gc.setFill(Color.rgb(255, 215, 0));
        gc.setFont(Font.font("Arial", HIGH_SCORE_FONT_SIZE));
        gc.fillText("HighScore: " + highScoreDisplay, SCORE_X, HIGH_SCORE_Y);
    }

    /**
     * Draws the speed-up notification text.
     *
     * @param gc the GraphicsContext used for drawing
     * @param sceneWidth the width of the scene as a double
     * @param sceneHeight the height of the scene as a double
     */
    public void drawSpeedUp(final GraphicsContext gc,
                            final double sceneWidth,
                            final double sceneHeight) {
        gc.setFill(Color.YELLOW);
        gc.setFont(Font.font("Arial", SPEED_UP_FONT_SIZE));
        gc.fillText("Speed Up!", sceneWidth / 2 - 50, sceneHeight / 2);
    }

    /**
     * Draws the game over overlay.
     *
     * @param gc the GraphicsContext used for drawing
     * @param sceneWidth the width of the scene as a double
     * @param sceneHeight the height of the scene as a double
     */
    public void drawGameOver(final GraphicsContext gc, final double sceneWidth,
                             final double sceneHeight) {
        gc.setFill(Color.rgb(0, 0, 0, 0.8));
        gc.fillRect(0, 0, sceneWidth, sceneHeight);
        gc.setFill(Color.rgb(220, 60, 60));
        gc.setFont(Font.font("Courier New", GAME_OVER_FONT_SIZE));
        gc.fillText("GAME OVER", sceneWidth / 2, sceneHeight / 2);
        gc.fillText("Press R to Restart", sceneWidth / 2, sceneHeight / 2 + 50);
    }

    /**
     * Draws the name input box for a new high score entry.
     *
     * @param gc the GraphicsContext used for drawing
     * @param sceneWidth the width of the scene as a double
     * @param sceneHeight the height of the scene as a double
     * @param peakScore the new high score value as an int
     * @param typedText the text currently typed by the player as a String
     */
    public void drawNameInput(final GraphicsContext gc, final double sceneWidth,
                              final double sceneHeight, final int peakScore,
                              final String typedText) {
        double boxX = sceneWidth / 2 - BOX_WIDTH / 2;
        double boxY = sceneHeight / 2 - BOX_HEIGHT / 2;
        gc.setFill(Color.rgb(0, 0, 0, 0.8));
        gc.fillRect(0, 0, sceneWidth, sceneHeight);

        gc.setFill(Color.rgb(30, 30, 50));
        gc.fillRoundRect(boxX, boxY, BOX_WIDTH, BOX_HEIGHT, BOX_RADIUS, BOX_RADIUS);
        gc.setStroke(Color.rgb(255, 215, 0));
        gc.setLineWidth(LINE_WIDTH_THICK);
        gc.strokeRoundRect(boxX, boxY, BOX_WIDTH, BOX_HEIGHT, BOX_RADIUS, BOX_RADIUS);

        gc.setFill(Color.rgb(255, 215, 0));
        gc.setFont(Font.font("Arial", NAME_LABEL_FONT_SIZE));
        gc.fillText("New High Score: " + peakScore + "!",
                boxX + 100, boxY + TITLE_BOX_Y_OFFSET);

        gc.setFill(Color.rgb(255, 215, 0));
        gc.setFont(Font.font("Arial", NAME_LABEL_FONT_SIZE));
        gc.fillText("Name: ", boxX + NAME_LABEL_X_OFFSET, boxY + NAME_LABEL_Y_OFFSET);

        gc.setFill(Color.rgb(50, 50, 70));
        gc.fillRoundRect(boxX + INPUT_BOX_X_OFFSET, boxY + INPUT_BOX_Y_OFFSET,
                INPUT_BOX_WIDTH, INPUT_BOX_HEIGHT, INPUT_BOX_RADIUS, INPUT_BOX_RADIUS);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);
        gc.strokeRoundRect(boxX + INPUT_BOX_X_OFFSET, boxY + INPUT_BOX_Y_OFFSET,
                INPUT_BOX_WIDTH, INPUT_BOX_HEIGHT, INPUT_BOX_RADIUS, INPUT_BOX_RADIUS);

        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", NAME_TEXT_FONT_SIZE));
        gc.fillText(typedText, boxX + NAME_TEXT_X_OFFSET, boxY + NAME_TEXT_Y_OFFSET);

        gc.setFill(Color.rgb(180, 180, 180));
        gc.setFont(Font.font("Arial", HINT_FONT_SIZE));
        gc.fillText("Press ENTER to confirm",
                boxX + INPUT_BOX_X_OFFSET, boxY + HINT_Y_OFFSET);
    }
}