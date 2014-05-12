package com.album.model.api;

import java.util.List;

/**
 * Created by akarpinska on 5/12/14.
 */
public interface Album {

    public String getName();

    public List<Photo> getPhotos();
}
