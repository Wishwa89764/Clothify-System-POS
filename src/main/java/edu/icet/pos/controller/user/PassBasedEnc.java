package edu.icet.pos.controller.user;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class PassBasedEnc {
    private static final Random random = new SecureRandom();
    private static final String characters="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int iteration = 10000;
    private static final int keyLength =256;

    public static String getSaltValue(int length){
        StringBuilder finalVal = new StringBuilder(length);

        for(int i =0; i<length;i++){
            finalVal.append(characters.charAt(random.nextInt(characters.length())));
        }
        return new String(finalVal);
    }

    public static byte[] hash(char[] password, byte[]salt){
        PBEKeySpec spec = new PBEKeySpec(password,salt,iteration,keyLength);
        Arrays.fill(password,Character.MIN_VALUE);

        try {

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return secretKeyFactory.generateSecret(spec).getEncoded();

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);

        }
        finally {
            spec.clearPassword();
        }

    }

    public static String generateSecurePassword(String password, String salt){
        String finalVal = null;

        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

        finalVal = Base64.getEncoder().encodeToString(securePassword);

        return finalVal;
    }

    public static boolean verifyUserPassword(String providedPassword,String securedPassword, String salt){
        boolean finalval = false;

        String newSecurePassword = generateSecurePassword(providedPassword,salt);

        finalval = newSecurePassword.equalsIgnoreCase(securedPassword);

        return finalval;
    }
}
