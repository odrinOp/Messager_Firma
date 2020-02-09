package sample.entities;

import javafx.scene.control.Alert;

public class MessageWindowList {
    public static void messageWindowList(String header,String body){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(null);
        alert.setTitle(header);
        alert.setContentText(body);

        alert.onCloseRequestProperty();
        alert.showAndWait();
    }
}
