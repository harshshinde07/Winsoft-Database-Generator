package sample.utils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class AlertMaker {

    public static void showMaterialDialog(StackPane root, Node nodeToBeBlurred, List<JFXButton> controls, String header, String body) {
        BoxBlur blur = new BoxBlur(3, 3, 3);
        if (controls.isEmpty()) {
            controls.add(new JFXButton("Okay"));
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);

        controls.forEach(controlButton -> {
            controlButton.getStyleClass().add("dialog-button");
            controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialog.close();
            });
        });

        dialogLayout.setHeading(new Label(header));
        dialogLayout.setBody(new Label(body));
        dialogLayout.setActions(controls);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
            nodeToBeBlurred.setEffect(null);
        });
        nodeToBeBlurred.setEffect(blur);
    }

    public static void showSimpleAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        styleAlert(alert);
        alert.showAndWait();
    }

    public static void showErrorMessage(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(content);
        styleAlert(alert);
        alert.showAndWait();
    }

    private static void styleAlert(Alert alert) {
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        Util.setStageIcon(stage);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(AlertMaker.class.getResource("/sample/utils/dark-theme.css").toExternalForm());
        dialogPane.getStyleClass().add("custom-alert");
    }
}
