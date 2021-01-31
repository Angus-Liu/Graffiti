package effect;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * EffectDemo
 */
public class EffectDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle r = new Rectangle(50, 50, 300, 300);
        r.setFill(Color.web("#157c9d"));
        r.setStroke(null);
        r.setArcHeight(10);
        r.setArcWidth(10);

        DropShadow shadow = new DropShadow();
        r.setEffect(shadow);

        Group g = new Group(r);

        Scene scene = new Scene(g, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
