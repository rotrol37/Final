package objects;

import enums.BallColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Ball class.
 *
 * @author Meiqi Zhao
 * @version 2026
 */
class BallTest {

    private Ball ball;

    @BeforeEach
    void setUp() {
        ball = new Ball(100, 200, BallColor.RED);
    }

    // constructor tests

    @Test
    void constructorSetsXCorrectly() {
        assertEquals(100, ball.getX());
    }

    @Test
    void constructorSetsYCorrectly() {
        assertEquals(200, ball.getY());
    }

    @Test
    void constructorSetsColorCorrectly() {
        assertEquals(BallColor.RED, ball.getColor());
    }

    // update tests

    @Test
    void updateMovesballUpward() {
        final double yBefore;
        yBefore = ball.getY();
        ball.update();
        assertTrue(ball.getY() < yBefore);
    }

    @Test
    void updateDecreaseYBySpeed() {
        final double yBefore;
        yBefore = ball.getY();
        ball.update();
        assertEquals(yBefore - 7.5, ball.getY(), 1e-9);
    }

    // isOffScreen tests

    @Test
    void isOffScreenReturnsFalseWhenOnScreen() {
        assertFalse(ball.isOffScreen());
    }

    @Test
    void isOffScreenReturnsTrueWhenAboveScreen() {
        final Ball offScreen;
        offScreen = new Ball(100, -50, BallColor.RED);
        assertTrue(offScreen.isOffScreen());
    }

    @Test
    void isOffScreenReturnsFalseWhenAtTopEdge() {
        final Ball atEdge;
        atEdge = new Ball(100, 0, BallColor.RED);
        assertFalse(atEdge.isOffScreen());
    }

    // equals tests

    @Test
    void equalsReturnsTrueForSameObject() {
        assertEquals(ball, ball);
    }

    @Test
    void equalsReturnsTrueForSameValues() {
        final Ball same;
        same = new Ball(100, 200, BallColor.RED);
        assertEquals(ball, same);
    }

    @Test
    void equalsReturnsFalseForDifferentX() {
        final Ball different;
        different = new Ball(999, 200, BallColor.RED);
        assertNotEquals(ball, different);
    }

    @Test
    void equalsReturnsFalseForDifferentY() {
        final Ball different;
        different = new Ball(100, 999, BallColor.RED);
        assertNotEquals(ball, different);
    }

    @Test
    void equalsReturnsFalseForDifferentColor() {
        final Ball different;
        different = new Ball(100, 200, BallColor.YELLOW);
        assertNotEquals(ball, different);
    }

    @Test
    void equalsReturnsFalseForNull() {
        assertNotEquals(null, ball);
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    void equalsReturnsFalseForDifferentType() {
        assertNotEquals("not a ball", ball);
    }

    // hashCode tests

    @Test
    void hashCodeIsConsistent() {
        assertEquals(ball.hashCode(), ball.hashCode());
    }

    @Test
    void hashCodeIsEqualForEqualObjects() {
        final Ball same;
        same = new Ball(100, 200, BallColor.RED);
        assertEquals(ball.hashCode(), same.hashCode());
    }

    // toString tests

    @Test
    void toStringContainsColor() {
        assertTrue(ball.toString().contains("color="));
    }

    @Test
    void toStringContainsSpeed() {
        assertTrue(ball.toString().contains("speed="));
    }
}