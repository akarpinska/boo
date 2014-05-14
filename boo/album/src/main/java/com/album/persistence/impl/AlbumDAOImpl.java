package com.album.persistence.impl;

import com.album.model.api.Album;
import com.album.model.api.ModelFactory;
import com.album.model.api.User;
import com.album.persistence.api.AlbumDAO;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.Iterator;
import java.util.List;

/**
 * Created by akarpinska on 5/6/14.
 */
class AlbumDAOImpl extends JdbcDaoSupport implements AlbumDAO {

    ModelFactory modelFactory;

    public AlbumDAOImpl(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public Album saveNewAlbum(User user, String albumName)
    {
        String sql = "INSERT INTO albums (user_id, album_name) SELECT id, ? FROM users WHERE username = ?";

        int result;
        try {
            result = getJdbcTemplate().update(sql, albumName, user.getUsername());
            if (result != 0)
                return modelFactory.newAlbum(albumName, user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loadAlbums(User user) {
        String sql = "SELECT album_name FROM albums" +
                " INNER JOIN users ON albums.user_id = users.id WHERE users.username = ?";

        List<String> albumNames = getJdbcTemplate().queryForList(sql, new Object[]{user.getUsername()}, String.class);
        Iterator<String> albumNamesIt = albumNames.iterator();
        while (albumNamesIt.hasNext()) {
            user.addAlbum(modelFactory.newAlbum(albumNamesIt.next(), user));
        }
    }
}
