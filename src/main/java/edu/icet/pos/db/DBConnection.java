package edu.icet.pos.db;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
@RequiredArgsConstructor
public class DBConnection {
    private static DBConnection dbConnection;

    private final Connection connection;
    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/clothify_System",
                "root",
                "Aloka#89764"
        );
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        if(dbConnection==null){
            return dbConnection = new DBConnection();
        }
        return dbConnection;
    }
}
