package com.calculator.app.http_server.impl;

import com.calculator.app.http_server.api.HttpRequest;
import com.calculator.app.http_server.api.HttpSocketHandler;

/**
 * Created by akarpinska on 4/22/14.
 */
public class LoginPageImpl extends HttpRequestProcessorImpl {

    public LoginPageImpl(HttpSocketHandler httpSocketHandler) {
        super(httpSocketHandler);
    }

    public void onGet(HttpRequest httpRequest) {
        httpSocketHandler.sendFile("html/index.html");
    }

    public void onPost(HttpRequest httpRequest) {
        httpSocketHandler.sendFile("html/index.html");
    }
}
