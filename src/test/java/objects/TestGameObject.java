package objects;

import enums.BallColor;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the GameObject class using Ball as a concrete implementation.
 *
 * @author Meiqi Zhao
 * @version 2026
 */
class GameObjectTest {

    private Ball gameObject;

    @BeforeEach
    void setUp() {
        gameObject = new Ball(100, 200, BallColor.RED);
    }

    // constructor tests

    @Test
    void constructorSetsXCorrectly() {
        assertEquals(100, gameObject.getX());
    }

    @Test
    void constructorSetsYCorrectly() {
        assertEquals(200, gameObject.getY());
    }

    @Test
    void constructorSetsWidthCorrectly() {
        assertEquals(18, gameObject.getWidth());
    }

    @Test
    void constructorSetsHeightCorrectly() {
        assertEquals(18, gameObject.getHeight());
    }

    // getPosition tests

    @Test
    void getPositionReturnsCorrectX() {
        final Point2D position;
        position = gameObject.getPosition();
        assertEquals(100, position.getX());
    }

    @Test
    void getPositionReturnsCorrectY() {
        final Point2D position;
        position = gameObject.getPosition();
        assertEquals(200, position.getY());
    }

    @Test
    void getPositionReturnsNewPointEachTime() {
        final Point2D pos1;
        final Point2D pos2;
        pos1 = gameObject.getPosition();
        pos2 = gameObject.getPosition();
        assertEquals(pos1, pos2);
    }

    // setPosition tests

    @Test
    void setPositionUpdatesX() {
        gameObject.setPosition(new Point2D(50, 200));
        assertEquals(50, gameObject.getX());
    }

    @Test
    void setPositionUpdatesY() {
        gameObject.setPosition(new Point2D(100, 75));
        assertEquals(75, gameObject.getY());
    }

    @Test
    void setPositionUpdatesXAndYTogether() {
        gameObject.setPosition(new Point2D(300, 400));
        assertEquals(300, gameObject.getX());
        assertEquals(400, gameObject.getY());
    }

    @Test
    void setPositionAcceptsZero() {
        gameObject.setPosition(new Point2D(0, 0));
        assertEquals(0, gameObject.getX());
        assertEquals(0, gameObject.getY());
    }

    @Test
    void setPositionAcceptsNegativeValues() {
        gameObject.setPosition(new Point2D(-50, -100));
        assertEquals(-50, gameObject.getX());
        assertEquals(-100, gameObject.getY());
    }

    @Test
    void setPositionCanBeCalledMultipleTimes() {
        gameObject.setPosition(new Point2D(10, 20));
        gameObject.setPosition(new Point2D(30, 40));
        assertEquals(30, gameObject.getX());
        assertEquals(40, gameObject.getY());
    }

    // equals tests

    @Test
    void equalsReturnsTrueForSameObject() {
        assertEquals(gameObject, gameObject);
    }

    @Test
    void equalsReturnsTrueForSameValues() {
        final Ball same;
        same = new Ball(100, 200, BallColor.RED);
        assertEquals(gameObject, same);
    }

    @Test
    void equalsReturnsFalseForNull() {
        assertNotEquals(null, gameObject);
    }

    @Test
    void equalsReturnsFalseForDifferentType() {
        assertNotEquals("not a gameobject", gameObject);
    }

    @Test
    void equalsReturnsFalseForDifferentX() {
        final Ball different;
        different = new Ball(999, 200, BallColor.RED);
        assertNotEquals(gameObject, different);
    }

    @Test
    void equalsReturnsFalseForDifferentY() {
        final Ball different;
        different = new Ball(100, 999, BallColor.RED);
        assertNotEquals(gameObject, different);
    }

    @Test
    void equalsReturnsFalseAfterPositionChange() {
        final Ball same;
        same = new Ball(100, 200, BallColor.RED);
        gameObject.setPosition(new Point2D(999, 999));
        assertNotEquals(gameObject, same);
    }

    // hashCode tests

    @Test
    void hashCodeIsConsistent() {
        assertEquals(gameObject.hashCode(), gameObject.hashCode());
    }

    @Test
    void hashCodeIsEqualForEqualObjects() {
        final Ball same;
        same = new Ball(100, 200, BallColor.RED);
        assertEquals(gameObject.hashCode(), same.hashCode());
    }

    @Test
    void hashCodeChangeAfterPositionChange() {
        final int hashBefore;
        hashBefore = gameObject.hashCode();
        gameObject.setPosition(new Point2D(999, 999));
        assertNotEquals(hashBefore, gameObject.hashCode());
    }

    // toString tests

    @Test
    void toStringContainsX() {
        assertTrue(gameObject.toString().contains("x="));
    }

    @Test
    void toStringContainsY() {
        assertTrue(gameObject.toString().contains("y="));
    }

    @Test
    void toStringContainsWidth() {
        assertTrue(gameObject.toString().contains("width="));
    }

    @Test
    void toStringContainsHeight() {
        assertTrue(gameObject.toString().contains("height="));
    }

    @Test
    void toStringContainsCorrectXValue() {
        assertTrue(gameObject.toString().contains("x=100"));
    }

    @Test
    void toStringContainsCorrectYValue() {
        assertTrue(gameObject.toString().contains("y=200"));
    }

    @Test
    void toStringContainsCorrectWidthValue() {
        assertTrue(gameObject.toString().contains("width=18"));
    }

    @Test
    void toStringContainsCorrectHeightValue() {
        assertTrue(gameObject.toString().contains("height=18"));
    }

    @Test
    void toStringContainsClassName() {
        assertTrue(gameObject.toString().contains("Ball"));
    }
}