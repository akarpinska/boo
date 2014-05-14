package com.album.persistence.impl;

import com.album.model.api.Album;
import com.album.model.api.ModelFactory;
import com.album.model.api.Photo;
import com.album.persistence.api.PhotoDAO;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by akarpinska on 5/14/14.
 */
class PhotoDAOImpl extends JdbcDaoSupport implements PhotoDAO {

    ModelFactory modelFactory;

    public PhotoDAOImpl(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public Photo saveNewPhoto(Album album, String fileName, byte[] fileData) {
        String sql = "INSERT INTO photos (album_id, file_name, file_data, comment) " +
                "SELECT albums.id, ?, ?, ? " +
                "FROM albums INNER JOIN users ON albums.user_id = users.id " +
                "WHERE username = ? and albums.album_name = ?";

        int result;
        try {
            result = getJdbcTemplate().update(sql, fileName, fileData, "",
                    album.getUser().getUsername(), album.getAlbumName());
            if (result != 0)
                return modelFactory.newPhoto(fileName, fileData);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loadPhotos(Album album) {
        String sql = "SELECT file_name, file_data FROM photos " +
                "INNER JOIN albums ON album_id = albums.id " +
                "INNER JOIN users ON albums.user_id = users.id " +
                "WHERE users.username = ? and albums.album_name = ?";

        List<Map<String, Object>> photos = getJdbcTemplate().queryForList(sql,
                new Object[]{album.getUser().getUsername(), album.getAlbumName()});
        Iterator<Map<String, Object>> photosIt = photos.iterator();
        while (photosIt.hasNext()) {
            Map photo = photosIt.next();
            album.addPhoto(modelFactory.newPhoto((String) photo.get("file_name"), (byte[]) photo.get("file_data")));
        }
    }
}
