package objects;

import enums.BallColor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents a ball fired by the launcher that travels upward.
 *
 * @author Meiqi Zhao
 * @version 2026
 */
public class Ball extends GameObject {
    /**
     * The color of this ball.
     */
    private final BallColor color;
    /**
     * The upward travel speed of this ball.
     */
    private final double speed;

    /**
     * Constructs a Ball at the given position with the specified color.
     *
     * @param x     the initial x-coordinate of the ball
     * @param y     the initial y-coordinate of the ball
     * @param color the BallColor of this ball
     */
    public Ball(double x, double y, BallColor color) {
        super(x, y, 18, 18);
        this.color = color;
        this.speed = 7.5;
    }

    /**
     * Updates the ball's position by moving it upward by its speed.
     */
    @Override
    public void update() {
        y -= speed;
    }

    /**
     * Returns whether this ball has moved above the visible screen area.
     *
     * @return true if the ball is off-screen, false otherwise
     */
    public boolean isOffScreen() {
        return y + height < 0;
    }

    /**
     * Renders the ball onto the given GraphicsContext as a filled circle.
     *
     * @param gc the GraphicsContext used for drawing
     */
    @Override
    public void render(GraphicsContext gc) {
        double radius = width / 2;
        double centerX = x;
        double centerY = y;
        Color baseColor = color.toFxColor();
        gc.setFill(baseColor);
        gc.fillOval(centerX - radius, centerY - radius, width, height);
    }

    /**
     * Returns the color of this ball.
     *
     * @return the BallColor of this ball
     */
    public BallColor getColor() {
        return color;
    }

    /**
     * Compares this Ball to another object for equality.
     * Two balls are equal if they share the same position, speed, and color.
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

        final Ball ball;
        ball = (Ball) object;

        return Double.compare(x, ball.x) == 0 &&
                Double.compare(y, ball.y) == 0 &&
                Double.compare(speed, ball.speed) == 0 &&
                color == ball.color;
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
        result = usefulPrime * result + Double.hashCode(speed);
        return result;
    }

    /**
     * Returns a string representation of this Ball.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        final StringBuilder stringBuilder;
        stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append(", objects.Ball{");
        stringBuilder.append("color=").append(color);
        stringBuilder.append(", speed=").append(speed);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}