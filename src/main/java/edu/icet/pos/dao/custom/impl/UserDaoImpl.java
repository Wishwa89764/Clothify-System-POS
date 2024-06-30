package edu.icet.pos.dao.custom.impl;

import edu.icet.pos.dao.custom.SupplierDao;
import edu.icet.pos.dao.custom.UserDao;
import edu.icet.pos.entity.SupplierEntity;
import edu.icet.pos.entity.UserEntity;
import edu.icet.pos.utill.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl implements UserDao {
    Session session = HibernateUtil.getSession();

    @Override
    public boolean save(UserEntity entity) {
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public ObservableList getAllID() {
        ObservableList list = FXCollections.observableArrayList();
        Query query = session.createQuery("select id from user");
        List resultList = query.getResultList();

        for (Object employeeEntity : resultList) {
            list.add(employeeEntity);

        }

        return list;
    }

    @Override
    public String getLastID() {
        Long recordsCount = getRecordsCount();

        if (recordsCount > 0) {
            List resultList = session.createQuery("SELECT id FROM user ORDER BY id DESC LIMIT 1").getResultList();

            for (Object o : resultList) {
                return o.toString();
            }
        }
        return "0";
    }

    @Override
    public <T> T getSelected(String id) throws NullPointerException {
        UserEntity userEntity;
        userEntity = session.get(UserEntity.class, id);
        if(userEntity==null){
            return (T) new UserEntity();
        }
        return (T) userEntity;
    }

    @Override
    public ObservableList<String> getSameId(String string) {
        return null;
    }

    @Override
    public Long getRecordsCount() {
        return session.createQuery("SELECT COUNT(*) FROM user").getResultCount();
    }
}
