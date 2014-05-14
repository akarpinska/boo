package com.album.model.api;

/**
 * Created by akarpinska on 5/6/14.
 */
public interface AlbumService {

    public User registerNewUser(String fullName, String username, String password);

    public User loadUser(String username, String password);

    public Album createNewAlbum(User user, String albumName);

    public void loadAlbums(User user);

    public Photo savePhoto(Album album, String fileName, byte[] fileBytes);

    public void loadPhotos(Album album);

}
