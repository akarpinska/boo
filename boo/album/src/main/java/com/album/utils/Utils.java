package com.album.utils;

import java.security.MessageDigest;

/**
 * Created by akarpinska on 5/6/14.
 */
public class Utils {

    public static byte[] getMd5Hash(String source) {
        byte[] result = null;
        try {
            byte[] bytesOfMessage = source.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            result = md.digest(bytesOfMessage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
