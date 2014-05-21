package com.album.persistence.impl;

import com.album.model.api.Album;
import com.album.model.impl.ModelFactory;
import com.album.persistence.api.AlbumDAO;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by akarpinska on 5/6/14.
 */
class AlbumDAOImpl extends JdbcDaoSupport implements AlbumDAO {

    private SimpleJdbcInsert insertQuery;

    public void initialize() {
        insertQuery = new SimpleJdbcInsert(getJdbcTemplate()).withTableName("albums").usingGeneratedKeyColumns("id");
    }

    public Album saveNewAlbum(int userId, String albumName)
    {
        Map parameters = new HashMap();
        parameters.put("user_id", userId);
        parameters.put("album_name", albumName);
        Album newAlbum = null;
        try {
            Number albumId = insertQuery.executeAndReturnKey(parameters);
            newAlbum = ModelFactory.newAlbum(albumId.intValue(), albumName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return newAlbum;
    }

    public List<String> loadUserAlbums(int userId) {
        String sql = "SELECT album_name FROM albums WHERE user_id = ?";
        return getJdbcTemplate().queryForList(sql, new Object[]{userId}, String.class);
    }

    public Album loadAlbum(int userId, String albumName) {
        String sql = "SELECT id FROM albums where user_id=? and album_name=?";

        List<Number> result = getJdbcTemplate().queryForList(sql,
                new Object[]{userId, albumName}, Number.class);
        Album newAlbum = null;
        if (result.size() == 1) {
            int albumId = result.get(0).intValue();
            newAlbum = ModelFactory.newAlbum(albumId, albumName);
        }
        return newAlbum;
    }
}
