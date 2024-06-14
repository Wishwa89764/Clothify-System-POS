package edu.icet.pos.controller.item;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemManagementFormController implements Initializable {

    @FXML
    private Pane paneChild;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("/view/add-employee-form.fxml"));
            paneChild.getChildren().add(load.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
