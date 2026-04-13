import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class GameDrawer {

    private static final double TITLE_X = 180;
    private static final double TITLE_Y = 40;
    private static final int TITLE_FONT_SIZE = 32;
    private static final Color BACKGROUND_COLOR = Color.rgb(10, 15, 24);
    private static final Color TEXT_COLOR = Color.rgb(220, 160, 20);

    public void draw(final GraphicsContext gc, final double canvasWidth, final double canvasHeight) {
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        drawBackground(gc, canvasWidth, canvasHeight);
        drawTitle(gc, canvasWidth, canvasHeight);
    }

    private void drawBackground(final GraphicsContext gc, final double canvasWidth, final double canvasHeight) {
        gc.setFill(BACKGROUND_COLOR);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    private void drawTitle(final GraphicsContext gc, final double canvasWidth, final double canvasHeight) {
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFill(TEXT_COLOR);
        gc.setFont(Font.font("Courier New", FontWeight.BOLD, TITLE_FONT_SIZE));
        gc.fillText("Deadline Defender", TITLE_X, TITLE_Y);
    }

}
