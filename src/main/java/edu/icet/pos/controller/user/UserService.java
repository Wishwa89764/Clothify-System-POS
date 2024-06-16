package edu.icet.pos.controller.user;

import edu.icet.pos.model.User;
import javafx.collections.ObservableList;

public interface UserService {
    String generateUserID();
    boolean addNewUser(User user);
    User searchUser(long userID);
    ObservableList<User> getAllUsers();

}
