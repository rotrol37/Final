package game;

import java.util.Objects;

/**
 * Manages the score for the game.
 *
 * @author Mittapap
 * @version 2026
 */
public class ScoreBoard {

    private static final int PENALTY_AMOUNT = 15;
    private static final int INITIAL_SCORE = 100;

    private int score;

    /**
     * Constructs a ScoreBoard with the initial score.
     */
    public ScoreBoard() {
        this.score = INITIAL_SCORE;
    }

    /**
     * Adds points to the current score.
     *
     * @param points the points to add as an int
     */
    public void addScore(final int points) {
        this.score += points;
    }

    /**
     * Applies the penalty amount to the current score.
     */
    public void applyPenalty() {
        this.score -= PENALTY_AMOUNT;
    }

    /**
     * Returns the current score.
     *
     * @return the current score as an int
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Returns true if the score is zero or below, else false.
     *
     * @return true if the game is over, else false
     */
    public boolean isGameOver() {
        return getScore() <= 0;
    }

    /**
     * Resets the score to the initial value.
     */
    public void reset() {
        this.score = INITIAL_SCORE;
    }

    /**
     * Returns a String representation of this ScoreBoard.
     *
     * @return description of this ScoreBoard as a String
     */
    @Override
    public String toString() {
        final StringBuilder scoreBoardDescription;
        scoreBoardDescription = new StringBuilder("ScoreBoard{");
        scoreBoardDescription.append("score=").append(getScore());
        scoreBoardDescription.append('}');
        return scoreBoardDescription.toString();
    }

    /**
     * Returns true if the argument is equal to this ScoreBoard, else false.
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

        ScoreBoard scoreBoard = (ScoreBoard) object;

        return getScore() == scoreBoard.getScore();
    }

    /**
     * Returns a hashCode for this ScoreBoard.
     *
     * @return hashCode as an int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getScore());
    }
}
