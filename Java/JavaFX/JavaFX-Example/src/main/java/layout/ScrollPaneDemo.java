package layout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * ScrollPane Demo
 */
public class ScrollPaneDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        TilePane pane = new TilePane(10, 10);
        for (int i = 0; i < 100; i++) {
            Rectangle r = new Rectangle(100, 100);
            r.setFill(Color.BLANCHEDALMOND);
            pane.getChildren().add(r);
        }

        ScrollPane scrollPane = new ScrollPane(pane);
        scrollPane.setPannable(true);
        scrollPane.setMaxWidth(250);
        scrollPane.setMinWidth(250);
        scrollPane.setPrefWidth(250);
        scrollPane.setPrefHeight(400);

        Scene scene = new Scene(scrollPane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
