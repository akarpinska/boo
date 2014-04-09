package com.calculator.app.calculator;

import com.calculator.app.calculator.api.Server;
import com.calculator.app.calculator.impl.CalculatorFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by anastasia on 4/4/14.
 */
public class Calculator {

    private static final String pathToProperties = "/config.properties";
    private static final String maxUsersNumberProperty = "maxUsersNumber";

    private Properties properties;

    public Calculator() {
        properties = readProperties();
    }

    public void run() {

        try {
            int maxUsersNumber = Integer.valueOf(properties.getProperty(maxUsersNumberProperty, "10"));
            Server server = CalculatorFactory.newServer(maxUsersNumber);
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

    private Properties readProperties() {
        Properties properties = new Properties();
        InputStream input = getClass().getResourceAsStream(pathToProperties);
        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }
}
