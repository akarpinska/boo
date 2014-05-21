package com.album.persistence.api;

import com.album.model.api.Album;

import java.util.List;

/**
 * Created by akarpinska on 5/12/14.
 */
public interface AlbumDAO {

    public Album saveNewAlbum(int userId, String albumName);

    public Album loadAlbum(int userId, String albumName);

    public List<String> loadUserAlbums(int userId);
}
