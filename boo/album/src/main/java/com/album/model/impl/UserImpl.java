package com.album.model.impl;

import com.album.model.api.User;

/**
 * Created by akarpinska on 5/6/14.
 */
class UserImpl implements User {

    private final String fullName;
    private final String username;
    private final byte[] hashedPassword;

    public UserImpl(String fullName, String username, byte[] hashedPassword) {
        this.fullName = fullName;
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getHashedPassword() {
        return hashedPassword;
    }
}
