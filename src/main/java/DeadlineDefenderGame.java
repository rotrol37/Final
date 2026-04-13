import game.InputHandler;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import scene.GameScene;


public class DeadlineDefenderGame extends Application {

    private static final int SCENE_WIDTH = 600;
    private static final int SCENE_HEIGHT = 700;
    private GameScene gameScene;
    private InputHandler input;

    @Override
    public void start(final Stage primaryStage) {
        input = new InputHandler();
        gameScene = new GameScene(SCENE_WIDTH, SCENE_HEIGHT, input);
        Pane root = new Pane(gameScene);
        gameScene.widthProperty().bind(root.widthProperty());
        gameScene.heightProperty().bind(root.heightProperty());
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.BLACK);

        scene.setOnKeyPressed(e -> input.keyPressed(e));
        scene.setOnKeyReleased(e -> input.keyReleased(e));

        AnimationTimer timer = getAnimationTimer();
        timer.start();

        primaryStage.setTitle("Deadline Defender");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private AnimationTimer getAnimationTimer() {
        return new AnimationTimer() {
            @Override
            public void handle(final long now) {
                gameScene.update();
                gameScene.draw();
            }
        };
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
