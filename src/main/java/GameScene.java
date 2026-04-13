import enums.CollisionResult;
import enums.GameState;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import objects.Ball;
import objects.Launcher;
import objects.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents the main game canvas and game loop.
 *
 * @author Mittapap
 * @version 2026
 */
public class GameScene extends Canvas {

    private static final int SCORE_PER_HIT = 10;
    private static final int DIFFICULTY_THRESHOLD_1 = 250;
    private static final int DIFFICULTY_THRESHOLD_2 = 500;
    private static final int DIFFICULTY_THRESHOLD_3 = 800;
    private static final double BOTTOM_LINE_OFFSET = 55.0;
    private static final double SPEED_UP_DURATION = 120;
    private static final double HIGHLIGHT_DURATION = 90;

    private final double sceneWidth;
    private final double sceneHeight;
    private final Launcher launcher;
    private final GameDrawer drawer;
    private final MenuDrawer menuDrawer;
    private int hoveredButton;
    private GameState state;
    private final InputHandler input;
    private final List<Ball> balls;
    private boolean shootPressed;
    private final List<Task> tasks;
    private final TaskSpawner spawner;
    private final CollisionDetector collision;
    private final ScoreBoard scoreBoard;
    private boolean switchPressed;
    private boolean isMatchColor;
    private double highlightedScoreFrame;
    private double speedUpFrame;
    private boolean difficulty1Reached;
    private boolean difficulty2Reached;
    private boolean difficulty3Reached;
    private final GameHighScore gameHighScore;
    private int peakScore;

    /**
     * Construct a GameScene with the initial state.
     *
     * @param width  the scene width as an int
     * @param height the scene height as an int
     * @param input  the input handler as an InputHandler
     */
    public GameScene(final int width, final int height, final InputHandler input) {
        super(width, height);
        this.sceneWidth = width;
        this.sceneHeight = height;
        this.input = input;
        this.drawer = new GameDrawer();
        this.state = GameState.MENU;
        this.menuDrawer = new MenuDrawer();
        this.hoveredButton = -1;
        this.launcher = new Launcher(width / 2.0, height - BOTTOM_LINE_OFFSET);
        this.balls = new ArrayList<>();
        this.shootPressed = false;
        this.tasks = new ArrayList<>();
        this.spawner = new TaskSpawner(width);
        this.collision = new CollisionDetector();
        this.scoreBoard = new ScoreBoard();
        this.switchPressed = false;
        this.highlightedScoreFrame = 0;
        this.isMatchColor = false;
        this.speedUpFrame = 0;
        this.difficulty1Reached = false;
        this.difficulty2Reached = false;
        this.difficulty3Reached = false;
        this.gameHighScore = new GameHighScore();
        this.peakScore = 100;
        registerMouseHandlers();
    }

    private void resetGame() {
        balls.clear();
        tasks.clear();
        scoreBoard.reset();
        spawner.reset();
        difficulty1Reached = false;
        difficulty2Reached = false;
        difficulty3Reached = false;
        speedUpFrame = 0;
        highlightedScoreFrame = 0;
        peakScore = 0;
        state = GameState.RUNNING;
    }

    /**
     * Updates the game state for each loop.
     */
    public void update() {
        if (state == GameState.RUNNING) {
            launcher.move(input.getDirection(), sceneWidth);
            handleShoot();
            handleSwitchColor();
            spawnNewTask();
            updateBalls();
            updateTasks();
            checkDifficulty();
        }
        if (state == GameState.HIGH_SCORE) {
            handleNameInput();
        }
        if (state == GameState.GAME_OVER && input.isRestart()) {
            resetGame();
        }
    }

