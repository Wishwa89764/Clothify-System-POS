package edu.icet.pos.bo.custom;

import edu.icet.pos.bo.SuperBo;
import edu.icet.pos.dao.CrudDao;
import edu.icet.pos.dto.Employee;
import edu.icet.pos.dto.Item;
import edu.icet.pos.entity.EmployeeEntity;
import javafx.collections.ObservableList;

public interface ItemBo extends SuperBo {
    boolean saveNewItem(Item dto);
    ObservableList getAllItemID();
    String getNewItemID();

    Item getSelectedItem(String empID);

    ObservableList loadAllSame(String string);
    Long getRecordsCount();
}
