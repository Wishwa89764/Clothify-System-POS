package edu.icet.pos.controller.user;

import edu.icet.pos.dto.PasswordEncrypt;

public class PasswordEncryption {
    private static PasswordEncryption instance;

    private void PasswordEncrypt(){}

    public static PasswordEncryption getInstance(){
        return instance!=null?instance:(instance=new PasswordEncryption());
    }

    public PasswordEncrypt generateEncryptPassword(String myPassword){

        //generate the salt value. in can be stored in a database.
        String saltValue = PassBasedEnc.getSaltValue(30);

        // generate an encrypted password. it can be stored in a database.
        String encryptedPassword = PassBasedEnc.generateSecurePassword(myPassword, saltValue);

        return new PasswordEncrypt(saltValue, encryptedPassword);
    }
}
