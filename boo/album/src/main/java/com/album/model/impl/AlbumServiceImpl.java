package com.album.model.impl;

import com.album.model.api.AlbumService;
import com.album.model.api.ModelFactory;
import com.album.model.api.User;
import com.album.persistence.api.DataSourceManager;
import com.album.utils.Utils;

/**
 * Created by akarpinska on 5/6/14.
 */
class AlbumServiceImpl implements AlbumService {

    DataSourceManager dataSourceManager;
    ModelFactory modelFactory;

    public AlbumServiceImpl(DataSourceManager dataSourceManager,
                            ModelFactory modelFactory) {
        this.dataSourceManager = dataSourceManager;
        this.modelFactory = modelFactory;
    }

    public User registerNewUser(String fullName, String username, String password) {
        User user = modelFactory.newUser(fullName, username, Utils.getMd5Hash(password));
        if (dataSourceManager.saveNewUser(user) == false)
            user = null;
        return user;
    }

    public User findUser(String username, String password) {
        return dataSourceManager.loadUser(username, Utils.getMd5Hash(password));
    }
}
