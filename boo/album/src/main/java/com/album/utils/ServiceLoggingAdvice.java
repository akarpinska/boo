package com.album.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by akarpinska on 5/7/14.
 */
public class ServiceLoggingAdvice implements MethodBeforeAdvice {

    private static org.apache.log4j.Logger log = Logger
            .getLogger("AlbumService");

    public ServiceLoggingAdvice() {
        PropertyConfigurator.configure(ServiceLoggingAdvice.class.getResource("/config/log4j.properties"));
    }

    public void before (Method method, Object args[], Object target) {
        log.info("Calling " + method.getName() + "...");
    }
}
