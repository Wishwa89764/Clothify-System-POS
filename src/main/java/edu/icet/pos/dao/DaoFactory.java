package edu.icet.pos.dao;

import edu.icet.pos.dao.custom.impl.*;
import edu.icet.pos.utill.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}
    public static DaoFactory getInstance(){
        return instance!=null?instance:(instance = new DaoFactory());
    }

    public <T extends SuperDao> T getDao(DaoType type){
        switch (type){
            case EMPLOYEE:return (T) new EmployeeDaoImpl();
            case ITEM:return (T) new ItemDaoImpl();
            case ORDER:return (T) new OrderDaoImpl();
            case SUPPLIER:return (T) new SupplierDaoImpl();
            case USER:return (T) new UserDaoImpl();
        }
        return null;
    }
}
