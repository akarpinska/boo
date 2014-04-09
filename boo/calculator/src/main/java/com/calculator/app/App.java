package com.calculator.app;

import com.calculator.app.calculator.api.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Task 1: Given the list of phrases (in the form of text file with a phrase for a line) you need to count and output
 * the number of times each n-gram is used among all the phrases’ words. The results should be sorted by the number
 * of usage in the descending order and printed to stdout.
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
        Server server = (Server) context.getBean("createServerInstance");
        try {
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
