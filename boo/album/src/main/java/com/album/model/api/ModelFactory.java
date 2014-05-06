package com.album.model.api;

/**
 * Created by akarpinska on 5/6/14.
 */
public interface ModelFactory {

    public User newUser(String fullName, String username, byte[] hashedPassword);
}
