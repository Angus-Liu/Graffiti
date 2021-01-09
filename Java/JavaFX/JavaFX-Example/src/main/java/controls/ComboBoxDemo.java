package controls;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * ComboBox Demo
 */
public class ComboBoxDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setEditable(true);
        comboBox.getItems().addAll("Tom", "Angus", "Any");

        BorderPane pane = new BorderPane(comboBox);
        pane.setPadding(new Insets(20));

        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
