package com.calculator.app.backend.impl;

import com.calculator.app.backend.api.User;
import com.calculator.app.http_server.api.HttpRequest;

/**
 * Created by akarpinska on 4/22/14.
 */
class LoginPageImpl extends PageProcessorImpl {

    public LoginPageImpl() {
        super("/");
    }

    public void onGet(HttpRequest httpRequest) {
        httpSocketHandler.sendFile("html/index.html");
    }

    public void onPost(HttpRequest httpRequest) {
        String userName = httpRequest.getData("user");
        String password = httpRequest.getData("password");
        String hashedPassword = Utils.getMd5Hash(password);
        User user = getDatabaseManager().loadUser(userName, hashedPassword);
        if (user != null)
            httpSocketHandler.sendFile("html/welcome.html");
        else
            httpSocketHandler.sendFile("html/login_failure.html");
    }
}
