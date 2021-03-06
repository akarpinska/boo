package com.calculator.app.http_server.impl;

import com.calculator.app.http_server.api.InvalidExpressionException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by anastasia on 4/6/14.
 */
class ExpressionEvaluatorImpl {

    public static String evaluate(String expression) throws InvalidExpressionException {
        try {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            Object result = engine.eval(expression);
            return result.toString();
        } catch (ScriptException e) {
            throw new InvalidExpressionException(expression);
        }
    }
}
