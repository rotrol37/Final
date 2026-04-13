package objects;

import enums.BallColor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Represents a falling task block that the player must match by color.
 *
 * @author Meiqi Zhao
 * @version 2026
 */
public class Task extends GameObject {
    /**
     * The color of this task block.
     */
    private final BallColor color;
    /**
     * The speed at which this task falls down the screen.
     */
    private final double fallSpeed;

    /**
     * Constructs a Task at the given position with the specified color and fall speed.
     *
     * @param x         the initial x-coordinate of the task
     * @param y         the initial y-coordinate of the task
     * @param color     the BallColor of this task
     * @param fallSpeed the speed at which the task falls
     */
    public Task(double x, double y, BallColor color, double fallSpeed) {
        super(x, y, 60, 32);
        this.color = color;
        this.fallSpeed = fallSpeed;
    }

    /**
     * Updates the task's position by moving it downward by its fall speed.
     */
    @Override
    public void update() {
        y += fallSpeed;
    }

    /**
     * Returns whether this task has moved below the visible screen area.
     *
     * @param sceneHeight the height of the scene
     * @return true if the task is off-screen, false otherwise
     */
    public boolean isOffScreen(double sceneHeight) {
        return y > sceneHeight;
    }

    /**
     * Renders the task block onto the given GraphicsContext,
     * including shadow, gradient body, border, and centered color label.
     *
     * @param gc the GraphicsContext used for drawing
     */
    @Override
    public void render(GraphicsContext gc) {
        Color baseColor = color.toFxColor();
        drawShadow(gc, x + 4, y + 4, width, height);

        gc.setFill(baseColor);
        gc.fillRect(x, y, width, height);

        gc.setFill(baseColor.brighter().brighter());
        gc.fillRect(x, y, width, 2);
        gc.fillRect(x, y, 2, height);

        gc.setFill(baseColor.darker().darker());
        gc.fillRect(x + width - 3, y, 3, height);
        gc.fillRect(x, y + height - 3, width, 3);

        gc.setTextAlign(javafx.scene.text.TextAlignment.CENTER);
        gc.setTextBaseline(javafx.geometry.VPos.CENTER);
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Courier New", FontWeight.BOLD, 11));
        gc.fillText(color.name(), x + width / 2, y + height / 2);
    }

    /**
     * Returns the color of this task.
     *
     * @return the BallColor of this task
     */
    public BallColor getColor() { return color; }

    /**
     * Compares this Task to another object for equality.
     * Two tasks are equal if they share the same position, fall speed, and color.
     *
     * @param object the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final Task task;
        task = (Task) object;

        return Double.compare(x, task.x) == 0 &&
                Double.compare(y, task.y) == 0 &&
                Double.compare(fallSpeed, task.fallSpeed) == 0 &&
                color == task.color;
    }

    /**
     * Returns a hash code consistent with equals.
     *
     * @return the hash code as an int
     */
    @Override
    public int hashCode() {
        final int usefulPrime;
        int result;

        usefulPrime = 31;
        result = color.hashCode();
        result = usefulPrime * result + Double.hashCode(x);
        result = usefulPrime * result + Double.hashCode(y);
        result = usefulPrime * result + Double.hashCode(fallSpeed);
        return result;
    }

    /**
     * Returns a string representation of this Task.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        final StringBuilder sb;
        sb = new StringBuilder(super.toString());
        sb.append(", objects.Task{");
        sb.append("color=").append(color);
        sb.append(", fallSpeed=").append(fallSpeed);
        sb.append('}');
        return sb.toString();
    }
}