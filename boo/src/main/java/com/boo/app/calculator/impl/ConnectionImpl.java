package com.boo.app.calculator.impl;


import com.boo.app.calculator.api.Server;
import com.boo.app.calculator.exceptions.InvalidExpressionException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by anastasia on 4/4/14.
 */
class ConnectionImpl implements Runnable {

    private final Socket socket;
    private final Server server;
    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    public ConnectionImpl(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String expression = readExpression(in);

            // This timeout is used to demonstrate parallel processing of requests
            try {
                Thread.sleep(15000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String result = ExpressionEvaluatorImpl.evaluate(expression);
                out.writeBytes(expression + " = " + result + "\n");
            } catch (InvalidExpressionException e) {
                out.writeBytes(e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.onConnectionClosed();
    }

    private String readExpression(DataInputStream in) throws IOException {
        byte raw_data[] = new byte[100];
        in.read(raw_data);
        String str = new String(raw_data, UTF8_CHARSET);
        str = str.substring(0, str.indexOf("\n"));
        return str;
    }
}
