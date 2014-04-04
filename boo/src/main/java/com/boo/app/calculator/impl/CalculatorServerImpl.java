package com.boo.app.calculator.impl;

import com.boo.app.calculator.api.Server;

import java.io.IOException;

/**
 * Created by anastasia on 4/6/14.
 */
public class CalculatorServerImpl {

    public static Server newServer() {
        return new ServerImpl();
    }
}
