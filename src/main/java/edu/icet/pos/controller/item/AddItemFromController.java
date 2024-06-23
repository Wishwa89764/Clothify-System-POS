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

import java.net.URL;
import java.util.ResourceBundle;

public class AddItemFromController implements Initializable {
    ItemService service = ItemController.getInstance();

    @FXML
    private JFXComboBox<?> cmbCategory;

    @FXML
    private TextArea imgItem;

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
                "My IMage"
        );

        boolean b = itemBo.saveNewItem(item);
        if(b){
            new Alert(Alert.AlertType.INFORMATION,"New Item Added !").show();
            clearText();
            loadInitialValues();
        }else{
            new Alert(Alert.AlertType.ERROR,"Item Not Added ! Please ReTry..").show();
        }

    }

    private void clearText() {
        lblItemID.setText("");
        txtItemName.setText("");
        txtItemDescription.setText("");
        txtItemName.requestFocus();
    }

    @FXML
    void btnCloseOnActiion(ActionEvent event) {

    }

    @FXML
    void btnUploadImageOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCategoryOnAction(ActionEvent event) {
       String category = cmbCategory.getValue().toString();

    }

    private void loadInitialValues(){
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
