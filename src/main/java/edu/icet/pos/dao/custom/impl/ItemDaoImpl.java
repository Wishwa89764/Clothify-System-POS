package edu.icet.pos.dao.custom.impl;

import edu.icet.pos.dao.custom.EmployeeDao;
import edu.icet.pos.dao.custom.ItemDao;
import edu.icet.pos.entity.EmployeeEntity;
import edu.icet.pos.entity.ItemEntity;
import edu.icet.pos.utill.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ItemDaoImpl implements ItemDao {
    Session session = HibernateUtil.getSession();
    @Override
    public boolean save(ItemEntity entity) {
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public ObservableList getAllID() {
        ObservableList list = FXCollections.observableArrayList();
        Query query = session.createQuery("select id from item");
        List resultList = query.getResultList();

        for (Object employeeEntity : resultList) {
            list.add(employeeEntity);

        }

        return list;
    }

    @Override
    public String getLastID() {
        String lastItemID ="0";
        long resultCount = session.createQuery("SELECT COUNT(*) FROM item").getResultCount();
        if(resultCount>0) {
            List resultList = session.createQuery("SELECT id FROM item ORDER BY id DESC LIMIT 1").getResultList();

            for (Object o : resultList) {
                lastItemID = o.toString();
            }
        }
        return lastItemID;
    }

    @Override
    public <T> T getSelected(String id) {

        ItemEntity itemEntity = session.get(ItemEntity.class, id);


        return (T) itemEntity;
    }

    @Override
    public ObservableList<String> getSameId(String string) {
        ObservableList<String> list = FXCollections.observableArrayList();
        String url = "SELECT id FROM item WHERE id LIKE '%"+string+"%'";
        Query query = session.createQuery(url);
        List resultList = query.getResultList();

        for (Object o : resultList) {
            list.add(o.toString());
        }
        return list;
    }

    @Override
    public Long getRecordsCount() {
        return null;
    }
}
