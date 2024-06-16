package edu.icet.pos.controller.user;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.icet.pos.controller.employee.EmployeeController;
import edu.icet.pos.controller.employee.EmployeeService;
import edu.icet.pos.model.Employee;
import edu.icet.pos.model.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;


public class AddUserFormController implements Initializable {
    UserService userService=new UserController();

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
        boolean b = userService.addNewUser(
                new User(
                        Long.parseLong(lblUserID.getText()),
                        txtUserName.getText(),
                        txtUserPassword.getText(),
                        cmbEmployeeID.getValue().toString(),
                        cmbRole.getValue().toString()
                )
        );

        if (b) {
            new Alert(Alert.AlertType.CONFIRMATION, "New Employee Added !").show();
            clearText();
            loadNewUserID();
        } else {
            new Alert(Alert.AlertType.ERROR, "Employee Not Added ! Please ReTry..").show();
        }

    }

    private void loadNewUserID() {
        lblUserID.setText(userService.generateUserID());
    }

    private void loadEmployeeID(){
        EmployeeService employeeService = EmployeeController.getInstance();
        ObservableList<Employee> allEmployees = employeeService.getAllEmployees();
        ObservableList employeeID = FXCollections.observableArrayList();


        for(Employee employees : allEmployees){
            employeeID.add(
            new String (employees.getEmployeeId())
            );
        }


        cmbEmployeeID.setItems(employeeID);

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
        EmployeeService employeeService=EmployeeController.getInstance();
        Employee employee = employeeService.searchEmployee(cmbEmployeeID.getValue().toString());
        lblEmployeeName.setText(employee.getFirstName()+" "+employee.getLastName());

    }

    @FXML
    void cmbRoleOnAction(MouseEvent event) {

    }



}
