package sample.entities;

import javafx.scene.control.Alert;

public class ErrorMessageAlert {

    public static void showErrorMessage(String header, String body){
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.initOwner(null);
        message.setTitle(header);
        message.setContentText(body);

        message.showAndWait();
    }
}
