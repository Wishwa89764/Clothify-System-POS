package edu.icet.pos.controller.item;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.pos.model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import lombok.RequiredArgsConstructor;

import java.net.URL;
import java.util.ResourceBundle;

public class AddItemFromController implements Initializable {
    ItemService service = new ItemController();

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadInitialValues();
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        Item myIMage = new Item(
                lblItemID.getText(),
                cmbCategory.getValue().toString(),
                txtItemName.getText(),
                txtItemDescription.getText(),
                "My IMage"
        );

        boolean b = service.AddItem(myIMage);
        System.out.println(b);

    }

    @FXML
    void btnCloseOnActiion(ActionEvent event) {

    }

    @FXML
    void btnUploadImageOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCategoryOnAction(ActionEvent event) {
       String id = cmbCategory.getValue().toString();
        System.out.println(id);
        String itemCode = service.generateItemCode(id);
        lblItemID.setText(itemCode);
    }

    private void loadInitialValues(){
        ObservableList category = FXCollections.observableArrayList();
        category.add(new String("KIDS Wear"));
        category.add(new String("MENS Wear"));
        category.add(new String("LADIES Wear"));
        category.add(new String("TOYS"));
        category.add(new String("OTHER"));

        cmbCategory.setItems(category);

    }


}
