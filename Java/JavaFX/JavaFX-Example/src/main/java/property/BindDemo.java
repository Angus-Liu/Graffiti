package property;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Bind Demo
 */
public class BindDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label lbl = new Label();
        TextField field = new TextField();
        lbl.textProperty().bind(field.textProperty());
        VBox page = new VBox(10, lbl, field);
        Scene scene = new Scene(page, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
