package com.album.model.api;

/**
 * Created by akarpinska on 5/6/14.
 */
public interface AlbumService {

    public User registerNewUser(String fullName, String username, String password);

    public User findUser(String username, String password);

}
