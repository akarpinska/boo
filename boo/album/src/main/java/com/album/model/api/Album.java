package com.album.model.api;

import java.util.Iterator;

/**
 * Created by akarpinska on 5/12/14.
 */
public interface Album {

    public String getAlbumName();

    public Iterator<Photo> browsePhotos();

    public void addPhoto(Photo photo);

    public User getUser();

    public Photo getPhoto(String fileName);
}
