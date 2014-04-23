package com.calculator.app.backend.impl;

import com.calculator.app.backend.api.DatabaseManager;
import com.calculator.app.http_server.api.BackendFactory;
import com.calculator.app.http_server.api.HttpRequestProcessor;
import com.calculator.app.http_server.api.HttpSocketHandler;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by akarpinska on 4/24/14.
 */
public class BackendFactoryImpl implements BackendFactory {

    Map<String, Class<? extends PageProcessorImpl>> pageProcessors;
    private DatabaseManager databaseManager;

    public BackendFactoryImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        browsePageProcessors();
    }

    private void browsePageProcessors() {
        Reflections reflections = new Reflections("com.calculator.app.backend.impl");

        Set<Class<? extends PageProcessorImpl>> pageProcessorsClasses =
                reflections.getSubTypesOf(PageProcessorImpl.class);

        pageProcessors = new HashMap<String, Class<? extends PageProcessorImpl>>();

        Iterator<Class<? extends PageProcessorImpl>> processorsIt = pageProcessorsClasses.iterator();
        while (processorsIt.hasNext()) {
            try {
                Class processorClass = processorsIt.next();
                PageProcessorImpl processor = (PageProcessorImpl) processorClass.newInstance();
                String pathToFile = processor.getPathToFileToProcess();
                pageProcessors.put(pathToFile, processorClass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public HttpRequestProcessor newRequestProcessor(HttpSocketHandler socketHandler, String pathToFile) {
        Class processorClass = pageProcessors.get(pathToFile);
        PageProcessorImpl processor;
        if (processorClass != null) {
            try {
                processor = (PageProcessorImpl) processorClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                processor = new PageNotFoundImpl();
            }
        }
        else
            processor = new PageNotFoundImpl();
        processor.setSocketHandler(socketHandler);
        processor.setDatabaseManager(databaseManager);
        return processor;
    }
}
