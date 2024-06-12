package edu.icet.pos.controller.supplier;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.pos.controller.employee.EmployeeController;
import edu.icet.pos.model.Employee;
import edu.icet.pos.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class SupplierManagementFormController implements Initializable {

    @FXML
    private JFXButton btnAddNewEmp;

    @FXML
    private JFXButton btnDeleteEmp;

    @FXML
    private JFXButton btnUpdateEmp;

    @FXML
    private JFXComboBox<?> cmbSex;

    @FXML
    private JFXComboBox<?> cmbSupplierID;

    @FXML
    private DatePicker dateOfRecruit;

    @FXML
    private Label lblCurrentDate;

    @FXML
    private Label lblCurrentTime;

    @FXML
    private Label lblUserID;

    @FXML
    private JFXTextField txtAccountNo;

    @FXML
    private JFXTextField txtBankHolderName;

    @FXML
    private JFXTextField txtBankName;

    @FXML
    private JFXTextField txtBranch;

    @FXML
    private JFXTextField txtComAddress1;

    @FXML
    private JFXTextField txtComAddress2;

    @FXML
    private JFXTextField txtComAddress3;

    @FXML
    private JFXTextField txtCompanyName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtMobileNo;

    @FXML
    private JFXTextField txtNicNo;

    @FXML
    private JFXTextField txtRegNo;

    @FXML
    private JFXTextField txtSupplierAddress1;

    @FXML
    private JFXTextField txtSupplierAddress2;

    @FXML
    private JFXTextField txtSupplierAddress3;

    @FXML
    private JFXTextField txtTelNo;

    @FXML
    void btnAddNewEmpOnAction(ActionEvent event) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date recuritmentDate = null;
        try {
            recuritmentDate = format.parse(dateOfRecruit.getValue().toString());
            Supplier supplier = new Supplier(
                    cmbSupplierID.getPromptText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    cmbSex.getValue().toString(),
                    txtNicNo.getText(),
                    txtMobileNo.getText(),
                    txtSupplierAddress1.getText(),
                    txtSupplierAddress2.getText(),
                    txtSupplierAddress3.getText(),
                    txtCompanyName.getText(),
                    txtRegNo.getText(),
                    txtTelNo.getText(),
                    txtEmail.getText(),
                    txtSupplierAddress1.getText(),
                    txtSupplierAddress2.getText(),
                    txtSupplierAddress3.getText(),
                    recuritmentDate,
                    txtBankHolderName.getText(),
                    txtBankName.getText(),
                    txtBranch.getText(),
                    txtAccountNo.getText(),
                    lblUserID.getText()
            );

            boolean b = SupplierController.getInstance().addNewSupplier(supplier);
            System.out.println(b);
            if (b) {
                new Alert(Alert.AlertType.ERROR, "Employee Not Added ! Please ReTry..").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "New Employee Added !").show();
                clearText();
                loadNewSupplierID();
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnDeleteEmpOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        if (cmbSupplierID.isDisable()) {
            ObservableList<Supplier> allSupplier = SupplierController.getInstance().getAllSuppliers();
            ObservableList allSupplierID = FXCollections.observableArrayList();
            for (Supplier allSuppliers : allSupplier) {
                allSupplierID.add(new String(allSuppliers.getSupplierID()));
            }
            cmbSupplierID.setItems(allSupplierID);
            cmbSupplierID.setDisable(true);
        }
        cmbSupplierID.setValue(null);
        loadNewSupplierID();
        cmbSupplierID.setDisable(false);
    }

    @FXML
    void btnUpdateEmpOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSupplierIDOnAction(ActionEvent event) {
        Supplier supplier = SupplierController.getInstance().searchSupplier(cmbSupplierID.getValue().toString());
        if(supplier!=null) {
            txtFirstName.setText(supplier.getFirstName());
            txtLastName.setText(supplier.getLastName());
            cmbSex.setPromptText(supplier.getSex());
            txtNicNo.setText(supplier.getNicNO());
            txtMobileNo.setText(supplier.getMobileNo());
            txtSupplierAddress1.setText(supplier.getAddress1());
            txtSupplierAddress2.setText(supplier.getAddress2());
            txtSupplierAddress3.setText(supplier.getAddress3());
            txtCompanyName.setText(supplier.getCompanyName());
            txtRegNo.setText(supplier.getRegNo());
            txtTelNo.setText(supplier.getTelephoneNo());
            txtEmail.setText(supplier.getEMailAddress());
            txtComAddress1.setText(supplier.getCompanyAddress1());
            txtComAddress2.setText(supplier.getCompanyAddress2());
            txtComAddress3.setText(supplier.getCompanyAddress3());
            dateOfRecruit.setPromptText(supplier.getRecruitmentDate().toString());
            txtBankHolderName.setText(supplier.getBankHolder());
            txtBankName.setText(supplier.getBank());
            txtBranch.setText(supplier.getBranch());
            txtAccountNo.setText(supplier.getAccountNo());
            lblUserID.setText(supplier.getUserID());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadNewSupplierID();
        loadInitialValues();
        cmbSupplierID.setDisable(true);
    }

    private void loadInitialValues() {
        ObservableList list = FXCollections.observableArrayList();
        list.add(new String("Male"));
        list.add(new String("Female"));

        cmbSex.setItems(list);
    }

    private void loadNewSupplierID() {
        String id = SupplierController.getInstance().generateSupplierID();
        cmbSupplierID.setPromptText(id);
    }

    private void clearText() {
        cmbSupplierID.setValue(null);
        txtFirstName.setText("");
        txtLastName.setText("");
        cmbSex.setValue(null);
        txtNicNo.setText("");
        txtMobileNo.setText("");
        txtSupplierAddress1.setText("");
        txtSupplierAddress2.setText("");
        txtSupplierAddress3.setText("");
        txtCompanyName.setText("");
        txtRegNo.setText("");
        txtTelNo.setText("");
        txtEmail.setText("");
        txtComAddress1.setText("");
        txtComAddress2.setText("");
        txtComAddress3.setText("");
        dateOfRecruit.setValue(null);
        txtBankHolderName.setText("");
        txtBankName.setText("");
        txtBranch.setText("");
        txtAccountNo.setText("");
        lblUserID.setText("userId");
    }
}
