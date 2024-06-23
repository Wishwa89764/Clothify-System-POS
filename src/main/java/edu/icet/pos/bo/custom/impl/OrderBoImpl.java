package edu.icet.pos.bo.custom.impl;

import edu.icet.pos.bo.custom.ItemBo;
import edu.icet.pos.bo.custom.OrderBo;
import edu.icet.pos.dao.DaoFactory;
import edu.icet.pos.dao.custom.ItemDao;
import edu.icet.pos.dao.custom.OrderDao;
import edu.icet.pos.dto.Employee;
import edu.icet.pos.dto.Item;
import edu.icet.pos.dto.Order;
import edu.icet.pos.entity.ItemEntity;
import edu.icet.pos.entity.OrderEntity;
import edu.icet.pos.utill.DaoType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class OrderBoImpl implements OrderBo {
    private final OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);
    @Override
    public boolean saveNewOrder(Order dto) {

        return orderDao.save(new ModelMapper().map(dto, OrderEntity.class));
    }

    @Override
    public ObservableList getAllOrderID() {
        return orderDao.getAllID();
    }

    @Override
    public String getNewOrderID() {
        String newItemID = "0000001";
        int lastItemID = Integer.parseInt(orderDao.getLastID());

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
    public Order getSelectedOrder(String orderID) {
        return new ModelMapper().map(orderDao.getSelected(orderID),Order.class);

    }
}
