package com.calculator.app.backend.impl;

import com.calculator.app.backend.api.User;
import com.calculator.app.http_server.api.HttpRequest;

/**
 * Created by akarpinska on 4/22/14.
 */
class RegisterPageImpl extends PageProcessorImpl {

    public RegisterPageImpl() { super("/register.html"); }

    public void onGet(HttpRequest httpRequest) {
        httpSocketHandler.sendFile("html/register.html");
    }

    public void onPost(HttpRequest httpRequest) {
        String userName = httpRequest.getData("user");
        String password = httpRequest.getData("password");
        String fullName = httpRequest.getData("full_name");
        String hashedPassword = Utils.getMd5Hash(password);

        User user = new UserImpl(fullName, userName, hashedPassword);
        if (getDatabaseManager().saveUser(user))
            httpSocketHandler.sendFile("html/registration_success.html");
        else
            httpSocketHandler.sendFile("html/registration_failure.html");
    }
}
