package edu.icet.pos.bo.custom;

import edu.icet.pos.bo.SuperBo;
import edu.icet.pos.dto.Supplier;
import edu.icet.pos.entity.SupplierEntity;
import javafx.collections.ObservableList;

public interface SupplierBo extends SuperBo {
    boolean saveNewSupplier(Supplier dto);
    ObservableList getAllSupplierID();
    String getNewSupplierID();

    Supplier getSelectedSupplier(String supplierID);
}
