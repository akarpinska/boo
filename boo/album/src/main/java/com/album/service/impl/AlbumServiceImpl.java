package com.album.service.impl;

import com.album.model.api.Album;
import com.album.model.api.User;
import com.album.persistence.api.AlbumDAO;
import com.album.persistence.api.PhotoDAO;
import com.album.persistence.api.UserDAO;
import com.album.service.api.AlbumService;
import com.album.service.api.ImageProcessor;
import com.album.utils.Utils;

import java.util.Iterator;
import java.util.List;

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
        if (user != null) {
            List<String> albumsNames = albumDAO.loadUserAlbums(user.getUserId());
            Iterator<String> albumNamesIt = albumsNames.iterator();
            while (albumNamesIt.hasNext())
                user.addAlbumName(albumNamesIt.next());
        }
        return user;
    }

    public Album createNewAlbum(User user, String albumName) {
        Album newAlbum = albumDAO.saveNewAlbum(user.getUserId(), albumName);
        if (newAlbum != null)
            user.addAlbumName(newAlbum.getAlbumName());
        return newAlbum;
    }

    public Album loadAlbum(User user, String albumName) {
        Album album = albumDAO.loadAlbum(user.getUserId(), albumName);
        if (album != null) {
            List<Integer> photoIds = photoDAO.loadPhotoIdsForAlbum(album.getAlbumId());
            Iterator<Integer> photosIdsIt = photoIds.iterator();
            while (photosIdsIt.hasNext())
                album.addPhotoId(photosIdsIt.next());
        }
        return album;
    }

    public Integer savePhoto(Album album, String fileName, byte[] fileBytes) {
        byte[] preview = ImageProcessor.createImagePreview(fileBytes);
        if (preview == null)
            return null;
        Integer newPhotoId = photoDAO.saveNewPhoto(album.getAlbumId(), fileBytes, preview);
        if (newPhotoId != null)
            album.addPhotoId(newPhotoId);
        return newPhotoId;
    }

    public byte[] loadPhoto(int photoId) {
        return photoDAO.loadPhoto(photoId);
    }

    public byte[] loadPreview(int photoId) {
        return photoDAO.loadPreview(photoId);
    }

}
