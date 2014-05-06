package com.album.model.api;

/**
 * Created by akarpinska on 5/6/14.
 */
public interface User {

    public String getFullName();

    public String getUsername();

    public byte[] getHashedPassword();
}
