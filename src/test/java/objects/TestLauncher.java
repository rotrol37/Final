package objects;

import enums.BallColor;
import enums.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Launcher class.
 *
 * @author Meiqi Zhao
 * @version 2026
 */
class LauncherTest {

    private Launcher launcher;

    @BeforeEach
    void setUp() {
        launcher = new Launcher(100, 200);
    }

    // constructor tests

    @Test
    void constructorSetsXCorrectly() {
        assertEquals(100, launcher.getX());
    }

    @Test
    void constructorSetsYCorrectly() {
        assertEquals(200, launcher.getY());
    }

    @Test
    void constructorSetsWidthCorrectly() {
        assertEquals(64, launcher.getWidth());
    }

    @Test
    void constructorSetsHeightCorrectly() {
        assertEquals(38, launcher.getHeight());
    }

    @Test
    void constructorSetsDefaultColorToRed() {
        assertEquals(BallColor.RED, launcher.getCurrentColor());
    }

    // move tests

    @Test
    void moveLeftDecreasesX() {
        final double xBefore;
        xBefore = launcher.getX();
        launcher.move(Direction.LEFT, 800);
        assertTrue(launcher.getX() < xBefore);
    }

    @Test
    void moveRightIncreasesX() {
        final double xBefore;
        xBefore = launcher.getX();
        launcher.move(Direction.RIGHT, 800);
        assertTrue(launcher.getX() > xBefore);
    }

    @Test
    void moveLeftDoesNotGoBeyondLeftBoundary() {
        final Launcher atLeftEdge;
        atLeftEdge = new Launcher(0, 200);
        atLeftEdge.move(Direction.LEFT, 800);
        assertTrue(atLeftEdge.getX() >= 0);
    }

    @Test
    void moveRightDoesNotGoBeyondRightBoundary() {
        final Launcher atRightEdge;
        atRightEdge = new Launcher(800, 200);
        atRightEdge.move(Direction.RIGHT, 800);
        assertTrue(atRightEdge.getX() <= 800);
    }

    @Test
    void moveLeftBySpeedAmount() {
        final double xBefore;
        xBefore = launcher.getX();
        launcher.move(Direction.LEFT, 800);
        assertEquals(xBefore - 7.5, launcher.getX(), 1e-9);
    }

    @Test
    void moveRightBySpeedAmount() {
        final double xBefore;
        xBefore = launcher.getX();
        launcher.move(Direction.RIGHT, 800);
        assertEquals(xBefore + 7.5, launcher.getX(), 1e-9);
    }

    @Test
    void moveDoesNotChangeY() {
        final double yBefore;
        yBefore = launcher.getY();
        launcher.move(Direction.LEFT, 800);
        assertEquals(yBefore, launcher.getY());
    }

    // switchColor tests

    @Test
    void switchColorChangesFromRedToYellow() {
        launcher.switchColor();
        assertEquals(BallColor.YELLOW, launcher.getCurrentColor());
    }

    @Test
    void switchColorChangesFromYellowToGreen() {
        launcher.switchColor();
        launcher.switchColor();
        assertEquals(BallColor.GREEN, launcher.getCurrentColor());
    }

    @Test
    void switchColorWrapsAroundToRed() {
        launcher.switchColor();
        launcher.switchColor();
        launcher.switchColor();
        assertEquals(BallColor.RED, launcher.getCurrentColor());
    }

    // shoot tests

    @Test
    void shootReturnsABall() {
        final Ball ball;
        ball = launcher.shoot();
        assertNotNull(ball);
    }

    @Test
    void shootBallHasCorrectColor() {
        final Ball ball;
        ball = launcher.shoot();
        assertEquals(BallColor.RED, ball.getColor());
    }

    @Test
    void shootBallHasCorrectX() {
        final Ball ball;
        ball = launcher.shoot();
        assertEquals(launcher.getX(), ball.getX(), 1e-9);
    }

    @Test
    void shootBallIsAboveLauncher() {
        final Ball ball;
        ball = launcher.shoot();
        assertTrue(ball.getY() < launcher.getY());
    }

    @Test
    void shootAfterSwitchColorUsesNewColor() {
        launcher.switchColor();
        final Ball ball;
        ball = launcher.shoot();
        assertEquals(BallColor.YELLOW, ball.getColor());
    }

    // equals tests

    @Test
    void equalsReturnsTrueForSameObject() {
        assertEquals(launcher, launcher);
    }

    @Test
    void equalsReturnsTrueForSameValues() {
        final Launcher same;
        same = new Launcher(100, 200);
        assertEquals(launcher, same);
    }

    @Test
    void equalsReturnsFalseForNull() {
        assertNotEquals(null, launcher);
    }

    @Test
    void equalsReturnsFalseForDifferentType() {
        assertNotEquals("not a launcher", launcher);
    }

    @Test
    void equalsReturnsFalseForDifferentX() {
        final Launcher different;
        different = new Launcher(999, 200);
        assertNotEquals(launcher, different);
    }

    @Test
    void equalsReturnsFalseForDifferentY() {
        final Launcher different;
        different = new Launcher(100, 999);
        assertNotEquals(launcher, different);
    }

    @Test
    void equalsReturnsFalseForDifferentColor() {
        final Launcher different;
        different = new Launcher(100, 200);
        different.switchColor();
        assertNotEquals(launcher, different);
    }

    // hashCode tests

    @Test
    void hashCodeIsConsistent() {
        assertEquals(launcher.hashCode(), launcher.hashCode());
    }

    @Test
    void hashCodeIsEqualForEqualObjects() {
        final Launcher same;
        same = new Launcher(100, 200);
        assertEquals(launcher.hashCode(), same.hashCode());
    }

    @Test
    void hashCodeChangesAfterSwitchColor() {
        final int hashBefore;
        hashBefore = launcher.hashCode();
        launcher.switchColor();
        assertNotEquals(hashBefore, launcher.hashCode());
    }

    // toString tests

    @Test
    void toStringContainsCurrentColor() {
        assertTrue(launcher.toString().contains("currentColor="));
    }

    @Test
    void toStringContainsSpeed() {
        assertTrue(launcher.toString().contains("speed="));
    }

    @Test
    void toStringContainsX() {
        assertTrue(launcher.toString().contains("x="));
    }

    @Test
    void toStringContainsY() {
        assertTrue(launcher.toString().contains("y="));
    }

    @Test
    void toStringContainsCorrectColorValue() {
        assertTrue(launcher.toString().contains("RED"));
    }
}