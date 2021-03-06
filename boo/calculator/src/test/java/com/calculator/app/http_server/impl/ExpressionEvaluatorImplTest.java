package com.calculator.app.http_server.impl;

import com.calculator.app.http_server.api.InvalidExpressionException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by akarpinska on 4/7/14.
 */
public class ExpressionEvaluatorImplTest {

    @Test
    public void evaluateExpression() {
        ExpressionEvaluatorImpl evaluator = new ExpressionEvaluatorImpl();
        String result = null;
        try {
            result = evaluator.evaluate("2+2*2");
        }
        catch (InvalidExpressionException e) {}
        finally {
            Assert.assertEquals(result, "6.0");
        }
    }

    @Test(expectedExceptions = InvalidExpressionException.class)
    public void invalidExpression() throws InvalidExpressionException {
        ExpressionEvaluatorImpl evaluator = new ExpressionEvaluatorImpl();
        evaluator.evaluate("abc");
    }
}
