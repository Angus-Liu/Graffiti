package space;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * 通过 Growing Nodes 添加空间
 */
public class GrowingNodes extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btn1 = new Button("One");
        Button btn2 = new Button("Two");
        Button btn3 = new Button("Three");

        Region spacer = new Region();

        HBox.setMargin(btn1, new Insets(10));
        HBox.setMargin(btn2, new Insets(10));
        HBox.setMargin(btn3, new Insets(10));

        HBox.setHgrow(spacer, Priority.SOMETIMES);

        HBox hBox = new HBox(10, btn1, btn2, spacer, btn3);

        Scene scene = new Scene(hBox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
