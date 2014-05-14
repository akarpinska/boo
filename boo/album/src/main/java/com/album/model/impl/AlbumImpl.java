package com.album.model.impl;

import com.album.model.api.Album;
import com.album.model.api.Photo;
import com.album.model.api.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by akarpinska on 5/13/14.
 */
class AlbumImpl implements Album {

    private String albumName;
    private final User user;
    protected Map<String, Photo> photos;

    public AlbumImpl(String albumName, User user) {
        this.albumName = albumName;
        this.user = user;
        photos = new HashMap<String, Photo>();
    }

    public String getAlbumName() {
        return albumName;
    }

    public Iterator<Photo> browsePhotos() {
        return photos.values().iterator();
    }

    public void addPhoto(Photo photo) {
        photos.put(photo.getFileName(), photo);
    }

    public User getUser() {
        return user;
    }

    public Photo getPhoto(String fileName) {
        if (photos.containsKey(fileName))
            return photos.get(fileName);
        return null;
    }
}
