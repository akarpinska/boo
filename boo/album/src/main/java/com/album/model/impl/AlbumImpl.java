package com.album.model.impl;

import com.album.model.api.Album;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akarpinska on 5/13/14.
 */
class AlbumImpl implements Album {

    private final int id;
    private String albumName;
    private List<Integer> photoIds;

    public AlbumImpl(int id, String albumName) {
        this.id = id;
        this.albumName = albumName;
        photoIds = new ArrayList<Integer>();
    }

    public int getAlbumId() {
        return id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public List<Integer> getPhotoIds() {
        return photoIds;
    }

    public void addPhotoId(Integer photoId) {
        photoIds.add(photoId);
    }

}
