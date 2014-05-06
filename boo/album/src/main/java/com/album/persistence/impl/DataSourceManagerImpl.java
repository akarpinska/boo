package com.album.persistence.impl;

import com.album.model.api.ModelFactory;
import com.album.model.api.User;
import com.album.persistence.api.DataSourceManager;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.*;

/**
 * Created by akarpinska on 5/6/14.
 */
class DataSourceManagerImpl implements DataSourceManager {

    BasicDataSource dataSource;
    ModelFactory modelFactory;

    public DataSourceManagerImpl(BasicDataSource dataSource,
                                 ModelFactory modelFactory) {
        this.dataSource = dataSource;
        this.modelFactory = modelFactory;
        createTables();
    }

    public boolean saveNewUser(User user)
    {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement saveUserStmt = connection.prepareStatement(
                    "INSERT INTO users (userName, password, fullName) VALUES (?, ?, ?)");
            saveUserStmt.setString(1, user.getUsername());
            saveUserStmt.setBytes(2, user.getHashedPassword());
            saveUserStmt.setString(3, user.getFullName());
            return saveUserStmt.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User loadUser(String userName, byte[] hashedPassword) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement loadUserStmt = connection.prepareStatement(
                    "SELECT fullName FROM users where userName=? and password=?");
            loadUserStmt.setString(1, userName);
            loadUserStmt.setBytes(2, hashedPassword);
            ResultSet result = loadUserStmt.executeQuery();
            if (result.first() ) {
                String fullName = result.getString(1);
                return modelFactory.newUser(fullName, userName, hashedPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createTables() {
        try {
            Connection connection = dataSource.getConnection();
            Statement createTableStmt = connection.createStatement();
            createTableStmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "userName VARCHAR(128) NOT NULL UNIQUE, " +
                    "password BLOB NOT NULL, " +
                    "fullName VARCHAR(128) NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
