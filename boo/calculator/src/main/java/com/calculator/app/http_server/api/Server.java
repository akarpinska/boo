package com.calculator.app.http_server.api;

import java.io.IOException;

/**
 * Created by anastasia on 4/4/14.
 */
public interface Server {

    public void startServer() throws IOException;

    public void stopServer();
}
