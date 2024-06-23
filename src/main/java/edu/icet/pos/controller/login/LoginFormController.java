package edu.icet.pos.controller.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.icet.pos.bo.BoFactory;
import edu.icet.pos.bo.custom.UserBo;
import edu.icet.pos.dto.User;
import edu.icet.pos.utill.BoType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    public JFXButton btnClose;
    public FontAwesomeIconView fontIconEye;
    public FontAwesomeIconView fontIconUserID;
    public HBox hBoxPasswordWarning;
    public JFXTextField txtPasswordShow;
    public HBox hBoxShowPassword;
    public HBox hBoxHidePassword;
    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserID;
    private final UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        if(isValidUserID()) {
            try {
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader
                        .load(Objects.requireNonNull(getClass()
                                .getResource("/view/main-form.fxml"))
                        )
                ));
                stage.setTitle("Clothify System");
                stage.setMaximized(true);
                stage.getIcons().add(new Image("img/logo.jpg"));
                stage.show();
                ((Stage) btnClose.getScene().getWindow()).close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            hBoxPasswordWarning.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPassword.textProperty().bindBidirectional(txtPasswordShow.textProperty());
        fontIconUserID.setVisible(false);
    }

    public void txtUserIDOnKeyType(KeyEvent keyEvent) {

    }

    public void fontIconEyeOnMouseClicked(MouseEvent mouseEvent) {
        hBoxHidePassword.setVisible(true);
        hBoxShowPassword.setVisible(false);
        txtPassword.requestFocus();
        txtPassword.positionCaret(txtPassword.getLength());
    }

    public void fontIconEyeSlashOnMouseClicked(MouseEvent mouseEvent) {
        hBoxHidePassword.setVisible(false);
        hBoxShowPassword.setVisible(true);
        txtPasswordShow.requestFocus();
        txtPasswordShow.positionCaret(txtPasswordShow.getLength());
    }

    public void btnFogotPasswordOnAction(ActionEvent actionEvent) {
    }

    public void txtUserIDOnKeyPressed(KeyEvent keyEvent) {
        String userID = txtUserID.getText();
        if(keyEvent.getCode()== KeyCode.ENTER||keyEvent.getCode()== KeyCode.TAB){
            if(userBo.isExistUser(userID)){
                fontIconUserID.setGlyphName("CHECK");
                fontIconUserID.setFill(Color.GREEN);
                fontIconUserID.setVisible(true);
                txtPassword.requestFocus();
            }else {
                fontIconUserID.setGlyphName("CLOSE");
                fontIconUserID.setFill(Color.RED);
                fontIconUserID.setVisible(true);
            }
        }
    }

    private boolean isValidUserID() {
        String text = txtUserID.getText();
        String password = txtPassword.getText();
        if(!text.isEmpty()||!password.isEmpty()) {
            User user = userBo.getSelectedUser(text);
            return user.getUserName().equals(text) && user.getUserPassword().equals(password);
        }
        return false;
    }

    public void txtPasswordOnKeyPressed(KeyEvent keyEvent) {
    }


}
