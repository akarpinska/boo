package com.album.persistence.api;

import com.album.model.api.Album;
import com.album.model.api.Photo;

/**
 * Created by akarpinska on 5/12/14.
 */
public interface PhotoDAO {

    public Photo saveNewPhoto(Album album, String fileName, byte[] fileData);

    public void loadPhotos(Album album);
}
