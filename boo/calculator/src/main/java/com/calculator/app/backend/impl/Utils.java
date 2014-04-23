package com.calculator.app.backend.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by akarpinska on 4/24/14.
 */
class Utils {

    public static String getMd5Hash(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            byte hash[] = md.digest();
            return new String(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
