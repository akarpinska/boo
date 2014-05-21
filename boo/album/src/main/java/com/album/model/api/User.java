package com.album.model.api;

import java.util.List;

/**
 * Created by akarpinska on 5/6/14.
 */
public interface User {

    public int getUserId();

    public String getFullName();

    public String getUsername();

    public List<String> getAlbumsNames();

    public void addAlbumName(String albumName);
}
