package edu.icet.pos.dao.custom.impl;

import edu.icet.pos.dao.custom.OrderDao;
import edu.icet.pos.dao.custom.SupplierDao;
import edu.icet.pos.entity.OrderEntity;
import edu.icet.pos.entity.SupplierEntity;
import edu.icet.pos.utill.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    Session session = HibernateUtil.getSession();
    @Override
    public boolean save(SupplierEntity entity) {
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public ObservableList getAllID() {
        ObservableList list = FXCollections.observableArrayList();
        Query query = session.createQuery("select id from supplier");
        List resultList = query.getResultList();

        for (Object employeeEntity : resultList) {
            list.add(employeeEntity);

        }

        return list;
    }

    @Override
    public String getLastID() {
        long resultCount = session.createQuery("SELECT COUNT(*) FROM supplier").getResultCount();
        if(resultCount>0) {
            List resultList = session.createQuery("SELECT id FROM supplier ORDER BY id DESC LIMIT 1").getResultList();

            for (Object o : resultList) {
                return o.toString();
            }
        }
        return "0";
    }

    @Override
    public <T> T getSelected(String id) {
        session.getTransaction().begin();
        SupplierEntity supplierEntity = session.get(SupplierEntity.class, id);
        session.close();

        return (T) supplierEntity;
    }
}
