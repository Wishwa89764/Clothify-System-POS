package edu.icet.pos.controller.order;

import com.jfoenix.controls.JFXButton;
import edu.icet.pos.controller.item.ItemController;
import edu.icet.pos.controller.item.ItemService;
import edu.icet.pos.dto.tm.OrderItemTable;
import edu.icet.pos.model.Item;
import edu.icet.pos.model.Order;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;


public class OrderManagementFormController implements Initializable {
    public Label lblNetPrice;
    public TableView tblItems;
    public Label lblUnitPrice;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colDiscount;
    public TableColumn colNetPrice;
    public Label lblDiscount;
    final ItemService itemService = ItemController.getInstance();
    public Label lblGrossPrice;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Label lblOrderID;

    @FXML
    private Label lblTotal;

    @FXML
    private ListView<?> listView;

    @FXML
    private TextField txtDiscount;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtQty;

    ObservableList<OrderItemTable> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(
                new PropertyValueFactory<>("itemCode")
        );
        colDescription.setCellValueFactory(
                new PropertyValueFactory<>("itemName")
        );
        colQty.setCellValueFactory(
                new PropertyValueFactory<>("qty")
        );
        colUnitPrice.setCellValueFactory(
                new PropertyValueFactory<>("unitPrice")
        );
        colDiscount.setCellValueFactory(
                new PropertyValueFactory<>("discount")
        );
        colNetPrice.setCellValueFactory(
                new PropertyValueFactory<>("netPrice")
        );
        loadGenerateID();
    }

    private void loadGenerateID() {
        String s = OrderController.getInstance().generateOrderID();
        lblOrderID.setText(s);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String code = txtItemCode.getText();
        for(int i = list.size();i>0;i--){
            if(list.get(i-1).getItemCode().equals(code)){
                list.remove(i-1);
                btnUpdate.setVisible(false);
                btnDelete.setVisible(false);
                clearText();
                tblItems.refresh();
                txtItemCode.requestFocus();
                txtItemCode.setEditable(true);

            }

        }
    }

    @FXML
    void btnNextOnAction(ActionEvent event) {
        for(int i = list.size();i>0;i--){
            Order order = new Order(
                    lblOrderID.getText(),
                    list.get(i-1).getItemCode(),
                    list.get(i-1).getItemName(),
                    Integer.parseInt(list.get(i-1).getQty()),
                    Double.parseDouble(list.get(i-1).getUnitPrice()),
                    Double.parseDouble(list.get(i-1).getGrossPrice()),
                    Double.parseDouble(list.get(i-1).getDiscount()),
                    Double.parseDouble(list.get(i-1).getNetPrice()),
                    setCurrentDate(),
                    setCurrentTime(),
                    "000001"
            );
            boolean b = OrderController.getInstance().placeNewOrder(order);
            if(i==1){
                if (b) {
                    new Alert(Alert.AlertType.ERROR, "Employee Not Added ! Please ReTry..").show();
                } else {
                  new Alert(Alert.AlertType.CONFIRMATION, "New Employee Added !")
                    .showAndWait()
                          .filter(response -> response == ButtonType.OK)
                          .ifPresent(response -> reloadForm());

                }
            }
        }


    }
    private String setCurrentDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return dateFormat.format(date);
    }

    private String setCurrentTime() {
        Date date = new Date();

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss a");

        return timeFormat.format(date);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String code = txtItemCode.getText();
        for(int i = list.size();i>0;i--){
            if(list.get(i-1).getItemCode().equals(code)){
                list.get(i-1).setQty(txtQty.getText());
                list.get(i-1).setGrossPrice(lblGrossPrice.getText());
                list.get(i-1).setDiscount(lblDiscount.getText());
                list.get(i-1).setNetPrice(lblNetPrice.getText());
                tblItems.refresh();
                btnUpdate.setVisible(false);
                btnDelete.setVisible(false);
            }
        }

    }

    @FXML
    void tblItemsOnMouseClicked(MouseEvent event) {
        int selectedIndex = tblItems.getSelectionModel().getSelectedIndex();
        txtItemCode.setText(list.get(selectedIndex).getItemCode());
        txtItemCode.setEditable(false);
        txtItemName.setText(list.get(selectedIndex).getItemName());
        txtItemName.setEditable(false);
        txtQty.setText(list.get(selectedIndex).getQty());
        lblUnitPrice.setText(list.get(selectedIndex).getUnitPrice());
        lblGrossPrice.setText(list.get(selectedIndex).getGrossPrice());
        lblDiscount.setText(list.get(selectedIndex).getDiscount());
        lblNetPrice.setText(list.get(selectedIndex).getNetPrice());
        txtQty.requestFocus();
        btnUpdate.setVisible(true);
        btnDelete.setVisible(true);

    }

    @FXML
    void txtItemCodeOnKeyTyped(KeyEvent event) {
        String s = txtItemCode.getText();
        listView.setVisible(false);
        if (s != null) {
            loadInitialValues(s, "GET");
        }
    }

    @FXML
    void txtQtyOnKeyTyped(KeyEvent event) {
        if(!txtQty.getText().isEmpty()) {
            int i = Integer.parseInt(txtQty.getText());
            netValueCal(i);
        }
    }

    void netValueCal(int qty){
        double value = (qty * Double.parseDouble(lblUnitPrice.getText()));
        lblGrossPrice.setText(String.valueOf(value));
        if(!txtDiscount.getText().isEmpty()){
            double discount = (value * Double.parseDouble(txtDiscount.getText())) / 100;
            double netValue = value - discount;
            lblDiscount.setText(String.valueOf(discount));
            lblNetPrice.setText(String.valueOf(netValue));
        }else{
            lblNetPrice.setText(String.valueOf(value));
        }

    }

    void loadInitialValues(String s, String string) {
        if(!s.isEmpty()) {
            if (string.equals("GET")) {
                ObservableList selectedItems = itemService.getSelectedItems(s);
                if (selectedItems != null) {
                    listView.setItems(selectedItems);
                    listView.setVisible(true);
                }
            } else if (string.equals("SET")) {
                Item item = itemService.searchItem(s);
                if (item != null) {
                    txtItemName.setText(item.getItemName());
                    lblUnitPrice.setText("1500");
                    txtDiscount.setText("");
                    listView.setVisible(false);
                }

            }
        }else{
            listView.setVisible(false);
        }
    }

    public void listViewOnMouseClicked(MouseEvent mouseEvent) {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        Object o = listView.getItems().get(selectedIndex);
        loadInitialValues(o.toString(), "SET");
    }

    public void txtItemCodeOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.DOWN && listView.isVisible()) {
            listView.requestFocus();

        }else {
            listView.setVisible(false);
        }
    }

    public void listViewOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            Object o = listView.getItems().get(selectedIndex);
            txtItemCode.setText(listView.getItems().get(selectedIndex).toString());
            listView.setVisible(false);
            loadInitialValues(o.toString(), "SET");
            txtDiscount.setText("00");
            txtDiscount.requestFocus();
        }
    }

    public void txtQtyOnKeyPressed(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();

        if (!txtQty.getText().isEmpty()) {

            if (keyCode == KeyCode.ENTER) {
                if(updateSameItems()){
                    tblItems.refresh();
                }else {
                    list.add(
                            new OrderItemTable(
                                    txtItemCode.getText(),
                                    txtItemName.getText(),
                                    txtQty.getText(),
                                    lblUnitPrice.getText(),
                                    lblGrossPrice.getText(),
                                    lblDiscount.getText(),
                                    lblNetPrice.getText()
                            )
                    );
                    tblItems.setItems(list);
                }
                getTotal();
                clearText();
            }

        }

    }

    private void getTotal() {
        double total = 0;
        for(int i=list.size();i>0;i--){
            total=total+Double.parseDouble(list.get(i-1).getNetPrice());
        }
        lblTotal.setText(String.valueOf(total));
    }

    private void clearText() {
        txtItemCode.setText("");
        txtItemName.setText("");
        txtDiscount.setText("");
        txtQty.setText("");
        lblUnitPrice.setText("");
        lblDiscount.setText("");
        lblUnitPrice.setText("");
        lblGrossPrice.setText("");
        txtItemCode.requestFocus();
    }

    boolean updateSameItems(){
        int size =list.size();
        if(size==0){
            return false;
        }else {

            for(int i =size;i>0;i--){
                if(list.get(i-1).getItemCode().equals(txtItemCode.getText())){
                    list.get(i-1).setQty(String.valueOf(
                            Integer.parseInt(
                                    list.get(i-1).getQty())+Integer.parseInt(
                                            txtQty.getText())
                    ));
                   if(!txtDiscount.getText().equals("0.0")) {
                        list.get(i - 1).setDiscount(String.valueOf(
                                Double.parseDouble(
                                        list.get(i - 1).getDiscount()) + Double.parseDouble(
                                        lblDiscount.getText())
                        ));
                    }
                    list.get(i-1).setGrossPrice(String.valueOf(
                            Double.parseDouble(
                                    list.get(i-1).getGrossPrice())+Double.parseDouble(
                                    lblGrossPrice.getText())
                    ));

                    list.get(i-1).setNetPrice(String.valueOf(
                            Double.parseDouble(
                                    list.get(i-1).getNetPrice())+Double.parseDouble(
                                    lblNetPrice.getText())
                    ));
                    return true;
                }
            }
        }
        return false;
    }

    public void txtDiscountOnKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode()==KeyCode.ENTER){
            if(txtDiscount.getText().isEmpty()){
                txtDiscount.setText("0.00");
                txtQty.requestFocus();
            }else{
                txtQty.requestFocus();
            }
        }
    }
    private void reloadForm(){
        try {
            Parent fxmlLoader = new FXMLLoader(getClass().getResource("/view/order-management-form.fxml")).load();
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
