package css;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CSSDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Button btn = new Button("test");
        btn.getStyleClass().add("button-large");
        BorderPane pane = new BorderPane(btn);

        Scene scene = new Scene(pane, 100, 100);
        scene.getStylesheets().add("css/demo.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
