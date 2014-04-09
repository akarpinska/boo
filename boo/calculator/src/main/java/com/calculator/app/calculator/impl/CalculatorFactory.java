package com.calculator.app.calculator.impl;

import com.calculator.app.calculator.api.Server;

import java.util.Properties;

/**
 * Created by anastasia on 4/6/14.
 */
public class CalculatorFactory {

    private static final String maxUsersNumberProperty = "maxUsersNumber";
    private static final String portProperty = "port";

    private Properties config;

    public CalculatorFactory(Properties config) {
        this.config = config;
    }

    public Server newServer() {
        int maxUsersNumber = Integer.valueOf(config.getProperty(maxUsersNumberProperty, "10"));
        int port = Integer.valueOf(config.getProperty(portProperty, "2525"));
        return new ServerImpl(maxUsersNumber, port);
    }
}
