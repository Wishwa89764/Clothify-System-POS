package edu.icet.pos.controller.supplier;

import edu.icet.pos.model.Employee;
import edu.icet.pos.model.Supplier;
import javafx.collections.ObservableList;

public interface SupplierService {

    Supplier searchSupplier(String supID);

    ObservableList<Supplier> getAllSuppliers();

    boolean addNewSupplier(Supplier supplier);

    String generateSupplierID();
}
