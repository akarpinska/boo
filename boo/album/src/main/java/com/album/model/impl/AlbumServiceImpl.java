package com.album.model.impl;

import com.album.model.api.*;
import com.album.persistence.api.AlbumDAO;
import com.album.persistence.api.PhotoDAO;
import com.album.persistence.api.UserDAO;
import com.album.utils.Utils;

import java.util.Iterator;

/**
 * Created by akarpinska on 5/6/14.
 */
class AlbumServiceImpl implements AlbumService {

    UserDAO userDAO;
    AlbumDAO albumDAO;
    PhotoDAO photoDAO;

    public AlbumServiceImpl(UserDAO userDAO,
                            AlbumDAO albumDAO,
                            PhotoDAO photoDAO) {
        this.userDAO = userDAO;
        this.albumDAO = albumDAO;
        this.photoDAO = photoDAO;
    }

    public User registerNewUser(String fullName, String username, String password) {
        return userDAO.saveNewUser(fullName, username, Utils.getMd5Hash(password));
    }

    public User loadUser(String username, String password) {
        User user = userDAO.loadUser(username, Utils.getMd5Hash(password));
        if (user != null ) {
            loadAlbums(user);
        }
        return user;
    }

    public Album createNewAlbum(User user, String albumName) {
        Album newAlbum = albumDAO.saveNewAlbum(user, albumName);
        if (newAlbum != null)
            user.addAlbum(newAlbum);
        return newAlbum;
    }

    public void loadAlbums(User user) {
        albumDAO.loadAlbums(user);
        Iterator<Album> albumsIt = user.browseAlbums();
        while (albumsIt.hasNext()) {
            Album album = albumsIt.next();
            loadPhotos(album);
        }

    }

    public Photo savePhoto(Album album, String fileName, byte[] fileBytes) {
        Photo newPhoto = photoDAO.saveNewPhoto(album, fileName, fileBytes);
        if (newPhoto != null)
            album.addPhoto(newPhoto);
        return newPhoto;
    }

    public void loadPhotos(Album album) {
        photoDAO.loadPhotos(album);
    }
}
