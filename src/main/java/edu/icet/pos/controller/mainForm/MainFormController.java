package edu.icet.pos.controller.mainForm;

import edu.icet.pos.bo.BoFactory;
import edu.icet.pos.bo.SuperBo;
import edu.icet.pos.bo.custom.UserBo;
import edu.icet.pos.controller.login.LoginFormController;
import edu.icet.pos.dto.User;
import edu.icet.pos.utill.BoType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    public TabPane tblPaneMain;
    private final UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);
    public Label lblUserID;
    public Label lblUserName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String userID = LoginFormController.userID;
        User selectedUser = userBo.getSelectedUser(userID);
        lblUserID.setText(selectedUser.getId());
        lblUserName.setText(selectedUser.getUserName());
    }

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) {
        try {
            Tab tab = new Tab();
            tab.setText("Employee");
            tab.setContent(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/add-employee-form.fxml"))));

            tblPaneMain.getTabs().add(tab);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {

        try {
            Tab tab = new Tab();
            tab.setText("Order");
            tab.setContent(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/place-order-form.fxml"))));
            tblPaneMain.getTabs().add(tab);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddNewItemOnAction(ActionEvent actionEvent) {
        try {
            Tab tab = new Tab();
            tab.setText("New Item");
            tab.setContent(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/add_item_form.fxml"))));
            tblPaneMain.getTabs().add(tab);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddNewSupplierOnAction(ActionEvent actionEvent) {
        try {
            Tab tab = new Tab();
            tab.setText("Supplier");
            tab.setContent(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/supplier-management-form.fxml"))));
            tblPaneMain.getTabs().add(tab);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        try {
            Parent loadLoginForm = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/login-form.fxml")));
            Stage stage = new Stage();
            stage.setScene(new Scene(loadLoginForm));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddNewUserOnAction(ActionEvent actionEvent) {
        try {
            Parent loadLoginForm = FXMLLoader.load(getClass().getResource("/view/add-user-form.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loadLoginForm));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
