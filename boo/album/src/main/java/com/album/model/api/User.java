package com.album.model.api;

import java.util.Iterator;

/**
 * Created by akarpinska on 5/6/14.
 */
public interface User {

    public String getFullName();

    public String getUsername();

    public byte[] getHashedPassword();

    public Iterator<Album> browseAlbums();

    public void addAlbum(Album album);

    public Album getAlbum(String albumName);
}
