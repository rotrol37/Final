import enums.CollisionResult;
import objects.Ball;
import objects.Task;

/**
 * Check collisions between balls and tasks.
 *
 * @author Mittapap
 * @version 2026
 */
public final class CollisionDetector {

    private static final double TWO = 2.0;

    /**
     * Checks if the ball overlaps the task.
     *
     * @param ball the ball as a Ball
     * @param task the task as a Task
     * @return true if the ball overlaps the task, else false
     */
    public boolean checkCollision(final Ball ball, final Task task) {
        double ballLeft = ball.getX() - ball.getWidth() / TWO;
        double ballTop = ball.getY() - ball.getHeight() / TWO;
        double ballRight = ballLeft + ball.getWidth();
        double ballBottom = ballTop + ball.getHeight();
        double taskLeft = task.getX();
        double taskTop = task.getY();
        double taskRight = taskLeft + task.getWidth();
        double taskBottom = taskTop + task.getHeight();
        if (ballLeft >= taskRight) {
            return false;
        }
        if (ballRight <= taskLeft) {
            return false;
        }
        if (ballTop >= taskBottom) {
            return false;
        }
        return !(ballBottom <= taskTop);
    }

    /**
     * Resolves the collision result from the ball and task colors.
     *
     * @param ball the colliding ball as a Ball
     * @param task the colliding task as a Task
     * @return the collision result as a CollisionResult
     */
    public CollisionResult resolve(final Ball ball, final Task task) {
        CollisionResult collisionResult;
        if (ball.getColor() == task.getColor()) {
            collisionResult = CollisionResult.CLEAR;
        } else {
            collisionResult = CollisionResult.PENALTY;
        }
        return collisionResult;
    }
}
