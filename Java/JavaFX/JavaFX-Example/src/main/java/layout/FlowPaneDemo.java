package layout;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * FlowPane Demo
 */
public class FlowPaneDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane pane = new FlowPane(10, 10);
        pane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 100; i++) {
            Button btn = new Button("Button " + i);
            btn.setPrefWidth(80);
            btn.setPrefHeight(80);
            pane.getChildren().add(btn);
        }
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
