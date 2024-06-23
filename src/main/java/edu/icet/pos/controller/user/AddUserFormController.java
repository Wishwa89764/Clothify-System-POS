package edu.icet.pos.controller.user;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.icet.pos.bo.BoFactory;
import edu.icet.pos.bo.custom.EmployeeBo;
import edu.icet.pos.bo.custom.ItemBo;
import edu.icet.pos.bo.custom.UserBo;
import edu.icet.pos.dto.Employee;
import edu.icet.pos.dto.User;
import edu.icet.pos.utill.BoType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class AddUserFormController implements Initializable {

    @FXML
    private JFXComboBox<?> cmbEmployeeID;

    @FXML
    private ChoiceBox<?> cmbRole;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblUserID;

    @FXML
    private JFXPasswordField txtRepeatPassword;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtUserPassword;

    private final UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);
    private final EmployeeBo employeeBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList role = FXCollections.observableArrayList();
        role.add(new String("Admin"));
        role.add(new String("User"));
        cmbRole.setItems(role);

        loadNewUserID();
        loadEmployeeID();
    }

    @FXML
    void btnAddNewUserOnAction(ActionEvent event) {
        boolean b = userBo.saveNewUser(
                new User(
                        Long.parseLong(lblUserID.getText()),
                        txtUserName.getText(),
                        txtUserPassword.getText(),
                        cmbEmployeeID.getValue().toString(),
                        cmbRole.getValue().toString()
                )
        );

        if (b) {
            new Alert(Alert.AlertType.CONFIRMATION, "New User Added !").show();
            clearText();
            loadNewUserID();
        } else {
            new Alert(Alert.AlertType.ERROR, "User Not Added ! Please ReTry..").show();
        }

    }

    private void loadNewUserID() {

        lblUserID.setText(userBo.getNewUserID());
    }

    private void loadEmployeeID(){
        ObservableList allEmployeeID = employeeBo.getAllEmployeeID();
        cmbEmployeeID.setItems(allEmployeeID);

    }

    private void clearText() {
        cmbRole.setValue(null);
        cmbEmployeeID.setValue(null);
        lblEmployeeName.setText("");
        lblUserID.setText("");
        txtUserName.setText("");
        txtUserPassword.setText("");
        txtRepeatPassword.setText("");
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void cmbEmployeeIDOnAction(ActionEvent event) {
        Employee employee = employeeBo.getSelectedEmployee(cmbEmployeeID.getValue().toString());
        lblEmployeeName.setText(employee.getFirstName()+" "+employee.getLastName());

    }

    @FXML
    void cmbRoleOnAction(MouseEvent event) {

    }



}
