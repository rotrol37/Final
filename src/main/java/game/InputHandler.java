package game;

import enums.Direction;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Reads keyboard input and stores the current control state for the game.
 *
 * @author Mittapap
 * @version 2026
 */
public class InputHandler {

    private final Set<KeyCode> heldKeys;
    private Direction currentDirection;
    private boolean shootPressed;
    private boolean switchColorPressed;
    private boolean restartPressed;
    private boolean typingMode;
    private String typedText;
    private boolean enterPressed;



    /**
     * Constructs an InputHandler with no input value.
     */
    public InputHandler() {
        this.heldKeys = new HashSet<>();
        this.currentDirection = Direction.NONE;
        this.shootPressed = false;
        this.switchColorPressed = false;
        this.restartPressed = false;
        this.typingMode = false;
        this.typedText = "";
        this.enterPressed = false;
    }

    /**
     * Updates the stored input state when a key is pressed.
     *
     * @param keyEvent the keyboard event as a KeyEvent
     */
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent != null) {
            KeyCode keyCode = keyEvent.getCode();
            this.heldKeys.add(keyCode);
            if (typingMode) {
                handleTyping(keyEvent);
                return;
            }
            if (keyCode == KeyCode.SPACE) {
                this.shootPressed = true;
            }
            if (keyCode == KeyCode.Z) {
                this.switchColorPressed = true;
            }
            if (keyCode == KeyCode.R) {
                this.restartPressed = true;
            }
            readInput();
        }

    }

    /**
     * Updates the stored input state when a key is released.
     *
     * @param keyEvent the keyboard event as a KeyEvent
     */
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent != null) {
            KeyCode keyCode = keyEvent.getCode();
            this.heldKeys.remove(keyCode);
            if (keyCode == KeyCode.SPACE) {
                this.shootPressed = false;
            }
            if (keyCode == KeyCode.Z) {
                this.switchColorPressed = false;
            }
            if (keyCode == KeyCode.R) {
                this.restartPressed = false;
            }
            readInput();
        }
    }

    /**
     * Reads the held keys and updates the current movement direction.
     */
    public void readInput() {
        boolean moveLeft = this.heldKeys.contains(KeyCode.LEFT);
        boolean moveRight = this.heldKeys.contains(KeyCode.RIGHT);
        if (moveLeft && !moveRight) {
            this.currentDirection = Direction.LEFT;
        } else if (moveRight && !moveLeft) {
            this.currentDirection = Direction.RIGHT;
        } else {
            this.currentDirection = Direction.NONE;
        }
    }

    /**
     * Returns the current movement direction.
     *
     * @return the current direction as a Direction
     */
    public Direction getDirection() {
        return this.currentDirection;
    }

    /**
     * Returns if the shoot input is pressed.
     *
     * @return true if shoot is pressed, else false
     */
    public boolean isShoot() {
        return this.shootPressed;
    }

    /**
     * Returns if the switch color input is pressed.
     *
     * @return true if switch color is pressed, else false
     */
    public boolean isSwitchColor() {
        return this.switchColorPressed;
    }

    /**
     * Returns if the restart input is pressed.
     *
     * @return true if R is pressed, false otherwise
     */
    public boolean isRestart() {
        return this.restartPressed;
    }

    /**
     * Clears one-shot action inputs after they are consumed.
     */
    public void clear() {
        this.shootPressed = false;
        this.switchColorPressed = false;
        this.restartPressed = false;
    }

    /**
     * Enables or disables typing mode.
     *
     * @param typingMode true to enable typing mode, else false
     */
    public void setTypingMode(final boolean typingMode) {
        this.typingMode = typingMode;
        if (!typingMode) {
            this.typedText = "";
            this.enterPressed = false;
        }
    }

    /**
     * Returns the text typed.
     *
     * @return the typed text as a String
     */
    public String getTypedText() {
        return this.typedText.toString();
    }

    /**
     * Check if ENTER was pressed in typing mode.
     *
     * @return true if ENTER is pressed, else false
     */
    public boolean isEnterPressed() {
        return this.enterPressed;
    }

    /* Handles keyboard input when in typing mode. */
    private void handleTyping(final KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        if (keyCode == KeyCode.BACK_SPACE && !typedText.isEmpty()) {
            typedText = typedText.substring(0, typedText.length() - 1);
        } else if (keyCode == KeyCode.ENTER) {
            this.enterPressed = true;
        } else if (keyEvent.getText() != null && !keyEvent.getText().isEmpty()) {
            typedText = typedText + keyEvent.getText();
        }
    }

    /**
     * Returns a String representation of this InputHandler.
     *
     * @return description of this InputHandler as a String
     */
    @Override
    public String toString() {
        final StringBuilder inputHandlerDescription;
        inputHandlerDescription = new StringBuilder("InputHandler{");
        inputHandlerDescription.append("currentDirection=").append(getDirection());
        inputHandlerDescription.append(", shootPressed=").append(isShoot());
        inputHandlerDescription.append(", switchColorPressed=").append(isSwitchColor());
        inputHandlerDescription.append('}');
        return inputHandlerDescription.toString();
    }

    /**
     * Returns true if the argument is equal to this InputHandler, else false.
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

        InputHandler inputHandler = (InputHandler) object;

        if (getDirection() != inputHandler.getDirection()) {
            return false;
        }
        if (isShoot() != inputHandler.isShoot()) {
            return false;
        }
        return isSwitchColor() == inputHandler.isSwitchColor();
    }

    /**
     * Returns a hashCode for this InputHandler.
     *
     * @return hashCode as an int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getDirection(), isShoot(), isSwitchColor());
    }
}
