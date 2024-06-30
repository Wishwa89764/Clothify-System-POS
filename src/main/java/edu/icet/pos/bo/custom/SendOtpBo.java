package edu.icet.pos.bo.custom;

import edu.icet.pos.bo.SuperBo;

public interface SendOtpBo extends SuperBo {
    boolean sendOTPCode(String string);
    boolean otpAuthentication(String string);

    void isSucsess(boolean b);
}
