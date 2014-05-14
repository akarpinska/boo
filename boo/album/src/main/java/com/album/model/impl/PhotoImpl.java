package com.album.model.impl;

import com.album.model.api.Photo;

/**
 * Created by akarpinska on 5/14/14.
 */
class PhotoImpl implements Photo {

    private final String fileName;
    private byte[] fileData;

    public PhotoImpl(String fileName, byte[] fileData) {
        this.fileName = fileName;
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getData() {
        return fileData;
    }
}
