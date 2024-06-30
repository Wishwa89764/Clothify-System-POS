package edu.icet.pos.bo.custom.impl;

import edu.icet.pos.bo.BoFactory;
import edu.icet.pos.bo.custom.SendOtpBo;
import edu.icet.pos.bo.custom.UserBo;
import edu.icet.pos.utill.BoType;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;

public class SendOtpBoImpl implements SendOtpBo {
    private final String MYEMAIL = "wishwaaloka89764@gmail.com";
    private final String PASSWORD = "hxha oukc kuid bdcc";
    private static String otpCode = "";
    private final UserBo USERBO = BoFactory.getInstance().getBo(BoType.USER);

    private Integer generateOTPCode() {
        int min = 100000;
        int max = 999999;

        return (int) (Math.random() * (max - min + 1) + min);
    }

    public boolean sendOTPCode(String recipientEmail) {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        try {

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(MYEMAIL, PASSWORD);
                }
            });


            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MYEMAIL));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSentDate(new Date());
            message.setSubject("Clothify Store - OTP Code");
            otpCode = String.valueOf(generateOTPCode());
            message.setText("Your OTP Code is >> "+otpCode);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    public boolean otpAuthentication(String code){
        return otpCode.equals(code);

    }

    @Override
    public void isSucsess(boolean b) {

        USERBO.emailVerified(b);
    }
}
