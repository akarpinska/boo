package com.calculator.app.http_server.impl;

import com.calculator.app.http_server.api.HttpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akarpinska on 4/17/14.
 */
class HttpRequestImpl implements HttpRequest {

    private RequestType type;
    private String requestedFile;
    private Map<String, String> data;

    public HttpRequestImpl(RequestType type, String requestedFile) {
        this.type = type;
        this.requestedFile = requestedFile;
        data = new HashMap<String, String>();
    }

    public RequestType getType() {
        return type;
    }

    public void setData(String key, String value) {
        data.put(key, value);
    }

    public String getData(String key) {
        return data.get(key);
    }

    public String getRequestedFile() {
        return requestedFile;
    }
}
