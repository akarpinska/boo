package com.calculator.app.calculator.impl;

import com.calculator.app.calculator.api.Server;

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
