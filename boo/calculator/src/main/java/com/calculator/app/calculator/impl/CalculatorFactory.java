package com.calculator.app.calculator.impl;

import com.calculator.app.calculator.api.Server;

/**
 * Created by anastasia on 4/6/14.
 */
public class CalculatorFactory {

    public static Server newServer() {
        return new ServerImpl();
    }
}
