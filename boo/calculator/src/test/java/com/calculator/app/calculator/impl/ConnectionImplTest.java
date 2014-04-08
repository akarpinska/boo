package com.calculator.app.calculator.impl;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.lang.reflect.Method;
import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by akarpinska on 4/7/14.
 */
public class ConnectionImplTest {

    Socket mockedSocket;

    @BeforeClass
    public void setUp() {
        mockedSocket = mock(Socket.class);
    }

    @Test
    public void testExpressionReader() throws Exception {
        ConnectionImpl connection = new ConnectionImpl(null);
        Method readExpression = ConnectionImpl.class.getDeclaredMethod("readExpression", DataInputStream.class );
        readExpression.setAccessible(true);

        String expected = "2+2";
        DataInputStream input = createInputStream(expected);
        String result = (String)readExpression.invoke(connection, input);

        Assert.assertEquals(result, expected);
    }

    @Test
    public void testConnectionReadsExpressionAndReturnsResult() throws Exception {
        String expression = "2+2*3";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        when(mockedSocket.getInputStream()).thenReturn(createInputStream(expression));
        when(mockedSocket.getOutputStream()).thenReturn(createOutputStream(output));
        ConnectionImpl connection = new ConnectionImpl(mockedSocket);
        connection.run();

        String expected = "8.0\n";
        Assert.assertEquals(output.toString(), expected);
    }

    @Test
    public void testConnectionInvalidExpression() throws Exception {
        String expression = "abc";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        when(mockedSocket.getInputStream()).thenReturn(createInputStream(expression));
        when(mockedSocket.getOutputStream()).thenReturn(createOutputStream(output));
        ConnectionImpl connection = new ConnectionImpl(mockedSocket);
        connection.run();

        String expected = "Invalid expression " + expression + ".\n";
        Assert.assertEquals(output.toString(), expected);
    }

    private DataInputStream createInputStream(String data) {
        return new DataInputStream(new ByteArrayInputStream((data + "\n").getBytes()));
    }

    private DataOutputStream createOutputStream(ByteArrayOutputStream out) {
        return new DataOutputStream(out);
    }
}
