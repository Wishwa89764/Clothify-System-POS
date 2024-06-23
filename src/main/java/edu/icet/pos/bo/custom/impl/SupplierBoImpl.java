package edu.icet.pos.bo.custom.impl;

import edu.icet.pos.bo.custom.SupplierBo;
import edu.icet.pos.dao.DaoFactory;
import edu.icet.pos.dao.custom.SupplierDao;
import edu.icet.pos.dto.Supplier;
import edu.icet.pos.entity.SupplierEntity;
import edu.icet.pos.utill.DaoType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class SupplierBoImpl implements SupplierBo {
    private final SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
    @Override
    public boolean saveNewSupplier(Supplier dto) {

        return supplierDao.save(new ModelMapper().map(dto, SupplierEntity.class));
    }

    @Override
    public ObservableList getAllSupplierID() {
        return supplierDao.getAllID();
    }

    @Override
    public String getNewSupplierID() {
        String newItemID = "0000001";
        int lastItemID = Integer.parseInt(supplierDao.getLastID());

        if(lastItemID>0){
            int i = lastItemID+1;
            if (lastItemID < 10) {
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
        return newItemID;
    }

    @Override
    public Supplier getSelectedSupplier(String supplierID) {
        return new ModelMapper().map(supplierDao.getSelected(supplierID),Supplier.class);

    }
}
