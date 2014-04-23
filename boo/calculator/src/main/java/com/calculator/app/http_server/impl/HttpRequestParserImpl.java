package com.calculator.app.http_server.impl;

import com.calculator.app.http_server.api.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by akarpinska on 4/24/14.
 */
class HttpRequestParserImpl {

    public static HttpRequest parseHttpRequest(BufferedReader in) {
        String input = null;
        try {
            input = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (input == null)
            return null;

        StringTokenizer parse = new StringTokenizer(input);
        String method = parse.nextToken().toUpperCase();
        String requestedFile = parse.nextToken().toLowerCase();

        if (method.equals("GET"))
            return createGetRequest(requestedFile, in);
        if (method.equals("POST"))
            return createPostRequest(requestedFile, in);

        return null;
    }

    private static HttpRequest createGetRequest(String requestedFile, BufferedReader in) {
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

    private static HttpRequest createPostRequest(String requestedFile, BufferedReader in) {
        HttpRequestImpl request = new HttpRequestImpl(HttpRequest.RequestType.POST, requestedFile);

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

    private static void parseParameters(String parameters, HttpRequestImpl request) {
        String values[] = parameters.split("&");
        for (int i = 0; i < values.length; ++i) {
            String parameter[] = values[i].split("=", 2);
            request.setData(parameter[0], parameter[1]);
        }
    }
}
