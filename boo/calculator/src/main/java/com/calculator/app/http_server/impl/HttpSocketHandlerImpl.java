package com.calculator.app.http_server.impl;

import com.calculator.app.http_server.api.BackendFactory;
import com.calculator.app.http_server.api.HttpRequest;
import com.calculator.app.http_server.api.HttpRequestProcessor;
import com.calculator.app.http_server.api.HttpSocketHandler;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.Date;

/**
 * Created by anastasia on 4/4/14.
 */
class HttpSocketHandlerImpl implements Runnable, HttpSocketHandler {

    private final Socket socket;
    private BackendFactory backendFactory;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public HttpSocketHandlerImpl(Socket socket, BackendFactory backendFactory) {
        this.socket = socket;
        this.backendFactory = backendFactory;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            HttpRequest request = HttpRequestParserImpl.parseHttpRequest(in);
            if (request != null) {
                HttpRequestProcessor requestProcessor =
                        backendFactory.newRequestProcessor(this, request.getRequestedFile());
                if (request.getType().equals(HttpRequest.RequestType.GET))
                    requestProcessor.onGet(request);
                else if (request.getType().equals(HttpRequest.RequestType.POST))
                    requestProcessor.onPost(request);
            }
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String path) {
        try {
            File file = new File(ClassLoader.getSystemResource(path).toURI());
            sendHttpHeader(file.length());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        BufferedReader fileStream = new BufferedReader(new InputStreamReader(
                ClassLoader.getSystemResourceAsStream(path)));
        String line;
        try {
            while ((line = fileStream.readLine()) != null) {
                out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.flush();
    }

    public void sendHeader(String header) {
        out.println(header);
        out.flush();
    }

    private void sendHttpHeader(long contentLength) {
        out.println("HTTP/1.0 200 OK");
        out.println("Server: Java HTTP Server 1.0");
        out.println("Date: " + new Date());
        out.println("Content-type: text/html");
        out.println("Content-length: " + contentLength);
        out.println();
        out.flush();
    }
}
