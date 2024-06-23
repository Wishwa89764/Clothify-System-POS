package edu.icet.pos.dao.custom.impl;

import edu.icet.pos.dao.custom.ItemDao;
import edu.icet.pos.dao.custom.OrderDao;
import edu.icet.pos.entity.ItemEntity;
import edu.icet.pos.entity.OrderEntity;
import edu.icet.pos.utill.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    Session session = HibernateUtil.getSession();
    @Override
    public boolean save(OrderEntity entity) {
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public ObservableList getAllID() {
        ObservableList list = FXCollections.observableArrayList();
        Query query = session.createQuery("select id from orders");
        List resultList = query.getResultList();

        for (Object employeeEntity : resultList) {
            list.add(employeeEntity);

        }

        return list;
    }

    @Override
    public String getLastID() {
        long resultCount = session.createQuery("SELECT COUNT(*) FROM orders").getResultCount();
        if(resultCount>0) {
            List resultList = session.createQuery("SELECT id FROM orders ORDER BY id DESC LIMIT 1").getResultList();

            for (Object o : resultList) {
                return o.toString();
            }
        }
        return "0";
    }

    @Override
    public <T> T getSelected(String id) {
        session.getTransaction().begin();
        OrderEntity orderEntity = session.get(OrderEntity.class, id);
        session.close();

        return (T) orderEntity;
    }
}
