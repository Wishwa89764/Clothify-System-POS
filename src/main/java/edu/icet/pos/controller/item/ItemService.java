package edu.icet.pos.controller.item;

import edu.icet.pos.model.Item;
import javafx.collections.ObservableList;

public interface ItemService {
    Item searchItem(String itemCode);

    ObservableList<Item> getAllItems();
    ObservableList getSelectedItems(String string);

    boolean AddItem(Item item);

    String generateItemCode(String category);
}
