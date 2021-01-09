package controls;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * ListView Demo
 */
public class ListViewDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ListView<String> view = new ListView<>();
        view.getItems().addAll("red", "blue", "black", "yellow");
        view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Pane pane = new BorderPane(view);
        pane.setPadding(new Insets(20));

        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
