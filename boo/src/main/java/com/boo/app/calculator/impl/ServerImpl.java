package com.boo.app.calculator.impl;

import com.boo.app.calculator.api.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by anastasia on 4/4/14.
 */
class ServerImpl extends Thread implements Server {

    private static final int DefaultPort = 2353;

    private ServerSocket serverSocket = null;
    private ExecutorService connections;
    private int maxUsersNumber = 5;
    private int runningTasks = 0;
    private boolean isRunning = false;

    public ServerImpl() {
        connections = Executors.newCachedThreadPool();
    }

    public void setMaxUsersNumber(int maxUsers) {
        maxUsersNumber = maxUsers;
    }

    public int getMaxUsersNumber() {
        return maxUsersNumber;
    }

    public void run() {
        isRunning = true;
        while (isRunning) {
            try {
                if (runningTasks < maxUsersNumber) {
                    synchronized (this) {
                        runningTasks++;
                    }
                    Socket server = serverSocket.accept();
                    ConnectionImpl connection = new ConnectionImpl(server, this);
                    connections.execute(connection);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void onConnectionClosed() {
        runningTasks--;
    }

    public void startServer() throws IOException {
        serverSocket = new ServerSocket(DefaultPort);
        start();
    }

    public void stopServer() {
        isRunning = false;
        while (runningTasks > 0) {
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
}
