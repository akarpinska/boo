package com.boo.app.calculator.api;

import java.io.IOException;

/**
 * Created by anastasia on 4/4/14.
 */
public interface Server {

    public void setMaxUsersNumber(int maxUsers);

    public int getMaxUsersNumber();

    public void onConnectionClosed();

    public void startServer() throws IOException;

    public void stopServer();
}
