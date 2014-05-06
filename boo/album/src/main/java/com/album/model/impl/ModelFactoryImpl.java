package com.album.model.impl;

import com.album.model.api.ModelFactory;
import com.album.model.api.User;

/**
 * Created by akarpinska on 5/6/14.
 */
class ModelFactoryImpl implements ModelFactory {

    public User newUser(String fullName, String username, byte[] hashedPassword) {
        return new UserImpl(fullName, username, hashedPassword);
    }
}
