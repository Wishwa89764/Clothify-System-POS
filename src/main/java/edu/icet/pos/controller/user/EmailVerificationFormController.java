package edu.icet.pos.controller.user;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.icet.pos.bo.BoFactory;
import edu.icet.pos.bo.custom.SendOtpBo;
import edu.icet.pos.utill.BoType;
import jakarta.mail.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EmailVerificationFormController implements Initializable {

    @FXML
    private FontAwesomeIconView btnClose;

    @FXML
    private JFXButton btnReSend;

    @FXML
    private JFXButton btnVerify;

    @FXML
    private Label lblEmail;

    @FXML
    private TextField txtOtpCode;

    SendOtpBo sendOtpBo = BoFactory.getInstance().getBo(BoType.OTP);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void btnCloseOnMouseClicked(MouseEvent event) {
        ((Stage) btnClose.getScene().getWindow()).close();
    }

    @FXML
    void btnReSendOnAction(ActionEvent event) {

    }

    @FXML
    void btnVerifyOnAction(ActionEvent event) {
        if(sendOtpBo.otpAuthentication(txtOtpCode.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Verification Success !");
            alert.setHeaderText("Success");
            alert.show();
            ((Stage)btnVerify.getScene().getWindow()).close();
            sendOtpBo.isSucsess(true);
            loadUserForm();
        }else {
            new Alert(Alert.AlertType.ERROR, "OTP Code missMatch !").show();
            txtOtpCode.setText("");
            txtOtpCode.requestFocus();
        }
    }

    private void loadUserForm(){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader
                    .load(Objects
                            .requireNonNull(getClass()
                                    .getResource("/view/add-user-form.fxml")))
            ));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
