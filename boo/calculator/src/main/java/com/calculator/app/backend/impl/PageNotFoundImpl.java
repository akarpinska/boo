package com.calculator.app.backend.impl;

import com.calculator.app.http_server.api.HttpRequest;

/**
 * Created by akarpinska on 4/22/14.
 */
class PageNotFoundImpl extends PageProcessorImpl {

    public PageNotFoundImpl() {
        super("");
    }

    public void onGet(HttpRequest httpRequest) {
        httpSocketHandler.sendHeader("HTTP/1.0 404 Not Found");
    }

    public void onPost(HttpRequest httpRequest) {
        httpSocketHandler.sendHeader("HTTP/1.0 404 Not Found");
    }
}
