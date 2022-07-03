package com.expertake.calculatelatency.exception;

/**
 * @author afahumy
 * @since 7/2/2022
 */
public class LatencyServiceException extends RuntimeException {

    public LatencyServiceException() {

    }

    public LatencyServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public LatencyServiceException(String message) {
        super(message);
    }
}
