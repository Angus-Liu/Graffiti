package fancy;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Welcome");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        // grid.setGridLinesVisible(true);

        Text sceneTitle = new Text("Welcome");
        sceneTitle.setId("welcome-text");
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label username = new Label("User Name:");
        grid.add(username, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pwd = new Label("Password:");
        grid.add(pwd, 0, 2);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        Text actionTarget = new Text();
        actionTarget.setId("action-target");
        grid.add(actionTarget, 1, 4);

        Button btn = new Button("Sign in");
        btn.setOnAction(e -> {
            actionTarget.setText("Sign in button pressed");
        });
        HBox hb = new HBox();
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.getChildren().add(btn);
        grid.add(hb, 1, 3);

        Scene scene = new Scene(grid, 300, 275);
        scene.getStylesheets().add (Login.class.getResource("Login.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
