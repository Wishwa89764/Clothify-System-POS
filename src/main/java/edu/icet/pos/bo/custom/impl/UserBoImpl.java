package edu.icet.pos.bo.custom.impl;

import edu.icet.pos.bo.custom.UserBo;
import edu.icet.pos.controller.user.AddUserFormController;
import edu.icet.pos.dao.DaoFactory;
import edu.icet.pos.dao.custom.UserDao;
import edu.icet.pos.dto.User;
import edu.icet.pos.entity.UserEntity;
import edu.icet.pos.utill.DaoType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class UserBoImpl implements UserBo {
    private final UserDao userDao = DaoFactory.getInstance().getDao(DaoType.USER);
    private static AddUserFormController addUserFormController;
    @Override
    public boolean saveNewUser(User dto) {

        return userDao.save(new ModelMapper().map(dto, UserEntity.class));
    }

    @Override
    public ObservableList getAllUserID() {
        return userDao.getAllID();
    }

    @Override
    public String getNewUserID() {
        String newItemID = "0000001";
        int lastItemID = Integer.parseInt(userDao.getLastID());

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
    public User getSelectedUser(String userID) {
        return new ModelMapper().map(userDao.getSelected(userID),User.class);

    }

    @Override
    public boolean isExistUser(String userID) {
        User user = new ModelMapper().map(userDao.getSelected(userID), User.class);
        if(user.getUserName()!=null){
            return true;
        }
        return false;
    }

    @Override
    public Long getRecordsCount() {
        return userDao.getRecordsCount();
    }

    @Override
    public void emailVerified(boolean b){
        if(addUserFormController==null) addUserFormController = new AddUserFormController();
        addUserFormController.addNewUser(b);
    }
}
