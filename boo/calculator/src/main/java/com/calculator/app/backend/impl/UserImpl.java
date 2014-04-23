package com.calculator.app.backend.impl;

import com.calculator.app.backend.api.User;

/**
 * Created by akarpinska on 4/24/14.
 */
class UserImpl implements User {

    private final String fullName;
    private final String userName;
    private final String passwordHash;

    public UserImpl(String fullName, String userName, String passwordHash) {
        this.fullName = fullName;
        this.userName = userName;
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
