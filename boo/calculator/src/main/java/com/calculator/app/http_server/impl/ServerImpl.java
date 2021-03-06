package com.calculator.app.http_server.impl;

import com.calculator.app.http_server.api.BackendFactory;
import com.calculator.app.http_server.api.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by anastasia on 4/4/14.
 */
class ServerImpl extends Thread implements Server {

    private ServerSocket serverSocket = null;
    private BackendFactory backendFactory;
    private ExecutorService connections;
    private int port;


    public ServerImpl(int maxUsersNumber, int port, BackendFactory backendFactory) {
        connections = Executors.newFixedThreadPool(maxUsersNumber);
        this.port = port;
        this.backendFactory = backendFactory;
    }

    public void run() {
        while (!connections.isShutdown()) {
            try {
                Socket socket = serverSocket.accept();
                HttpSocketHandlerImpl connection = new HttpSocketHandlerImpl(socket, backendFactory);
                connections.execute(connection);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startServer() throws IOException {
        serverSocket = new ServerSocket(port);
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
