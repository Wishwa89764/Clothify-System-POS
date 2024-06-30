package edu.icet.pos.bo;

import edu.icet.pos.bo.custom.SendOtpBo;
import edu.icet.pos.bo.custom.impl.*;
import edu.icet.pos.utill.BoType;

public class BoFactory {
    private static BoFactory instance;
    private BoFactory(){}
    public static BoFactory getInstance() {
        return instance != null ? instance : (instance = new BoFactory());
    }

    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case EMPLOYEE:return (T) new EmployeeBoImpl();
            case ITEM:return (T) new ItemBoImpl();
            case ORDER:return (T) new OrderBoImpl();
            case SUPPLIER:return (T) new SupplierBoImpl();
            case USER:return (T) new UserBoImpl();
            case OTP:return (T) new SendOtpBoImpl();
        }
        return null;
    }
}
