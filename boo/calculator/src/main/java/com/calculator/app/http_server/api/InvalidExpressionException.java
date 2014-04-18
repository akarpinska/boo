package com.calculator.app.http_server.api;

/**
 * Created by anastasia on 4/6/14.
 */
public class InvalidExpressionException extends Exception {

    public InvalidExpressionException(String expression) {
        super("Invalid expression " + expression + ".\n");
    }
}
