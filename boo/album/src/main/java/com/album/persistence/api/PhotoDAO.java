package com.album.persistence.api;

import java.util.List;

/**
 * Created by akarpinska on 5/12/14.
 */
public interface PhotoDAO {

    public Integer saveNewPhoto(int albumId, byte[] sourceImage, byte[] smallImage);

    public List<Integer> loadPhotoIdsForAlbum(int albumId);

    public byte[] loadPhoto(int photoId);

    public byte[] loadPreview(int photoId);
}
