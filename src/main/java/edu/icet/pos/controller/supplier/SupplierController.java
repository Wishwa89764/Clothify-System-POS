package edu.icet.pos.controller.supplier;

import edu.icet.pos.crudUtil.CrudUtil;
import edu.icet.pos.db.DBConnection;
import edu.icet.pos.model.Employee;
import edu.icet.pos.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class SupplierController implements SupplierService{

    private static SupplierController instance;

    private SupplierController(){}

    public static SupplierController getInstance(){
        if(instance==null){
            return instance = new SupplierController();
        }
        return instance;
    }
    @Override
    public Supplier searchSupplier(String supID) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM supplier WHERE supplier_id=?", supID);
            while (resultSet.next()) {
                return new Supplier(
                        resultSet.getString("supplier_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("sex"),
                        resultSet.getString("nic_no"),
                        resultSet.getString("mobile_no"),
                        resultSet.getString("address1"),
                        resultSet.getString("address2"),
                        resultSet.getString("address3"),
                        resultSet.getString("company_name"),
                        resultSet.getString("reg_no"),
                        resultSet.getString("telephone_no"),
                        resultSet.getString("e_mail"),
                        resultSet.getString("com_address1"),
                        resultSet.getString("com_address2"),
                        resultSet.getString("com_address3"),
                        resultSet.getDate("recruitment_date"),
                        resultSet.getString("account_holder"),
                        resultSet.getString("bank"),
                        resultSet.getString("branch"),
                        resultSet.getString("account_no"),
                        resultSet.getString("user_id")
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ObservableList<Supplier> getAllSuppliers() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM supplier");
            ObservableList<Supplier> suppliersList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                suppliersList.add(
                        new Supplier(
                                resultSet.getString("supplier_id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getString("sex"),
                                resultSet.getString("nic_no"),
                                resultSet.getString("mobile_no"),
                                resultSet.getString("address1"),
                                resultSet.getString("address2"),
                                resultSet.getString("address3"),
                                resultSet.getString("company_name"),
                                resultSet.getString("reg_no"),
                                resultSet.getString("telephone_no"),
                                resultSet.getString("e_mail"),
                                resultSet.getString("com_address1"),
                                resultSet.getString("com_address2"),
                                resultSet.getString("com_address3"),
                                resultSet.getDate("recruitment_date"),
                                resultSet.getString("account_holder"),
                                resultSet.getString("bank"),
                                resultSet.getString("branch"),
                                resultSet.getString("account_no"),
                                resultSet.getString("user_id")
                        )
                );
            }
            return suppliersList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addNewSupplier(Supplier supplier) {
        String sql = "INSERT INTO supplier VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            CrudUtil.execute(
              sql,
              supplier.getSupplierID(),
              supplier.getFirstName(),
              supplier.getLastName(),
              supplier.getSex(),
              supplier.getNicNO(),
              supplier.getMobileNo(),
              supplier.getAddress1(),
              supplier.getAddress2(),
              supplier.getAddress3(),
              supplier.getCompanyName(),
              supplier.getRegNo(),
              supplier.getTelephoneNo(),
              supplier.getEMailAddress(),
              supplier.getCompanyAddress1(),
              supplier.getCompanyAddress2(),
              supplier.getCompanyAddress3(),
              supplier.getRecruitmentDate(),
              supplier.getBankHolder(),
              supplier.getBank(),
              supplier.getBranch(),
              supplier.getAccountNo(),
              supplier.getUserID()
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public String generateSupplierID() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSetCount = statement.executeQuery("SELECT COUNT(*) FROM supplier");
            int count =0;

            while(resultSetCount.next()){
                count= resultSetCount.getInt(1);
            }

            if(count==0){
                return "SUP000001";
            }
            ResultSet resultSet = statement.executeQuery("SELECT * FROM supplier ORDER BY supplier_id DESC LIMIT 1;");
            if(resultSet.next()){
                String string = resultSet.getString(1);
                String s = string.replaceAll("([A-Z])", "");
                int i = Integer.parseInt(s)+1;
                if (i < 10) {
                    return "SUP00000" + i;
                } else if (i < 100) {
                    return "SUP0000" + i;
                } else if (i < 1000) {
                    return "SUP000" + i;
                } else if (i < 10000) {
                    return "SUP00" + i;
                } else if (i < 100000) {
                    return "SUP0" + i;
                } else {
                    return "SUP"+i;
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
