package com.dadaban.utils;

import org.slf4j.Logger;

/**
 * @author Hardy Ferentschik
 */
public final class LoggerFactory {
    public static Logger make() {
        Throwable t = new Throwable();
        StackTraceElement directCaller = t.getStackTrace()[1];
        return org.slf4j.LoggerFactory.getLogger(directCaller.getClassName());
    }

    // private constructor to avoid instantiation
    private LoggerFactory() {

    }
}