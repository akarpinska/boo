package com.calculator.app.http_server.api;

/**
 * Created by akarpinska on 4/17/14.
 */
public interface HttpRequest {

    public enum RequestType {
        GET,
        POST
    };

    public RequestType getType();

    public String getData(String key);

    public String getRequestedFile();
}
