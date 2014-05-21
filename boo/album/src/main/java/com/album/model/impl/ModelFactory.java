package com.album.model.impl;

import com.album.model.api.Album;
import com.album.model.api.User;

/**
 * Created by akarpinska on 5/6/14.
 */
public class ModelFactory {

    static public User newUser(int id, String fullName, String username) {
        return new UserImpl(id, fullName, username);
    }

    static public Album newAlbum(int id, String albumName) {
        return new AlbumImpl(id, albumName);
    }

}
