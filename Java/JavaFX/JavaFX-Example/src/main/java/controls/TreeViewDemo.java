package controls;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * TreeView Demo
 */
public class TreeViewDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        TreeView<String> view = new TreeView<>();

        TreeItem<String> root = new TreeItem<>("root");
        TreeItem<String> a = new TreeItem<>("a");
        TreeItem<String> b = new TreeItem<>("b");
        root.getChildren().addAll(a, b);

        view.setRoot(root);

        Pane pane = new BorderPane(view);
        pane.setPadding(new Insets(20));

        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
