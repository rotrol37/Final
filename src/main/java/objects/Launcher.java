package objects;

import enums.BallColor;
import enums.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents a launcher that moves horizontally and shoots colored balls.
 *
 * @author Meiqi Zhao
 * @version 2026
 */

public class Launcher extends GameObject {
    /**
     * The current color of the ball to be shot.
     */
    private BallColor currentColor;
    /**
     * The movement speed of the launcher.
     */
    private final double speed;

    /**
     * Constructs a Launcher at the given position with a default color and speed.
     *
     * @param x the initial x-coordinate of the launcher
     * @param y the initial y-coordinate of the launcher
     */
    public Launcher(double x, double y) {
        super(x, y, 64, 38);
        this.currentColor = BallColor.RED;
        this.speed = 7.5;
    }

    /**
     * Moves the launcher left or right within the scene boundaries.
     *
     * @param direction  the direction to move; either LEFT or RIGHT
     * @param sceneWidth the width of the scene used to clamp the position
     */
    public void move(Direction direction, double sceneWidth) {
        if (direction == Direction.LEFT)  x = Math.max(width / 2, x - speed);
        if (direction == Direction.RIGHT) x = Math.min(sceneWidth - width / 2, x + speed);
    }


    /**
     * Switches the current ball color to the next color in the sequence.
     */
    public void switchColor() {
        currentColor = currentColor.next();
    }

    /**
     * Creates and returns a new Ball shot from the launcher's current position.
     *
     * @return a new Ball at the launcher's nozzle position with the current color
     */
    public Ball shoot() {
        return new Ball(x, y - height / 2 - 10, currentColor);
    }

    /**
     * Returns the current ball color of the launcher.
     *
     * @return the current BallColor
     */
    public BallColor getCurrentColor() { return currentColor; }

    /**
     * Updates the launcher state with a time delta. Currently unused.
     *
     * @param dt the time delta in seconds
     */
    public void update(double dt) {}

    /**
     * Updates the launcher state. Currently unused.
     */
    @Override
    public void update() {

    }

    /**
     * Renders the launcher onto the given GraphicsContext, including barrel,
     * body, indicator, and wheels.
     *
     * @param gc the GraphicsContext used for drawing
     */
    @Override
    public void render(GraphicsContext gc) {
        Color baseColor = currentColor.toFxColor();

        double centerX = x;
        double centerY = y;
        double bodyX = centerX - width / 2;
        double bodyY = centerY - height / 2;
        gc.setFill(Color.rgb(0, 0, 0, 0.5));
        gc.fillRect(bodyX + 4, bodyY + 4, width, height);
        gc.setFill(baseColor);
        gc.fillRect(bodyX, bodyY, width, height);
        gc.setFill(baseColor.brighter().brighter());
        gc.fillRect(bodyX, bodyY, width, 3);
        gc.fillRect(bodyX, bodyY, 3, height);
        gc.setFill(baseColor.darker().darker());
        gc.fillRect(bodyX + width - 3, bodyY, 3, height);
        gc.fillRect(bodyX, bodyY + height - 3, width, 3);
        double barrelWidth = 16;
        double barrelHeight = 18;
        double barrelX = centerX - barrelWidth / 2;
        double barrelY = bodyY - barrelHeight;
        gc.setFill(Color.rgb(60, 60, 75));
        gc.fillRect(barrelX, barrelY, barrelWidth, barrelHeight);
        gc.setFill(Color.rgb(100, 100, 115));
        gc.fillRect(barrelX, barrelY, 2, barrelHeight);
        double wheelY = centerY + height / 2 - 10;
        gc.setFill(Color.rgb(45, 45, 58));
        gc.fillRect(centerX - 22 - 10, wheelY, 20, 10);
        gc.fillRect(centerX + 22 - 10, wheelY, 20, 10);
        gc.setFill(Color.WHITE);
        gc.fillRect(centerX - 5, bodyY + 4, 10, 8);
        gc.setFill(Color.rgb(150, 220, 255));
        gc.fillRect(centerX - 4, bodyY + 5, 8, 6);
    }

    /**
     * Compares this objects.Launcher to another object for equality.
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

        final Launcher launcher;
        launcher = (Launcher) object;

        return Double.compare(x, launcher.x) == 0 &&
                Double.compare(y, launcher.y) == 0 &&
                Double.compare(speed, launcher.speed) == 0 &&
                currentColor == launcher.currentColor;
    }

    /**
     * Returns the hash code of the objects.Launcher.
     *
     * @return hash code value
     */
    @Override
    public int hashCode() {
        final int usefulPrime;
        int result;

        usefulPrime = 31;
        result = currentColor.hashCode();
        result = usefulPrime * result + Double.hashCode(x);
        result = usefulPrime * result + Double.hashCode(y);
        result = usefulPrime * result + Double.hashCode(speed);
        return result;
    }

    /**
     * Returns a string representation of the objects.Launcher.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        final StringBuilder sb;
        sb = new StringBuilder(super.toString());
        sb.append(", objects.Launcher{");
        sb.append("currentColor=").append(currentColor);
        sb.append(", speed=").append(speed);
        sb.append('}');
        return sb.toString();
    }
}
