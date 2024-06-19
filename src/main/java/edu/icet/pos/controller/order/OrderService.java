package edu.icet.pos.controller.order;

import edu.icet.pos.model.Order;
import javafx.collections.ObservableList;

public interface OrderService {
    boolean placeNewOrder(Order order);
    String generateOrderID();

}
