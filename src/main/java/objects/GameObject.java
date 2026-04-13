package objects;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents the base class for all game objects with position and dimensions.
 *
 * @author Meiqi Zhao
 * @version 2026
 */
public abstract class GameObject {
    /**
     * The x-coordinate of this game object.
     */
    protected double x;
    /**
     * The y-coordinate of this game object.
     */
    protected double y;
    /**
     * The width of this game object.
     */
    protected double width;
    /**
     * The height of this game object.
     */
    protected double height;

    /**
     * Constructs a GameObject at the given position with the specified dimensions.
     *
     * @param x      the initial x-coordinate
     * @param y      the initial y-coordinate
     * @param width  the width of the object
     * @param height the height of the object
     */
    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the current position of this game object as a Point2D.
     *
     * @return the position as a Point2D
     */
    public Point2D getPosition() {
        return new Point2D(x, y);
    }

    /**
     * Sets the position of this game object from a Point2D.
     *
     * @param position the new position as a Point2D
     */
    public void setPosition(Point2D position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    /**
     * Returns the x-coordinate of this game object.
     *
     * @return the x-coordinate as a double
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this game object.
     *
     * @return the y-coordinate as a double
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the width of this game object.
     *
     * @return the width as a double
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of this game object.
     *
     * @return the height as a double
     */
    public double getHeight() {
        return height;
    }

    /**
     * Updates the state of this game object by one step.
     */
    public abstract void update();

    /**
     * Draws a pixel-style drop shadow at the given position.
     *
     * @param gc     the GraphicsContext used for drawing
     * @param shadowX the x-coordinate of the shadow
     * @param shadowY the y-coordinate of the shadow
     * @param shadowWidth  the width of the shadow
     * @param shadowHeight the height of the shadow
     */
    protected void drawShadow(final GraphicsContext gc, final double shadowX,
                              final double shadowY, final double shadowWidth,
                              final double shadowHeight) {
        gc.setFill(Color.rgb(0, 0, 0, 0.5));
        gc.fillRect(shadowX, shadowY, shadowWidth, shadowHeight);
    }

    /**
     * Renders this game object onto the given GraphicsContext.
     *
     * @param gc the GraphicsContext used for drawing
     */
    public abstract void render(GraphicsContext gc);

    /**
     * Compares this GameObject to another object for equality.
     * Two game objects are equal if they share the same position and dimensions.
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

        final GameObject gameObject;
        gameObject = (GameObject) object;

        return Double.compare(x, gameObject.x) == 0 &&
                Double.compare(y, gameObject.y) == 0 &&
                Double.compare(width, gameObject.width) == 0 &&
                Double.compare(height, gameObject.height) == 0;
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
        result = Double.hashCode(x);
        result = usefulPrime * result + Double.hashCode(y);
        result = usefulPrime * result + Double.hashCode(width);
        result = usefulPrime * result + Double.hashCode(height);
        return result;
    }

    /**
     * Returns a string representation of this GameObject.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        final StringBuilder sb;
        sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append(", width=").append(width);
        sb.append(", height=").append(height);
        sb.append("}");
        return sb.toString();
    }
}