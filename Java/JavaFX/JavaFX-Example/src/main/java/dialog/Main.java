package dialog;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Button btn = new Button("CLICK IT");
        btn.setOnAction(e -> {
            boolean res = ConfirmationBox.show("确认", "你打开了一个确认框", "好的", "关闭");
            System.out.println("res = " + res);
        });

        Pane pane = new AnchorPane(btn);
        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
