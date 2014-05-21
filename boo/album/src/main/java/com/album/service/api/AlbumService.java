package com.album.service.api;

import com.album.model.api.Album;
import com.album.model.api.User;

/**
 * Created by akarpinska on 5/6/14.
 */
public interface AlbumService {

    public User registerNewUser(String fullName, String username, String password);

    public User loadUser(String username, String password);

    public Album createNewAlbum(User user, String albumName);

    public Album loadAlbum(User user, String albumName);

    public Integer savePhoto(Album album, String fileName, byte[] fileBytes);

    public byte[] loadPhoto(int photoId);

    public byte[] loadPreview(int photoId);
}
