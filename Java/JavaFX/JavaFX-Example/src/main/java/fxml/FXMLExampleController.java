package fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;

public class FXMLExampleController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text actionTarget;

    @FXML
    private void handleSubmitButtonAction(ActionEvent actionEvent) {
        actionTarget.setText("Sign in: " + passwordField.getText());
    }
}
