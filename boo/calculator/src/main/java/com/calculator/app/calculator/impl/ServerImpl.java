package com.calculator.app.calculator.impl;

import com.calculator.app.calculator.api.Server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by anastasia on 4/4/14.
 */
class ServerImpl extends Thread implements Server {

    private static final int DefaultPort = 2353;
    private static final String PathToProperties = "/config.properties";
    private static final String MaxUsersNumberProperty = "maxUsersNumber";

    private ServerSocket serverSocket = null;
    private ExecutorService connections;
    private Properties properties;

    public ServerImpl() {
        properties = readProperties();
        int maxUsersNumber = Integer.valueOf(properties.getProperty(MaxUsersNumberProperty, "10"));
        connections = Executors.newFixedThreadPool(maxUsersNumber);
        System.out.print(maxUsersNumber);
    }

    public void run() {
        while (!connections.isShutdown()) {
            try {
                Socket socket = serverSocket.accept();
                SocketHandlerImpl connection = new SocketHandlerImpl(socket);
                connections.execute(connection);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startServer() throws IOException {
        serverSocket = new ServerSocket(DefaultPort);
        start();
    }

    public void stopServer() {
        connections.shutdown();
        while (!connections.isTerminated()) {
            try {
                sleep(20);
            }
            catch (InterruptedException e) {
                break;
            }
        }
        try {
            serverSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            serverSocket = null;
        }
    }

    private Properties readProperties() {
        Properties properties = new Properties();
        InputStream input = getClass().getResourceAsStream(PathToProperties);
        try {
            properties.load(input);
        } catch (IOException e)
        {
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
