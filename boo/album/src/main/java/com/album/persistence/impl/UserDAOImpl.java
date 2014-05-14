package com.album.persistence.impl;

import com.album.model.api.ModelFactory;
import com.album.model.api.User;
import com.album.persistence.api.UserDAO;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * Created by akarpinska on 5/6/14.
 */
class UserDAOImpl extends JdbcDaoSupport implements UserDAO {

    ModelFactory modelFactory;

    public UserDAOImpl(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public User saveNewUser(String fullName, String userName, byte[] hashedPassword)
    {
        String sql = "INSERT INTO users (userName, password, fullName) VALUES (?, ?, ?)";

        int result = getJdbcTemplate().update(sql,
                new Object[] {userName, hashedPassword, fullName});

        if (result != 0)
            return modelFactory.newUser(fullName, userName, hashedPassword);

        return null;
    }

    public User loadUser(String userName, byte[] hashedPassword) {
        String sql = "SELECT fullName FROM users where userName=? and password=?";

        List<String> result = getJdbcTemplate().queryForList(sql, new Object[]{userName, hashedPassword}, String.class);
        if (result.size() == 1) {
            return modelFactory.newUser(result.get(0), userName, hashedPassword);
        }
        return null;
    }

}
