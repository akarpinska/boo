package com.album.persistence.impl;

import com.album.model.api.ModelFactory;
import com.album.model.api.User;
import com.album.persistence.api.DataSourceManager;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by akarpinska on 5/6/14.
 */
class DataSourceManagerImpl implements DataSourceManager {

    DataSource dataSource;
    ModelFactory modelFactory;

    public DataSourceManagerImpl(DataSource dataSource,
                                 ModelFactory modelFactory) {
        this.dataSource = dataSource;
        this.modelFactory = modelFactory;
    }

    public boolean saveNewUser(User user)
    {
        String sql = "INSERT INTO users (userName, password, fullName) VALUES (?, ?, ?)";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = jdbcTemplate.update(sql,
                new Object[] {user.getUsername(),user.getHashedPassword(), user.getFullName()});

        return result != 0;
    }

    public User loadUser(String userName, byte[] hashedPassword) {
        String sql = "SELECT fullName FROM users where userName=? and password=?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<String> result = jdbcTemplate.queryForList(sql, new Object[]{userName, hashedPassword}, String.class);
        if (result.size() == 1) {
            return modelFactory.newUser(result.get(0), userName, hashedPassword);
        }
        return null;
    }

}
