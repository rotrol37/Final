package objects;

import enums.BallColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Task class.
 *
 * @author Meiqi Zhao
 * @version 2026
 */
class TaskTest {

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task(100, 200, BallColor.RED, 3.0);
    }

    // constructor tests

    @Test
    void constructorSetsXCorrectly() {
        assertEquals(100, task.getX());
    }

    @Test
    void constructorSetsYCorrectly() {
        assertEquals(200, task.getY());
    }

    @Test
    void constructorSetsColorCorrectly() {
        assertEquals(BallColor.RED, task.getColor());
    }

    @Test
    void constructorSetsWidthCorrectly() {
        assertEquals(60, task.getWidth());
    }

    @Test
    void constructorSetsHeightCorrectly() {
        assertEquals(32, task.getHeight());
    }

    // update tests

    @Test
    void updateMovesTaskDownward() {
        final double yBefore;
        yBefore = task.getY();
        task.update();
        assertTrue(task.getY() > yBefore);
    }

    @Test
    void updateIncreaseYByFallSpeed() {
        final double yBefore;
        yBefore = task.getY();
        task.update();
        assertEquals(yBefore + 3.0, task.getY(), 1e-9);
    }

    @Test
    void updateDoesNotChangeX() {
        final double xBefore;
        xBefore = task.getX();
        task.update();
        assertEquals(xBefore, task.getX());
    }

    @Test
    void updateCanBeCalledMultipleTimes() {
        final double yBefore;
        yBefore = task.getY();
        task.update();
        task.update();
        task.update();
        assertEquals(yBefore + 9.0, task.getY(), 1e-9);
    }

    // isOffScreen tests

    @Test
    void isOffScreenReturnsFalseWhenOnScreen() {
        assertFalse(task.isOffScreen(600));
    }

    @Test
    void isOffScreenReturnsTrueWhenBelowScreen() {
        final Task offScreen;
        offScreen = new Task(100, 700, BallColor.RED, 3.0);
        assertTrue(offScreen.isOffScreen(600));
    }

    @Test
    void isOffScreenReturnsFalseWhenAtTopOfScreen() {
        final Task atTop;
        atTop = new Task(100, 0, BallColor.RED, 3.0);
        assertFalse(atTop.isOffScreen(600));
    }

    @Test
    void isOffScreenDependsOnSceneHeight() {
        final Task atEdge;
        atEdge = new Task(100, 600, BallColor.RED, 3.0);
        assertFalse(atEdge.isOffScreen(600));
        assertTrue(atEdge.isOffScreen(500));
    }

    // equals tests

    @Test
    void equalsReturnsTrueForSameObject() {
        assertEquals(task, task);
    }

    @Test
    void equalsReturnsTrueForSameValues() {
        final Task same;
        same = new Task(100, 200, BallColor.RED, 3.0);
        assertEquals(task, same);
    }

    @Test
    void equalsReturnsFalseForNull() {
        assertNotEquals(null, task);
    }

    @Test
    void equalsReturnsFalseForDifferentType() {
        assertNotEquals("not a task", task);
    }

    @Test
    void equalsReturnsFalseForDifferentX() {
        final Task different;
        different = new Task(999, 200, BallColor.RED, 3.0);
        assertNotEquals(task, different);
    }

    @Test
    void equalsReturnsFalseForDifferentY() {
        final Task different;
        different = new Task(100, 999, BallColor.RED, 3.0);
        assertNotEquals(task, different);
    }

    @Test
    void equalsReturnsFalseForDifferentColor() {
        final Task different;
        different = new Task(100, 200, BallColor.YELLOW, 3.0);
        assertNotEquals(task, different);
    }

    @Test
    void equalsReturnsFalseForDifferentFallSpeed() {
        final Task different;
        different = new Task(100, 200, BallColor.RED, 9.0);
        assertNotEquals(task, different);
    }

    // hashCode tests

    @Test
    void hashCodeIsConsistent() {
        assertEquals(task.hashCode(), task.hashCode());
    }

    @Test
    void hashCodeIsEqualForEqualObjects() {
        final Task same;
        same = new Task(100, 200, BallColor.RED, 3.0);
        assertEquals(task.hashCode(), same.hashCode());
    }

    @Test
    void hashCodeDiffersForDifferentColor() {
        final Task different;
        different = new Task(100, 200, BallColor.YELLOW, 3.0);
        assertNotEquals(task.hashCode(), different.hashCode());
    }

    @Test
    void hashCodeDiffersForDifferentFallSpeed() {
        final Task different;
        different = new Task(100, 200, BallColor.RED, 9.0);
        assertNotEquals(task.hashCode(), different.hashCode());
    }

    // toString tests

    @Test
    void toStringContainsColor() {
        assertTrue(task.toString().contains("color="));
    }

    @Test
    void toStringContainsFallSpeed() {
        assertTrue(task.toString().contains("fallSpeed="));
    }

    @Test
    void toStringContainsX() {
        assertTrue(task.toString().contains("x="));
    }

    @Test
    void toStringContainsY() {
        assertTrue(task.toString().contains("y="));
    }

    @Test
    void toStringContainsCorrectColorValue() {
        assertTrue(task.toString().contains("RED"));
    }

    @Test
    void toStringContainsCorrectFallSpeedValue() {
        assertTrue(task.toString().contains("3.0"));
    }
}