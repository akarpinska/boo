package com.album.persistence.impl;

import com.album.persistence.api.PhotoDAO;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by akarpinska on 5/14/14.
 */
class PhotoDAOImpl extends JdbcDaoSupport implements PhotoDAO {

    private SimpleJdbcInsert insertQuery;

    public void initialize() {
        insertQuery = new SimpleJdbcInsert(getJdbcTemplate()).withTableName("photos").usingGeneratedKeyColumns("id");
    }

    public Integer saveNewPhoto(int albumId, byte[] sourceImage, byte[] smallImage) {
        Map parameters = new HashMap();
        parameters.put("album_id", albumId);
        parameters.put("file_data", sourceImage);
        parameters.put("preview_data", smallImage);
        Integer photoId = null;
        try {
            Number result = insertQuery.executeAndReturnKey(parameters);
            photoId = new Integer(result.intValue());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return photoId;
    }

    public List<Integer> loadPhotoIdsForAlbum(int albumId) {
        String sql = "SELECT id FROM photos WHERE album_id = ?";
        return getJdbcTemplate().queryForList(sql, new Object[]{albumId}, Integer.class);
    }

    public byte[] loadPhoto(int photoId) {
        String sql = "SELECT file_data FROM photos WHERE id = ?";
        byte[] photo = null;
        try {
            photo = getJdbcTemplate().queryForObject(sql,  new Object[]{photoId}, byte[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return photo;

    }

    public byte[] loadPreview(int photoId) {
        String sql = "SELECT preview_data FROM photos WHERE id = ?";
        byte[] preview = null;
        try {
            preview = getJdbcTemplate().queryForObject(sql,  new Object[]{photoId}, byte[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preview;
    }
}
