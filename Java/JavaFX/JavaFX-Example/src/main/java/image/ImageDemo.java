package image;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ImageDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Image img1 = new Image("file:/Users/angus/Documents/Image/005T5Xcngy1fwmyxnrpkzj30zk0qotif.jpg");
        ImageView iv1 = new ImageView(img1);
        iv1.setFitHeight(200);
        iv1.setFitWidth(200);
        iv1.setPreserveRatio(true);

        Image img2 = new Image("https://img1.doubanio.com/img/musician/large/28588.jpg");
        ImageView iv2 = new ImageView(img2);
        iv2.setFitHeight(200);
        iv2.setFitWidth(200);
        iv2.setPreserveRatio(true);

        HBox pane = new HBox(100, iv1, iv2);
        Scene scene = new Scene(pane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
