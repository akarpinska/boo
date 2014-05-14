package com.album.model.api;

/**
 * Created by akarpinska on 5/6/14.
 */
public interface ModelFactory {

    public User newUser(String fullName, String username, byte[] hashedPassword);

    public Album newAlbum(String albumName, User user);

    public Photo newPhoto(String fileName, byte[] fileData);

}
