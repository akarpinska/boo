package com.album.model.impl;

import com.album.model.api.Album;
import com.album.model.api.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akarpinska on 5/6/14.
 */
class UserImpl implements User {

    private final String fullName;
    private final String username;
    private final byte[] hashedPassword;

    private List<Album> albums;

    public UserImpl(String fullName, String username, byte[] hashedPassword) {
        this.fullName = fullName;
        this.username = username;
        this.hashedPassword = hashedPassword;
        albums = new ArrayList<Album>();
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }
}
