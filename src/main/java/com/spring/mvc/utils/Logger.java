package com.spring.mvc.utils;

/**
 * Created by liluoqi on 15/4/23.
 */
public class Logger {
    private org.apache.log4j.Logger log;

    private Logger(org.apache.log4j.Logger log) {
        this.log = log;
    }

    public static Logger getLogger(Class clazz) {
        return new Logger(org.apache.log4j.Logger.getLogger(clazz));
    }

    public void info(String message,Throwable throwable){
        log.info(message,throwable);
    }

    public void info(String message){
        log.info(message);
    }

    public void warn(String message,Throwable throwable){
        log.warn(message, throwable);
    }

    public void warn(String message){
        log.warn(message);
    }

    public void error(String message,Throwable throwable){
        log.error(message, throwable);
    }

    public void error(String message){
        log.error(message);
    }
}
