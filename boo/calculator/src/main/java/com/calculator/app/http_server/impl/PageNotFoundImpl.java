package com.calculator.app.http_server.impl;

import com.calculator.app.http_server.api.HttpRequest;
import com.calculator.app.http_server.api.HttpSocketHandler;

/**
 * Created by akarpinska on 4/22/14.
 */
public class PageNotFoundImpl extends HttpRequestProcessorImpl {

    public PageNotFoundImpl(HttpSocketHandler httpSocketHandler) {
        super(httpSocketHandler);
    }

    public void onGet(HttpRequest httpRequest) {
        httpSocketHandler.sendHeader("HTTP/1.0 404 Not Found");
    }

    public void onPost(HttpRequest httpRequest) {
        httpSocketHandler.sendHeader("HTTP/1.0 404 Not Found");
    }
}
