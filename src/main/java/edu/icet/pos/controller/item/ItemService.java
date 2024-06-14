package edu.icet.pos.controller.item;

import edu.icet.pos.model.Item;
import javafx.collections.ObservableList;

public interface ItemService {
    Item searchEmployee(String itemCode);

    ObservableList<Item> getAllItems();

    boolean AddItem(Item item);

    String generateItemCode(String category);
}
