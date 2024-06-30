package edu.icet.pos.controller.user;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.icet.pos.bo.BoFactory;
import edu.icet.pos.bo.custom.EmployeeBo;
import edu.icet.pos.bo.custom.ItemBo;
import edu.icet.pos.bo.custom.SendOtpBo;
import edu.icet.pos.bo.custom.UserBo;
import edu.icet.pos.dto.Employee;
import edu.icet.pos.dto.User;
import edu.icet.pos.utill.BoType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class AddUserFormController implements Initializable {

    public JFXButton btnCancel;
    public ComboBox cmbRole;
    public JFXButton btnAddNewUser;
    public JFXTextField txtUserID;
    @FXML
    private JFXComboBox<?> cmbEmployeeID;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private JFXPasswordField txtRepeatPassword;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtUserPassword;

    private final UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);
    private final EmployeeBo employeeBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);

    private final SendOtpBo sendOtpBo = BoFactory.getInstance().getBo(BoType.OTP);
    private static User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> role = FXCollections.observableArrayList();
        role.add(new String("Admin"));
        role.add(new String("User"));
        cmbRole.setItems(role);

        if(userBo.getRecordsCount()==0){
            cmbRole.setPromptText("Owner");
            cmbRole.setDisable(true);
            cmbEmployeeID.setPromptText("Owner");
            cmbEmployeeID.setVisible(false);
            btnCancel.setVisible(false);
            txtUserName.requestFocus();
        }
        loadEmployeeID();

    }

    @FXML
    void btnAddNewUserOnAction(ActionEvent event) {

        if(userBo.getRecordsCount()!=0){
            boolean isValidEmail= sendOtpBo.sendOTPCode(txtUserID.getText());
            if(isValidEmail){
                createNewUser();
                loadEmailVerificationForm();
                ((Stage)btnAddNewUser.getScene().getWindow()).close();
             }else{
                new Alert(Alert.AlertType.ERROR,"Invalid Email Address!").show();
             }
        }
    }
    public void addNewUser(boolean result){
        if(result){
            boolean isSaved = userBo.saveNewUser(user);
            user=null;
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "New User Added !").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "User Not Added ! Please ReTry..").show();
            }

        }

    }
    private void loadEmailVerificationForm(){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader
                    .load(getClass()
                            .getResource("/view/email-verification-form.fxml")
                    )
            ));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void createNewUser(){
        String empID;
        String  userRole;
        if(userBo.getRecordsCount()==0){
            empID="Owner";
            userRole="Owner";
        }else {
                empID = cmbEmployeeID.getPromptText();
                userRole = cmbRole.getPromptText();
        }
        user=new User(
                txtUserID.getText(),
                txtUserName.getText(),
                txtUserPassword.getText(),
                empID,
                userRole
        );
    }

    private void loadLoginForm() {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader
                    .load(Objects.requireNonNull(getClass()
                            .getResource("/view/login-form.fxml"))
                    )
            ));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadEmployeeID(){
        ObservableList allEmployeeID = employeeBo.getAllEmployeeID();
        cmbEmployeeID.setItems(allEmployeeID);

    }

    private void clearText() {
        cmbRole.setValue(null);
        cmbEmployeeID.setValue(null);
        lblEmployeeName.setText("");
        txtUserID.setText("");
        txtUserName.setText("");
        txtUserPassword.setText("");
        txtRepeatPassword.setText("");
        txtUserID.requestFocus();
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        ((Stage)btnCancel.getScene().getWindow()).close();
    }

    @FXML
    void cmbEmployeeIDOnAction(ActionEvent event) {
        Employee employee = employeeBo.getSelectedEmployee(cmbEmployeeID.getValue().toString());
        lblEmployeeName.setText(employee.getFirstName()+" "+employee.getLastName());

    }


    public void cmbRoleOnAction(ActionEvent actionEvent) {
    }
}
