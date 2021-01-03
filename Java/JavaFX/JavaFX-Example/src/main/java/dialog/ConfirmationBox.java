package dialog;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 确认框
 */
public class ConfirmationBox {

    private static boolean btnYesClicked;

    public static boolean show(String title, String message, String btnYesText, String btnNoText) {
        btnYesClicked = false;

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinWidth(250);

        Button btnYes = new Button(btnYesText);
        btnYes.setOnAction(e -> {
            btnYesClicked = true;
            stage.close();
        });
        Button btnNo = new Button(btnNoText);
        btnNo.setOnAction(e -> {
            btnYesClicked = false;
            stage.close();
        });
        HBox btnPane = new HBox(20);
        btnPane.getChildren().addAll(btnYes, btnNo);

        Label label = new Label(message);

        VBox pane = new VBox(20);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(label, btnPane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
        return btnYesClicked;
    }
}
