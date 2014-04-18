package com.calculator.app.http_server.impl;

import com.calculator.app.http_server.api.HttpRequest;
import com.calculator.app.http_server.api.HttpRequestProcessor;
import com.calculator.app.http_server.api.HttpSocketHandler;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by anastasia on 4/4/14.
 */
class HttpSocketHandlerImpl implements Runnable, HttpSocketHandler {

    private final Socket socket;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public HttpSocketHandlerImpl(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            String input = in.readLine();
            if (input != null) {

                StringTokenizer parse = new StringTokenizer(input);
                String method = parse.nextToken().toUpperCase();
                String requestedFile = parse.nextToken().toLowerCase();

                if (method.equals("GET")) {
                    HttpRequest request = createGetRequest(requestedFile);
                    HttpRequestProcessor requestProcessor = getRequestProcessor(request.getRequestedFile());
                    requestProcessor.onGet(request);
                }
                if (method.equals("POST")) {
                    HttpRequest request = createPostRequest(requestedFile);
                    HttpRequestProcessor requestProcessor = getRequestProcessor(request.getRequestedFile());
                    requestProcessor.onPost(request);
                }
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

    private HttpRequestProcessor getRequestProcessor(String requestedFile) {
        if (requestedFile.equals("/"))
            return new LoginPageImpl(this);
        else if (requestedFile.equals("/register.html"))
            return new RegisterPageImpl(this);
        return new PageNotFoundImpl(this);
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

    private HttpRequest createGetRequest(String requestedFile) {
        String parameters[] = requestedFile.split("\\?", 2);
        HttpRequestImpl request = new HttpRequestImpl(HttpRequest.RequestType.GET, parameters[0]);
        if (parameters.length == 2)
            parseParameters(parameters[1], request);

        try {
            while (!in.readLine().equals("")) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    private HttpRequest createPostRequest(String requestedFile) {
        HttpRequestImpl request = new HttpRequestImpl(HttpRequest.RequestType.GET, requestedFile);

        try {
            String data;
            int contentLength = 0;
            while (!(data = in.readLine()).equals("")) {
                String parameter[] = data.split(": ", 2);
                if (parameter[0].equals("Content-Length")) {
                    contentLength = Integer.parseInt(parameter[1]);
                }
            }

            if (contentLength > 0) {
                byte content[] = new byte[contentLength];
                for (int i = 0; i < contentLength; ++i) {
                    content[i] = (byte)in.read();
                }
                parseParameters(new String(content), request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return request;
    }

    private void parseParameters(String parameters, HttpRequestImpl request) {
        String values[] = parameters.split("&");
        for (int i = 0; i < values.length; ++i) {
            String parameter[] = values[i].split("=", 2);
            request.setData(parameter[0], parameter[1]);
        }
    }
}