    /**
     * Draws the game scene.
     */
    public void draw() {
        final GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, sceneWidth, sceneHeight);
        if (state == GameState.MENU) {
            menuDrawer.drawMenu(gc, sceneWidth, sceneHeight, hoveredButton);
            return;
        }
        drawer.draw(gc, sceneWidth, sceneHeight);
        drawer.drawBottomLine(gc, sceneWidth, getBottomLine(),
                highlightedScoreFrame > 0, isMatchColor);
        drawer.drawObjects(gc, tasks, balls);
        launcher.render(gc);
        drawer.drawHighlightedScore(gc, scoreBoard.getScore(),
                gameHighScore.getDisplayString(),
                highlightedScoreFrame > 0, isMatchColor);
        if (highlightedScoreFrame > 0) {
            highlightedScoreFrame--;
        }
        if (speedUpFrame > 0) {
            drawer.drawSpeedUp(gc, sceneWidth, sceneHeight);
            speedUpFrame--;
        }
        if (state == GameState.GAME_OVER) {
            drawer.drawGameOver(gc, sceneWidth, sceneHeight);
        }
        if (state == GameState.HIGH_SCORE) {
            drawer.drawNameInput(gc, sceneWidth, sceneHeight,
                    peakScore, input.getTypedText());
        }
    }

    /* Returns the y-coordinate of the bottom penalty line. */
    private double getBottomLine() {
        return sceneHeight - BOTTOM_LINE_OFFSET;
    }

    /* Registers mouse event handlers for the menu. */
    private void registerMouseHandlers() {
        setOnMouseMoved(e -> {
            if (state != GameState.MENU) {
                return;
            }
            double mouseX = e.getX();
            double mouseY = e.getY();
            double[][] yBounds = menuDrawer.buttonBounds(sceneHeight);
            double[][] xBounds = menuDrawer.buttonXBounds(sceneWidth);
            hoveredButton = -1;
            for (int i = 0; i < yBounds.length; i++) {
                if (mouseX >= xBounds[i][0] && mouseX <= xBounds[i][1] &&
                        mouseY >= yBounds[i][0] && mouseY <= yBounds[i][1]) {
                    hoveredButton = i;
                }
            }
        });

        setOnMouseClicked(e -> {
            if (state != GameState.MENU) {
                return;
            }
            double mouseX = e.getX();
            double mouseY = e.getY();
            double[][] yBounds = menuDrawer.buttonBounds(sceneHeight);
            double[][] xBounds = menuDrawer.buttonXBounds(sceneWidth);
            if (mouseX >= xBounds[0][0] && mouseX <= xBounds[0][1] &&
                    mouseY >= yBounds[0][0] && mouseY <= yBounds[0][1]) {
                state = GameState.RUNNING;
            }
            if (mouseX >= xBounds[1][0] && mouseX <= xBounds[1][1] &&
                    mouseY >= yBounds[1][0] && mouseY <= yBounds[1][1]) {
                javafx.application.Platform.exit();
            }
        });
    }

    private boolean checkBallCollision(final Ball ball) {
        Iterator<Task> taskIt = tasks.iterator();
        while (taskIt.hasNext()) {
            Task task = taskIt.next();
            if (collision.checkCollision(ball, task)) {
                CollisionResult result = collision.resolve(ball, task);
                if (result == CollisionResult.CLEAR) {
                    scoreBoard.addScore(SCORE_PER_HIT);
                    if (scoreBoard.getScore() > peakScore) {
                        peakScore = scoreBoard.getScore();
                    }
                    isMatchColor = true;
                    highlightedScoreFrame = HIGHLIGHT_DURATION;
                } else {
                    scoreBoard.applyPenalty();
                    isMatchColor = false;
                    highlightedScoreFrame = HIGHLIGHT_DURATION;
                    if (scoreBoard.isGameOver()) {
                        state = GameState.GAME_OVER;
                        checkHighScore();
                    }
                }
                taskIt.remove();
                return true;
            }
        }
        return false;
    }

    /* Switches the launcher color when Z is pressed. */
    private void handleSwitchColor() {
        if (input.isSwitchColor() && !switchPressed) {
            launcher.switchColor();
            switchPressed = true;
        }
        if (!input.isSwitchColor()) {
            switchPressed = false;
        }
    }

    /* Shoots a ball if shoot input is pressed. */
    private void handleShoot() {
        if (input.isShoot() && !shootPressed) {
            balls.add(launcher.shoot());
            shootPressed = true;
        }
        if (!input.isShoot()) {
            shootPressed = false;
        }
    }

    /* Spawns a new task. */
    private void spawnNewTask() {
        Task spawnedTask = spawner.update();
        if (spawnedTask != null) {
            tasks.add(spawnedTask);
        }
    }

    /* Updates all balls and checks for collision with tasks. */
    private void updateBalls() {
        Iterator<Ball> ballIt = balls.iterator();
        while (ballIt.hasNext()) {
            Ball ball = ballIt.next();
            ball.update();
            if (ball.isOffScreen()) {
                ballIt.remove();
                continue;
            }
            if (checkBallCollision(ball)) {
                ballIt.remove();
            }
        }
    }

    /* Updates all tasks and checks if the task have reached the bottom. */
    private void updateTasks() {
        Iterator<Task> taskIt = tasks.iterator();
        while (taskIt.hasNext()) {
            Task task = taskIt.next();
            task.update();
            if (task.isOffScreen(getBottomLine())) {
                taskIt.remove();
                if (state != GameState.GAME_OVER) {
                    scoreBoard.applyPenalty();
                    isMatchColor = false;
                    highlightedScoreFrame = HIGHLIGHT_DURATION;
                    if (scoreBoard.isGameOver()) {
                        state = GameState.GAME_OVER;
                        checkHighScore();
                    }
                }
            }
        }
    }

    /* Checks if the score has reached a difficulty threshold. */
    private void checkDifficulty() {
        int score = scoreBoard.getScore();
        if (!difficulty1Reached && score >= DIFFICULTY_THRESHOLD_1) {
            spawner.increaseDifficulty();
            speedUpFrame = SPEED_UP_DURATION;
            difficulty1Reached = true;
        }
        if (!difficulty2Reached && score >= DIFFICULTY_THRESHOLD_2) {
            spawner.increaseDifficulty();
            speedUpFrame = SPEED_UP_DURATION;
            difficulty2Reached = true;
        }
        if (!difficulty3Reached && score >= DIFFICULTY_THRESHOLD_3) {
            spawner.increaseDifficulty();
            speedUpFrame = SPEED_UP_DURATION;
            difficulty3Reached = true;
        }
    }

    /* Shows a dialog and saves if current score beats high score. */
    private void checkHighScore() {
        if (gameHighScore.isNewHighScore(peakScore)) {
            state = GameState.HIGH_SCORE;
            input.setTypingMode(true);
        }
    }

    /* Handles name input when entering a new high score. */
    private void handleNameInput() {
        if (input.isEnterPressed()) {
            String name = input.getTypedText();
            if (name != null && !name.isEmpty()) {
                gameHighScore.save(name, peakScore);
            } else {
                gameHighScore.save("Unknown", peakScore);
            }
            input.setTypingMode(false);
            state = GameState.GAME_OVER;
        }
    }
}