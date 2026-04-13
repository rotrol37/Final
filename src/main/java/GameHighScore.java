import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

/**
 * Saves and loads the high score from a file.
 *
 * @author Mittapap
 * @version 2026
 */
public class GameHighScore {

    private static final String FILE_PATH = "highscore.txt";
    private static final String SEPARATOR = ",";
    private static final int NAME_INDEX = 0;
    private static final int SCORE_INDEX = 1;
    private static final String DEFAULT_NAME = "Unknown";
    private static final int DEFAULT_SCORE = 100;

    private String highScoreName;
    private int highScore;

    /**
     * Constructs a GameHighScore with the high score from file.
     */
    public GameHighScore() {
        this.highScoreName = DEFAULT_NAME;
        this.highScore = DEFAULT_SCORE;
        load();
    }

    /**
     * Returns the high score name.
     *
     * @return the name as a String
     */
    public String getHighScoreName() {
        return this.highScoreName;
    }

    /**
     * Returns the high score.
     *
     * @return the high score as an int
     */
    public int getHighScore() {
        return this.highScore;
    }

    /**
     * Returns true if the given score beats the current high score.
     *
     * @param score the score to check as an int
     * @return true if the score is higher, else false
     */
    public boolean isNewHighScore(final int score) {
        return score > this.highScore;
    }

    /**
     * Saves the new high score and name to the file.
     *
     * @param name the player name as a String
     * @param score the score as an int
     */
    public void save(final String name, final int score) {
        Path path = Path.of(FILE_PATH);
        this.highScoreName = name;
        this.highScore = score;
        try {
            Files.writeString(path, name + SEPARATOR + score);
        } catch (final IOException e) {
            System.out.println("Cannot save high score.");
        }
    }

    /* Loads the high score from the file. */
    private void load() {
        Path path = Path.of(FILE_PATH);
        try (Scanner fileScan = new Scanner(path)) {
            while (fileScan.hasNext()) {
                String line = fileScan.nextLine();
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split(SEPARATOR);
                this.highScoreName = parts[NAME_INDEX];
                this.highScore = Integer.parseInt(parts[SCORE_INDEX]);
            }
        } catch (final IOException e) {
            System.out.println("highscore.txt is not found.");
        }
    }

    /**
     * Returns a String representation of this HighScoreManager.
     *
     * @return description of this HighScoreManager as a String
     */
    @Override
    public String toString() {
        final StringBuilder highScoreDescription;
        highScoreDescription = new StringBuilder("HighScoreManager{");
        highScoreDescription.append("highScoreName='").append(getHighScoreName()).append('\'');
        highScoreDescription.append(", highScore=").append(getHighScore());
        highScoreDescription.append('}');
        return highScoreDescription.toString();
    }

    /**
     * Returns true if the argument is equal to this HighScoreManager, else false.
     *
     * @param object the reference object with which to compare
     * @return true if equal, else false
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        GameHighScore gameHighScore = (GameHighScore) object;

        if (getHighScore() != gameHighScore.getHighScore()) {
            return false;
        }
        return getHighScoreName().equals(gameHighScore.getHighScoreName());
    }

    /**
     * Returns a hashCode for this HighScoreManager.
     *
     * @return hashCode as an int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getHighScoreName(), getHighScore());
    }
}