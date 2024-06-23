package edu.icet.pos.bo.custom;

import edu.icet.pos.bo.SuperBo;
import edu.icet.pos.dto.Employee;
import edu.icet.pos.dto.Item;
import edu.icet.pos.dto.Order;
import javafx.collections.ObservableList;

public interface OrderBo extends SuperBo {
    boolean saveNewOrder(Order dto);
    ObservableList getAllOrderID();
    String getNewOrderID();

    Order getSelectedOrder(String orderID);
}
