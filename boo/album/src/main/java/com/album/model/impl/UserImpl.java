package com.album.model.impl;

import com.album.model.api.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akarpinska on 5/6/14.
 */
class UserImpl implements User {

    private final int id;
    private final String fullName;
    private final String username;

    private List<String> albumsNames;

    public UserImpl(int id, String fullName, String username) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        albumsNames = new ArrayList<String>();
    }

    public int getUserId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getAlbumsNames() {
        return albumsNames;
    }

    public void addAlbumName(String albumName) {
        albumsNames.add(albumName);
    }
}
