package edu.icet.pos.bo.custom;

import edu.icet.pos.bo.SuperBo;
import edu.icet.pos.dto.Employee;
import javafx.collections.ObservableList;

public interface EmployeeBo extends SuperBo {
    boolean saveEmployee(Employee dto);
    ObservableList getAllEmployeeID();
    String getNewEmployeeID();

    Employee getSelectedEmployee(String empID);

}
