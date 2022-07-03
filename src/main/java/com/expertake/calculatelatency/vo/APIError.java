package com.expertake.calculatelatency.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author afahumy
 * @since 7/3/2022
 */
public class APIError {
    private HttpStatus status;
    private String message;
    private String debugMessage;
    private String reference;

    public APIError(HttpStatus status, String message, String debugMessage) {
        this.setStatus(status);
        this.message = message;
        this.debugMessage = debugMessage;
        this.reference = UUID.randomUUID().toString();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "APIError{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", debugMessage='" + debugMessage + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}
