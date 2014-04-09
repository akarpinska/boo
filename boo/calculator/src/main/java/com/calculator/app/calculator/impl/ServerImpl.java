package com.calculator.app.calculator.impl;

import com.calculator.app.calculator.api.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by anastasia on 4/4/14.
 */
class ServerImpl extends Thread implements Server {

    private static final int defaultPort = 2353;

    private ServerSocket serverSocket = null;
    private ExecutorService connections;


    public ServerImpl(int maxUsersNumber) {
        connections = Executors.newFixedThreadPool(maxUsersNumber);
    }

    public void run() {
        while (!connections.isShutdown()) {
            try {
                Socket socket = serverSocket.accept();
                SocketHandlerImpl connection = new SocketHandlerImpl(socket);
                connections.execute(connection);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startServer() throws IOException {
        serverSocket = new ServerSocket(defaultPort);
        start();
    }

    public void stopServer() {
        connections.shutdown();
        while (!connections.isTerminated()) {
            try {
                sleep(20);
            } catch (InterruptedException e) {
                break;
            }
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serverSocket = null;
        }
    }
}
