package edu.icet.pos.controller.item;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.pos.bo.BoFactory;
import edu.icet.pos.bo.custom.ItemBo;
import edu.icet.pos.bo.custom.impl.ItemBoImpl;
import edu.icet.pos.dto.Item;
import edu.icet.pos.utill.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemFromController implements Initializable {
    public ImageView imgItem;
    public JFXTextField txtSupplier;
    public JFXTextField txtItemCost;
    public JFXTextField txtItemSellingPrice;

    @FXML
    private JFXComboBox<?> cmbCategory;

    @FXML
    private Label lblItemID;

    @FXML
    private Label lblUserID;

    @FXML
    private JFXTextField txtItemDescription;

    @FXML
    private JFXTextField txtItemName;
    private final ItemBo itemBo = BoFactory.getInstance().getBo(BoType.ITEM);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadInitialValues();
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        Item item = new Item(
                lblItemID.getText(),
                cmbCategory.getValue().toString(),
                txtItemName.getText(),
                txtItemDescription.getText(),
                txtSupplier.getText(),
                Double.parseDouble(txtItemCost.getText()),
                Double.parseDouble(txtItemSellingPrice.getText()),
                imgItem.getImage().getUrl()
        );

        boolean b = itemBo.saveNewItem(item);
        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "New Item Added !").show();
            clearText();
            loadInitialValues();
        } else {
            new Alert(Alert.AlertType.ERROR, "Item Not Added ! Please ReTry..").show();
        }

    }

    private void clearText() {
        lblItemID.setText("");
        txtItemName.setText("");
        txtItemDescription.setText("");
        txtSupplier.setText("");
        txtItemCost.setText("");
        txtItemSellingPrice.setText("");
        txtItemName.requestFocus();
    }

    @FXML
    void btnCloseOnActiion(ActionEvent event) {

    }

    @FXML
    void btnUploadImageOnAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image");
            File file = fileChooser.showOpenDialog(stage);
            Image image = new Image(file.getAbsolutePath());
            imgItem.setImage(image);
            imgItem.setFitWidth(334);
            imgItem.setFitHeight(334);
            imgItem.setPreserveRatio(true);

            BufferedImage myImage = ImageIO.read(new File(file.getAbsolutePath()));
            ImageIO.write(myImage,
                    "jpg",
                    new File(
                            "F:\\ICET\\Java\\Java FX\\Final Course Work\\Clothify-System\\src\\main\\resources\\img\\itemImage\\" + lblItemID.getText() + ".jpg")
            );


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void cmbCategoryOnAction(ActionEvent event) {


    }

    private void loadInitialValues() {
        ObservableList category = FXCollections.observableArrayList();
        category.add(new String("KIDS Wear"));
        category.add(new String("MENS Wear"));
        category.add(new String("LADIES Wear"));
        category.add(new String("TOYS"));
        category.add(new String("OTHER"));

        cmbCategory.setItems(category);
        generateItemID();
    }

    private void generateItemID() {
        String newItemID = itemBo.getNewItemID();
        lblItemID.setText(newItemID);
    }


}
