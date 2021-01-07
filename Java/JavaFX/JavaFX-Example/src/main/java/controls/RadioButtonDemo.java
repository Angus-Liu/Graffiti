package controls;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * RadioButton Demo
 */
public class RadioButtonDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ToggleGroup group = new ToggleGroup();

        Pane pane = new HBox(10);
        pane.setPadding(new Insets(10));
        for (int i = 0; i < 10; i++) {
            RadioButton rb = new RadioButton("Btn " + i);
            rb.setToggleGroup(group);
            rb.setTooltip(new Tooltip("I'm Btn " + i));
            pane.getChildren().add(rb);
        }

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
