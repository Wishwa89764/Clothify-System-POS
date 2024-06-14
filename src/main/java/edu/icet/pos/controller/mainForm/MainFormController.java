package edu.icet.pos.controller.mainForm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainFormController {

    @FXML
    private AnchorPane anchorPaneMain;

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) {
        try {
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("/view/add-employee-form.fxml"));
            anchorPaneMain.getChildren().add(load.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
