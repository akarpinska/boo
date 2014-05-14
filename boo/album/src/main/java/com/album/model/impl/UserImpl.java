package com.album.model.impl;

import com.album.model.api.Album;
import com.album.model.api.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by akarpinska on 5/6/14.
 */
class UserImpl implements User {

    private final String fullName;
    private final String username;
    private final byte[] hashedPassword;

    private Map<String, Album> albums;

    public UserImpl(String fullName, String username, byte[] hashedPassword) {
        this.fullName = fullName;
        this.username = username;
        this.hashedPassword = hashedPassword;
        albums = new HashMap<String, Album>();
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

    public Iterator<Album> browseAlbums() {
        return albums.values().iterator();
    }

    public void addAlbum(Album album) {
        albums.put(album.getAlbumName(), album);
    }

    public Album getAlbum(String albumName) {
        if (albums.containsKey(albumName))
            return albums.get(albumName);
        return null;
    }
}
