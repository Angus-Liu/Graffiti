package animation;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BouncingBall extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        RadialGradient g = new RadialGradient(
                0, 0,
                0.35, 0.35,
                0.5,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.WHEAT),
                new Stop(1.0, Color.RED)
        );

        Circle ball = new Circle(0, 0, 20);
        ball.setFill(g);

        Group root = new Group(ball);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        TranslateTransition t = new TranslateTransition(Duration.millis(1500), ball);
        t.setFromX(ball.getRadius());
        t.setToX(scene.getWidth() - ball.getRadius());
        t.setCycleCount(Transition.INDEFINITE);
        t.setAutoReverse(true);
        t.play();

        TranslateTransition t1 = new TranslateTransition(Duration.millis(750), ball);
        t1.setFromY(scene.getHeight() / 2);
        t1.setToY(scene.getHeight() / 2 + 100);
        t1.setCycleCount(Transition.INDEFINITE);
        t1.setAutoReverse(true);
        t1.play();
    }
}
