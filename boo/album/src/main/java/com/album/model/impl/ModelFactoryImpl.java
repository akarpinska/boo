package com.album.model.impl;

import com.album.model.api.Album;
import com.album.model.api.ModelFactory;
import com.album.model.api.Photo;
import com.album.model.api.User;

/**
 * Created by akarpinska on 5/6/14.
 */
class ModelFactoryImpl implements ModelFactory {

    public User newUser(String fullName, String username, byte[] hashedPassword) {
        return new UserImpl(fullName, username, hashedPassword);
    }

    public Album newAlbum(String albumName, User user) {
        return new AlbumImpl(albumName, user);
    }

    public Photo newPhoto(String fileName, byte[] fileData) {
        return new PhotoImpl(fileName, fileData);
    }

}
