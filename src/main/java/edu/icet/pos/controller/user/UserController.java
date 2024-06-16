package edu.icet.pos.controller.user;

import edu.icet.pos.crudUtil.CrudUtil;
import edu.icet.pos.db.DBConnection;
import edu.icet.pos.model.User;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController implements UserService{
    @Override
    public String generateUserID() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSetCount = statement.executeQuery("SELECT COUNT(*) FROM user");
            int count =0;

            while(resultSetCount.next()){
                count= resultSetCount.getInt(1);
            }

            if(count==0){
                return "000001";
            }
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user ORDER BY user_id DESC LIMIT 1;");
            if(resultSet.next()){
                String string = resultSet.getString(1);
                String s = string.replaceAll("([A-Z])", "");
                int i = Integer.parseInt(s)+1;
                if (i < 10) {
                    return "00000" + i;
                } else if (i < 100) {
                    return "0000" + i;
                } else if (i < 1000) {
                    return "000" + i;
                } else if (i < 10000) {
                    return "00" + i;
                } else if (i < 100000) {
                    return "0" + i;
                } else {
                    return ""+i;
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    @Override
    public boolean addNewUser(User user) {
        String sql = "INSERT INTO user values(?,?,?,?,?)";
        try {
            Object execute = CrudUtil.execute(
                    sql,
                    user.getUserID(),
                    user.getUserName(),
                    user.getUserPassword(),
                    user.getEmpID(),
                    user.getRole()
            );
            if(execute.toString().equals("true")){
                return true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public User searchUser(long userID) {
        return null;
    }

    @Override
    public ObservableList<User> getAllUsers() {
        return null;
    }
}
