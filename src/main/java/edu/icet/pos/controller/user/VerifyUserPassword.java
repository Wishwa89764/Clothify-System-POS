package edu.icet.pos.controller.user;

import edu.icet.pos.bo.BoFactory;
import edu.icet.pos.bo.custom.UserBo;
import edu.icet.pos.dto.User;
import edu.icet.pos.utill.BoType;

public class VerifyUserPassword {

    public Boolean isVerify(String userID, String userPassword) {
        UserBo bo = BoFactory.getInstance().getBo(BoType.USER);

        User selectedUser = bo.getSelectedUser(userID);

        // verify the original password and encrypted password
        return PassBasedEnc.verifyUserPassword(userPassword, selectedUser.getSecurePassword(), selectedUser.getSalt());
    }
}
