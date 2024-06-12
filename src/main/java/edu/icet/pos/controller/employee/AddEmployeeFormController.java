package edu.icet.pos.controller.employee;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.pos.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AddEmployeeFormController implements Initializable {
    public JFXComboBox cmbSex;
    public JFXTextField txtSalary;
    String imgPath;
    @FXML
    private JFXButton btnAddNewEmp;
    @FXML
    private JFXButton btnDeleteEmp;
    @FXML
    private JFXButton btnUpdateEmp;
    @FXML
    private JFXComboBox<?> cmbDepartment;
    @FXML
    private JFXComboBox<?> cmbEmpID;
    @FXML
    private DatePicker dateDOB;
    @FXML
    private DatePicker dateOfRecruit;
    @FXML
    private ImageView imgEmployee;
    @FXML
    private Label lblCurrentDate;
    @FXML
    private Label lblCurrentTime;
    @FXML
    private Label lblUserID;
    @FXML
    private JFXTextField txtEducationLevel;
    @FXML
    private JFXTextField txtEmgAddress1;
    @FXML
    private JFXTextField txtEmgAddress2;
    @FXML
    private JFXTextField txtEmgAddress3;
    @FXML
    private JFXTextField txtEmgMobilNo;
    @FXML
    private JFXTextField txtEmgName;
    @FXML
    private JFXTextField txtEmpAddress1;
    @FXML
    private JFXTextField txtEmpAddress2;
    @FXML
    private JFXTextField txtEmpAddress3;
    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private JFXTextField txtMobileNo;
    @FXML
    private JFXTextField txtNicNo;
    @FXML
    private JFXTextField txtPosition;
    @FXML
    private JFXTextField txtRelationShip;

    private void setCurrentDateandTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = dateFormat.format(date);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss a");
        String currentTime = timeFormat.format(date);

        lblCurrentDate.setText(currentDate);
        lblCurrentTime.setText(currentTime);
    }

    @FXML
    void btnAddEmpPictureOnAction(ActionEvent event) throws IOException {
        setImage();

    }


    @FXML
    void btnAddNewEmpOnAction(ActionEvent event) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateDob = null;
        Date recuitmentDate = null;

        try {
            dateDob = format.parse(dateDOB.getValue().toString());
            recuitmentDate = format.parse(dateOfRecruit.getValue().toString());
            Employee employee = new Employee(
                    cmbEmpID.getPromptText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    dateDob,
                    cmbSex.getValue().toString(),
                    txtNicNo.getText(),
                    txtMobileNo.getText(),
                    txtEmpAddress1.getText(),
                    txtEmpAddress2.getText(),
                    txtEmpAddress3.getText(),
                    txtEducationLevel.getText(),
                    cmbDepartment.getValue().toString(),
                    txtPosition.getText(),
                    recuitmentDate,
                    Double.parseDouble(txtSalary.getText()),
                    txtEmgName.getText(),
                    txtRelationShip.getText(),
                    txtEmgMobilNo.getText(),
                    txtEmgAddress1.getText(),
                    txtEmpAddress2.getText(),
                    txtEmpAddress3.getText(),
                    imgPath,
                    lblUserID.getText()
            );
            boolean b = EmployeeController.getInstance().addEmployee(employee);
            System.out.println(b);
            if (b) {
                new Alert(Alert.AlertType.ERROR, "Employee Not Added ! Please ReTry..").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "New Employee Added !").show();
                clearText();
                loadEmpID();
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearText() {
        cmbEmpID.setPromptText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        dateDOB.setValue(null);
        cmbSex.setValue(null);
        txtNicNo.setText("");
        txtMobileNo.setText("");
        txtEmpAddress1.setText("");
        txtEmpAddress2.setText("");
        txtEmpAddress3.setText("");
        txtEducationLevel.setText("");
        cmbDepartment.setValue(null);
        txtPosition.setText("");
        dateOfRecruit.setValue(null);
        txtSalary.setText("");
        txtEmgName.setText("");
        txtRelationShip.setText("");
        txtEmgMobilNo.setText("");
        txtEmgAddress1.setText("");
        txtEmgAddress2.setText("");
        txtEmgAddress3.setText("");
        lblUserID.setText("");
    }

    @FXML
    void btnDeleteEmpOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateEmpOnAction(ActionEvent event) {

    }

    @FXML
    void cmbEmpIDOnAction(ActionEvent event) {
        Employee employee = EmployeeController.getInstance().searchEmployee(cmbEmpID.getValue().toString());
        if (employee != null) {
            txtFirstName.setText(employee.getFirstName());
            txtLastName.setText(employee.getLastName());
            dateDOB.setPromptText(String.valueOf(employee.getDob()));
            cmbSex.setPromptText(employee.getSex());
            txtNicNo.setText(employee.getNicNO());
            txtMobileNo.setText(employee.getMobileNo());
            txtEmpAddress1.setText(employee.getAddress1());
            txtEmpAddress2.setText(employee.getAddress2());
            txtEmpAddress3.setText(employee.getAddress3());
            txtEducationLevel.setText(employee.getEducationLevel());
            cmbDepartment.setPromptText(employee.getDepartment());
            txtPosition.setText(employee.getDesignation());
            dateOfRecruit.setPromptText(String.valueOf(employee.getRecruitmentDate()));
            txtSalary.setText(String.valueOf(employee.getSalary()));
            txtEmgName.setText(employee.getEmgContactName());
            txtRelationShip.setText(employee.getRelationShip());
            txtEmgMobilNo.setText(employee.getEmgContactMobileNo());
            txtEmgAddress1.setText(employee.getEmgContactAddress1());
            txtEmgAddress2.setText(employee.getEmgContactAddress2());
            txtEmgAddress3.setText(employee.getEmgContactAddress3());
            Image image = new Image(employee.getImgUrl());
            imgEmployee.setImage(image);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbEmpID.setDisable(true);
        setCurrentDateandTime();
        loadInitialValues();

    }

    private void setImage() throws IOException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        File file = fileChooser.showOpenDialog(stage);
        Image image = new Image(file.toURI().toString(), 121, 143, true, true);
        imgEmployee.setImage(image);
        imgEmployee.setFitWidth(121);
        imgEmployee.setFitHeight(143);
        imgEmployee.setPreserveRatio(true);

        BufferedImage myImage = ImageIO.read(new File(file.getAbsolutePath()));
        imgPath = "F:\\ICET\\Java\\Java FX\\Final Course Work\\Clothify-System\\src\\main\\resources\\img\\" + cmbEmpID.getPromptText() + ".jpg";
        ImageIO.write(myImage, "jpg", new File("F:\\ICET\\Java\\Java FX\\Final Course Work\\Clothify-System\\src\\main\\resources\\img\\" + cmbEmpID.getPromptText() + ".jpg"));
    }

    private void loadInitialValues() {
        ObservableList list = FXCollections.observableArrayList();
        list.add(new String("Male"));
        list.add(new String("Female"));

        cmbSex.setItems(list);
        loadEmpID();
        setCmbDepartment();
    }

    private void setCmbDepartment() {
        ObservableList departmentList = FXCollections.observableArrayList();
        departmentList.add(new String("HR"));
        departmentList.add(new String("Account"));

        cmbDepartment.setItems(departmentList);
    }

    private void loadEmpID() {
        String newEmployeeID = EmployeeController.getInstance().generateEmpID();

        cmbEmpID.setPromptText(String.valueOf(newEmployeeID));
    }

    public void btnReloadOnAction(ActionEvent actionEvent) {
        if (cmbEmpID.isDisable()) {
            ObservableList<Employee> allEmployees = EmployeeController.getInstance().getAllEmployees();
            ObservableList allEmployeeID = FXCollections.observableArrayList();
            for (Employee allEmployee : allEmployees) {
                allEmployeeID.add(new String(allEmployee.getEmployeeId()));
            }
            cmbEmpID.setItems(allEmployeeID);
            cmbEmpID.setDisable(true);
        }
        cmbEmpID.setValue(null);
        loadEmpID();
        cmbEmpID.setDisable(false);
    }
}
