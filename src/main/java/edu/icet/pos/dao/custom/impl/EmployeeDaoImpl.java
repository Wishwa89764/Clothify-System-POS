package edu.icet.pos.dao.custom.impl;

import edu.icet.pos.dao.custom.EmployeeDao;
import edu.icet.pos.entity.EmployeeEntity;
import edu.icet.pos.utill.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    Session session = HibernateUtil.getSession();
    @Override
    public boolean save(EmployeeEntity entity) {
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public ObservableList getAllID() {
        ObservableList list = FXCollections.observableArrayList();
        Query query = session.createQuery("select id from employee");
        List resultList = query.getResultList();

        for (Object employeeEntity : resultList) {
            list.add(employeeEntity);

        }

        return list;
    }

    @Override
    public String getLastID() {
        String lastEmployeeID ="0";
        long resultCount = session.createQuery("SELECT COUNT(*) FROM employee").getResultCount();
        if(resultCount>0) {
            List resultList = session.createQuery("SELECT id FROM employee ORDER BY id DESC LIMIT 1").getResultList();

            for (Object o : resultList) {
                lastEmployeeID = o.toString();
            }
        }
        return lastEmployeeID;
    }

    @Override
    public <T> T getSelected(String id) {
        session.getTransaction().begin();
        EmployeeEntity employeeEntity = session.get(EmployeeEntity.class, id);
        session.close();

        return (T) employeeEntity;
    }
}
