package com.calculator.app.http_server.api;

/**
 * Created by akarpinska on 4/22/14.
 */
public interface HttpSocketHandler {

    public void sendFile(String path);

    public void sendHeader(String header);
}
