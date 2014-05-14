package com.album.persistence.api;

import com.album.model.api.User;

/**
 * Created by akarpinska on 5/6/14.
 */
public interface UserDAO {

    public User saveNewUser(String fullName, String userName, byte[] hashedPassword);

    public User loadUser(String userName, byte[] hashedPassword);
}
