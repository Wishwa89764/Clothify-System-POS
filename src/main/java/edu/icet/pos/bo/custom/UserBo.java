package edu.icet.pos.bo.custom;

import edu.icet.pos.bo.SuperBo;
import edu.icet.pos.dto.User;
import javafx.collections.ObservableList;

public interface UserBo extends SuperBo {
    boolean saveNewUser(User dto);
    ObservableList getAllUserID();
    String getNewUserID();

    User getSelectedUser(String userID);

    boolean isExistUser(String userID);
    Long getRecordsCount();

   void emailVerified(boolean b);
}
