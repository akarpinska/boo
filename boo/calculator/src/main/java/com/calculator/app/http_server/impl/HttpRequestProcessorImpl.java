package com.calculator.app.http_server.impl;

import com.calculator.app.http_server.api.HttpRequestProcessor;
import com.calculator.app.http_server.api.HttpSocketHandler;

/**
 * Created by akarpinska on 4/22/14.
 */
abstract class HttpRequestProcessorImpl implements HttpRequestProcessor {

    protected HttpSocketHandler httpSocketHandler;

    public HttpRequestProcessorImpl(HttpSocketHandler httpSocketHandler) {
        this.httpSocketHandler = httpSocketHandler;
    }
}
