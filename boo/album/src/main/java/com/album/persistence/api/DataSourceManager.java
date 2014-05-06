package com.album.persistence.api;

import com.album.model.api.User;

/**
 * Created by akarpinska on 5/6/14.
 */
public interface DataSourceManager {

    public boolean saveNewUser(User user);

    public User loadUser(String userName, byte[] hashedPassword);
}
