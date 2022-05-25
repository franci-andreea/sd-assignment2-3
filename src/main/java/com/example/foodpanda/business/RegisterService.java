package com.example.foodpanda.business;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class RegisterService
{
    /**
     * method to encode the password introduced by any user (whether it is a normal user or an administrator)
     * @param password - the password which will be encoded
     * @return the new encoded password
     * @throws NoSuchAlgorithmException
     */
    public static String encodePassword(String password) throws NoSuchAlgorithmException
    {
        //we will use MD5 encryption technique
        String encryptedPassword = null;

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());

        byte[] bytes = messageDigest.digest();

        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes)
        {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }

        encryptedPassword = sb.toString();

        return encryptedPassword;
    }
}
