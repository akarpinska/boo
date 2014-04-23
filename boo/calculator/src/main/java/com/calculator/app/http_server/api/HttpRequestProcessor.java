package com.calculator.app.http_server.api;

/**
 * Created by akarpinska on 4/22/14.
 */
public abstract class HttpRequestProcessor {

    protected HttpSocketHandler httpSocketHandler = null;
    private final String fileToProcess;

    protected HttpRequestProcessor(String fileToProcess) {
        this.fileToProcess = fileToProcess;
    }

    abstract public void onGet(HttpRequest httpRequest);

    abstract public void onPost(HttpRequest httpRequest);

    public String getPathToFileToProcess() { return fileToProcess; }

    public void setSocketHandler(HttpSocketHandler httpSocketHandler) {
        this.httpSocketHandler = httpSocketHandler;
    }
}
