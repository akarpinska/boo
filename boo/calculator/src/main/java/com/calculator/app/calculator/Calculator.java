package com.calculator.app.calculator;

import com.calculator.app.calculator.api.Server;
import com.calculator.app.calculator.impl.CalculatorFactory;

import java.io.IOException;

/**
 * Created by anastasia on 4/4/14.
 */
public class Calculator {

    public void run() {

        try {
            Server server = CalculatorFactory.newServer();
            server.startServer();
            // This timeout is used to demonstrate how server waits for the running tasks before close a socket
            //try {
            //    Thread.sleep(5000);
            //} catch (Exception e) {
            //   System.out.println(e);
            //}
            //server.stopServer();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
}
