package com.nsuws.core.services;

/**
 * Wrapping Statistics Service Exceptions
 */
public class StatisticsServiceException extends  Exception{

    public StatisticsServiceException() {
        super();
    }

    public StatisticsServiceException(String message) {
        super(message);
    }

    public StatisticsServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatisticsServiceException(Throwable cause) {
        super(cause);
    }

    protected StatisticsServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
