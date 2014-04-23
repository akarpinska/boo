package com.calculator.app.backend.impl;

import com.calculator.app.backend.api.DatabaseManager;
import com.calculator.app.http_server.api.HttpRequestProcessor;

/**
 * Created by akarpinska on 4/24/14.
 */
abstract class PageProcessorImpl extends HttpRequestProcessor {

    private DatabaseManager databaseManager = null;

    protected PageProcessorImpl(String pathToFile) {
        super(pathToFile);
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
