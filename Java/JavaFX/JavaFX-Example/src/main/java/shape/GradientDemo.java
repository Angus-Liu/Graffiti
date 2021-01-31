package shape;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Gradient Demo
 */
public class GradientDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();

        LinearGradient gradient1 = new LinearGradient(
                0, 1,
                1, 0,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0.4, Color.WHITE),
                new Stop(0.6, Color.BLACK)
        );

        Rectangle r1 = new Rectangle(50, 25, 100, 100);
        r1.setFill(gradient1);
        r1.setStroke(Color.BLACK);
        r1.setStrokeWidth(3);

        group.getChildren().add(r1);

        Scene scene = new Scene(group, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

