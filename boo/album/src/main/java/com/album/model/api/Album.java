package com.album.model.api;

import java.util.List;

/**
 * Created by akarpinska on 5/12/14.
 */
public interface Album {

    public int getAlbumId();

    public String getAlbumName();

    public List<Integer> getPhotoIds();

    public void addPhotoId(Integer photoId);

}
