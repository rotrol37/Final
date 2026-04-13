import java.util.Objects;
import java.util.Random;

import enums.BallColor;
import objects.Task;

/**
 * Spawns falling tasks over time for the game.
 *
 * @author Mittapap
 * @version 2026
 */
public class TaskSpawner {

    private static final double DEFAULT_SPAWN_INTERVAL = 150;

    private final int sceneWidth;
    private final Random random;
    private final double spawnInterval;
    private double timer;
    private double speedMultiplier;

    /**
     * Constructs a TaskSpawner for the specified scene width.
     *
     * @param sceneWidth the scene width as an int
     */
    public TaskSpawner(final int sceneWidth) {
        this.sceneWidth = sceneWidth;
        this.random = new Random();
        this.spawnInterval = DEFAULT_SPAWN_INTERVAL;
        this.timer = 0;
        this.speedMultiplier = 0.5;
    }

    /**
     * Updates the internal timer and returns a new Task when spawning occurs.
     *
     * @return a new Task when spawning occurs, else null
     */
    public Task update() {
        Task spawnedTask = null;
        this.timer ++;
        if (this.timer >= getSpawnInterval()) {
            this.timer = 0;
            spawnedTask = spawnTask();
        }
        return spawnedTask;
    }

    /**
     * Increases the fall speed and spawn rate of tasks.
     */
    public void increaseDifficulty() {
        this.speedMultiplier += 0.3;
    }

    /**
     * Resets the timer and speed multiplier to their initial values.
     */
    public void reset() {
        this.timer = 0;
        this.speedMultiplier = 0.5;
    }

    /* Creates a new task using a random color, x position, and constant speed. */
    private Task spawnTask() {
        BallColor[] colors = BallColor.values();
        BallColor color = colors[this.random.nextInt(colors.length)];
        double fallSpeed = speedMultiplier * 2.0;
        double maxSpawnX = getSceneWidth() - 60.0;
        if (maxSpawnX < 0) {
            maxSpawnX = 0;
        }
        double spawnX = this.random.nextDouble() * maxSpawnX;
        return new Task(spawnX, -36.0, color, fallSpeed);
    }

    /**
     * Returns the current spawn interval.
     *
     * @return the spawn interval as a double
     */
    public double getSpawnInterval() {
        return this.spawnInterval;
    }

    /**
     * Returns the current timer value.
     *
     * @return the timer as a double
     */
    public double getTimer() {
        return this.timer;
    }

    /**
     * Returns the current speed multiplier.
     *
     * @return the speed multiplier as a double
     */
    public double getSpeedMultiplier() {
        return this.speedMultiplier;
    }

    /* Returns the scene width. */
    private int getSceneWidth() {
        return this.sceneWidth;
    }

    /**
     * Returns a String representation of this TaskSpawner.
     *
     * @return description of this TaskSpawner as a String
     */
    @Override
    public String toString() {
        final StringBuilder taskSpawnerDescription;
        taskSpawnerDescription = new StringBuilder("TaskSpawner{");
        taskSpawnerDescription.append("spawnInterval=").append(getSpawnInterval());
        taskSpawnerDescription.append(", timer=").append(getTimer());
        taskSpawnerDescription.append(", sceneWidth=").append(getSceneWidth());
        taskSpawnerDescription.append(", speedMultiplier=").append(getSpeedMultiplier());
        taskSpawnerDescription.append('}');
        return taskSpawnerDescription.toString();
    }

    /**
     * Returns true if the argument is equal to this TaskSpawner, else false.
     *
     * @param object the reference object with which to compare.
     * @return true if this object is equal to the argument, else false
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        TaskSpawner taskSpawner = (TaskSpawner) object;

        if (Double.compare(getSpawnInterval(), taskSpawner.getSpawnInterval()) != 0) {
            return false;
        }
        if (Double.compare(getTimer(), taskSpawner.getTimer()) != 0) {
            return false;
        }
        if (Double.compare(getSpeedMultiplier(), taskSpawner.getSpeedMultiplier()) != 0) {
            return false;
        }
        return getSceneWidth() == taskSpawner.getSceneWidth();
    }

    /**
     * Returns a hashCode for this TaskSpawner.
     *
     * @return hashCode as an int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getSpawnInterval(), getTimer(), getSceneWidth(), getSpeedMultiplier());
    }
}
