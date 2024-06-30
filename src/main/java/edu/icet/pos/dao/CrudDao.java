package edu.icet.pos.dao;

import jakarta.persistence.Entity;
import javafx.collections.ObservableList;

public interface CrudDao <T> extends SuperDao{
    boolean save(T entity);
    ObservableList getAllID();
    String getLastID();

    <T> T getSelected(String id);
    ObservableList<String> getSameId(String string);
    Long getRecordsCount();
}
