package com.calculator.app.http_server.api;

/**
 * Created by akarpinska on 4/24/14.
 */
public interface BackendFactory {

    public HttpRequestProcessor newRequestProcessor(HttpSocketHandler socketHandler, String pathToFile);
}