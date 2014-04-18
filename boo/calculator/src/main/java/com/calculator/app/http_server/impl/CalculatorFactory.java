package com.calculator.app.http_server.impl;

import com.calculator.app.http_server.api.Server;

/**
 * Created by anastasia on 4/6/14.
 */
public class CalculatorFactory {

    private int maxUsersNumber = 10;
    private int port = 2555;

    public CalculatorFactory(int maxUsersNumber, int port) {
        this.maxUsersNumber = maxUsersNumber;
        this.port = port;
    }

    public Server newServer() {
        return new ServerImpl(maxUsersNumber, port);
    }
}
