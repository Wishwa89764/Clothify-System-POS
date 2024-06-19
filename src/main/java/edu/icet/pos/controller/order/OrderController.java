package edu.icet.pos.controller.order;

import edu.icet.pos.crudUtil.CrudUtil;
import edu.icet.pos.db.DBConnection;
import edu.icet.pos.model.Order;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderController implements OrderService {
    private static OrderController instance;

    private OrderController(){}

    public static OrderController getInstance(){
        if(instance==null){
           return instance=new OrderController();
        }
        return instance;
    }
    @Override
    public boolean placeNewOrder(Order order) {
        String sql = "INSERT INTO inventryManager VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            CrudUtil.execute(
              sql,
              order.getOrderID(),
              order.getItemCode(),
              order.getItemName(),
              order.getQty(),
              order.getNetPrice(),
              order.getGrossPrice(),
              order.getDiscount(),
              order.getNetPrice(),
              order.getDate(),
              order.getTime(),
              order.getUserID()
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    @Override
    public String generateOrderID() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSetCount = statement.executeQuery("SELECT COUNT(*) FROM inventryManager");
            int count = 0;

            while (resultSetCount.next()) {
                count = resultSetCount.getInt(1);
            }

            if (count == 0) {
                return "0000001";
            }
            ResultSet resultSet = statement.executeQuery("SELECT * FROM inventryManager ORDER BY orderID DESC LIMIT 1;");

            if (resultSet.next()) {
                int i = Integer.parseInt(resultSet.getString(1)) + 1;
                if (i < 10) {
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
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
