package edu.icet.pos.controller.employee;

import edu.icet.pos.crudUtil.CrudUtil;
import edu.icet.pos.db.DBConnection;
import edu.icet.pos.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class EmployeeController implements EmployeeService {

    private static EmployeeController instance;

    private EmployeeController() {
    }

    public static EmployeeController getInstance() {
        if (instance == null) {
            return instance = new EmployeeController();
        }
        return instance;
    }

    @Override
    public Employee searchEmployee(String empID) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee WHERE emp_id=?", empID);
            while (resultSet.next()) {
                return new Employee(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getDate(14),
                        resultSet.getDouble(15),
                        resultSet.getString(16),
                        resultSet.getString(17),
                        resultSet.getString(18),
                        resultSet.getString(19),
                        resultSet.getString(20),
                        resultSet.getString(21),
                        resultSet.getString(22),
                        resultSet.getString(23)
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ObservableList<Employee> getAllEmployees() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee");
            ObservableList<Employee> employeeList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                employeeList.add(
                        new Employee(
                                resultSet.getString("emp_id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getDate("dob"),
                                resultSet.getString("sex"),
                                resultSet.getString("nic_no"),
                                resultSet.getString("mobile_no"),
                                resultSet.getString("address1"),
                                resultSet.getString("address2"),
                                resultSet.getString("address3"),
                                resultSet.getString("education_level"),
                                resultSet.getString("department"),
                                resultSet.getString("designation"),
                                resultSet.getDate("recruitment_date"),
                                resultSet.getDouble("salary"),
                                resultSet.getString("emg_name"),
                                resultSet.getString("relation"),
                                resultSet.getString("emg_mobile_no"),
                                resultSet.getString("emg_address1"),
                                resultSet.getString("emg_address2"),
                                resultSet.getString("emg_address3"),
                                resultSet.getString("profile_img_url"),
                                resultSet.getString("admin_id")
                        )
                );
            }
            return employeeList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addEmployee(Employee employee) {
        String SQL = "INSERT INTO employee VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            CrudUtil.execute(
                    SQL,
                    employee.getEmployeeId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDob(),
                    employee.getSex(),
                    employee.getNicNO(),
                    employee.getMobileNo(),
                    employee.getAddress1(),
                    employee.getAddress2(),
                    employee.getAddress3(),
                    employee.getEducationLevel(),
                    employee.getDepartment(),
                    employee.getDesignation(),
                    employee.getRecruitmentDate(),
                    employee.getSalary(),
                    employee.getEmgContactName(),
                    employee.getRelationShip(),
                    employee.getEmgContactMobileNo(),
                    employee.getEmgContactAddress1(),
                    employee.getEmgContactAddress2(),
                    employee.getEmgContactAddress3(),
                    employee.getImgUrl(),
                    employee.getAdminId()
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public String generateEmpID() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSetCount = statement.executeQuery("SELECT COUNT(*) FROM employee");
            int count = 0;

            while (resultSetCount.next()) {
                count = resultSetCount.getInt(1);
            }

            if (count == 0) {
                return "0000001";
            }
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee ORDER BY emp_id DESC LIMIT 1;");

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
