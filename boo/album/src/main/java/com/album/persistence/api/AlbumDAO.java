package com.album.persistence.api;

import com.album.model.api.Album;
import com.album.model.api.User;

/**
 * Created by akarpinska on 5/12/14.
 */
public interface AlbumDAO {

    public Album saveNewAlbum(User user, String albumName);

    public void loadAlbums(User user);
}
