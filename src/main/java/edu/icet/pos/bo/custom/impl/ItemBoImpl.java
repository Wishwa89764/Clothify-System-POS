package edu.icet.pos.bo.custom.impl;

import edu.icet.pos.bo.custom.EmployeeBo;
import edu.icet.pos.bo.custom.ItemBo;
import edu.icet.pos.dao.DaoFactory;
import edu.icet.pos.dao.custom.EmployeeDao;
import edu.icet.pos.dao.custom.ItemDao;
import edu.icet.pos.dto.Employee;
import edu.icet.pos.dto.Item;
import edu.icet.pos.entity.EmployeeEntity;
import edu.icet.pos.entity.ItemEntity;
import edu.icet.pos.utill.DaoType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class ItemBoImpl implements ItemBo {
    private final ItemDao itemDao = DaoFactory.getInstance().getDao(DaoType.ITEM);
    @Override
    public boolean saveNewItem(Item dto) {
        return itemDao.save(new ModelMapper().map(dto, ItemEntity.class));
    }

    @Override
    public ObservableList getAllItemID() {
        return itemDao.getAllID();
    }

    @Override
    public String getNewItemID() {
        String newItemID = "0000001";
        int lastItemID = Integer.parseInt(itemDao.getLastID());

        if(lastItemID>0){
            int i = lastItemID+1;
            if (lastItemID < 10) {
                return "000000" + i;
            } else if (i < 100) {
                return "00000" + i;
            } else if (i < 1000) {
                return "0000" + i;
            } else if (i < 10000) {
                return "000" + i;
            } else if (i < 100000) {
                return "00" + i;
            } else if (i < 1000000) {
                return "0" + i;
            } else {
                return String.valueOf(i);
            }
        }
        return newItemID;
    }

    @Override
    public Employee getSelectedItem(String empID) {
        return new ModelMapper().map(itemDao.getSelected(empID),Employee.class);

    }
}
