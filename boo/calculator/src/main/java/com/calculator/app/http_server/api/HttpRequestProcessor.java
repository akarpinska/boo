package com.calculator.app.http_server.api;

/**
 * Created by akarpinska on 4/22/14.
 */
public interface HttpRequestProcessor {

    public void onGet(HttpRequest httpRequest);

    public void onPost(HttpRequest httpRequest);
}
