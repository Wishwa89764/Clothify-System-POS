package edu.icet.pos.bo.custom.impl;

import edu.icet.pos.bo.custom.EmployeeBo;
import edu.icet.pos.dao.DaoFactory;
import edu.icet.pos.dao.custom.EmployeeDao;
import edu.icet.pos.dto.Employee;
import edu.icet.pos.entity.EmployeeEntity;
import edu.icet.pos.utill.DaoType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class EmployeeBoImpl implements EmployeeBo {
    private EmployeeDao employeeDao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
    @Override
    public boolean saveEmployee(Employee dto) {
        return employeeDao.save(new ModelMapper().map(dto, EmployeeEntity.class));
    }

    @Override
    public ObservableList getAllEmployeeID() {
        return employeeDao.getAllID();
    }

    @Override
    public String getNewEmployeeID() {
        String newEmployeeID = "0000001";
        int lastEmployeeID = Integer.parseInt(employeeDao.getLastID());

        if(lastEmployeeID>0){
            int i = lastEmployeeID+1;
            if (lastEmployeeID < 10) {
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
        return newEmployeeID;
    }

    @Override
    public Employee getSelectedEmployee(String empID) {
        return new ModelMapper().map(employeeDao.getSelected(empID),Employee.class);

    }
}
