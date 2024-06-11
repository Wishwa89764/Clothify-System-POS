package edu.icet.pos.controller.employee;

import edu.icet.pos.model.Employee;
import javafx.collections.ObservableList;

public interface EmployeeService {
    Employee searchEmployee(String empID);

    ObservableList<Employee> getAllEmployees();

    boolean addEmployee(Employee employee);

    String generateEmpID();

}
