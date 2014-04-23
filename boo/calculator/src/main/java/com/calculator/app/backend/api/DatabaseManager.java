package com.calculator.app.backend.api;

/**
 * Created by akarpinska on 4/24/14.
 */
public interface DatabaseManager {

    public boolean saveUser(User user);

    public User loadUser(String userName, String hashedPassword);

}
