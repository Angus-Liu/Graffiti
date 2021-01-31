package shape;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Transparent Demo
 */
public class TransparentDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        ObservableList<Node> groupNodes = group.getChildren();

        Rectangle r1 = new Rectangle(50, 25, 100, 140);
        r1.setFill(Color.rgb(255, 200, 200, 0.25));
        r1.setStroke(Color.BLACK);
        r1.setStrokeWidth(3);

        groupNodes.addAll(r1);

        Scene scene = new Scene(group, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
