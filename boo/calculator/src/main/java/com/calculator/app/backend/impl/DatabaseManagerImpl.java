package com.calculator.app.backend.impl;

import com.calculator.app.backend.api.DatabaseManager;
import com.calculator.app.backend.api.User;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.*;

/**
 * Created by akarpinska on 4/24/14.
 */
public class DatabaseManagerImpl implements DatabaseManager {

    private DriverManagerDataSource dataSource;

    DatabaseManagerImpl(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
        createTables();
    }

    private synchronized Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private void createTables() {
        try {
            Connection connection = getConnection();
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

    public boolean saveUser(User user) {
        try {
            Connection connection = getConnection();
            PreparedStatement saveUserStmt = connection.prepareStatement(
                    "INSERT INTO users (userName, password, fullName) VALUES (?, ?, ?)");
            saveUserStmt.setString(1, user.getUserName());
            saveUserStmt.setBytes(2, user.getPasswordHash().getBytes());
            saveUserStmt.setString(3, user.getFullName());
            return saveUserStmt.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User loadUser(String userName, String hashedPassword) {
        try {
            Connection connection = getConnection();
            PreparedStatement loadUserStmt = connection.prepareStatement(
                    "SELECT fullName FROM users where userName=? and password=?");
            loadUserStmt.setString(1, userName);
            loadUserStmt.setBytes(2, hashedPassword.getBytes());
            ResultSet result = loadUserStmt.executeQuery();
            if (result.first() ) {
                String fullName = result.getString(1);
                return new UserImpl(fullName, userName, hashedPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
