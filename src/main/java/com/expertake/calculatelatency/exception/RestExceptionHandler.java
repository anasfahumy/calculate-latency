package com.expertake.calculatelatency.exception;

import com.expertake.calculatelatency.vo.APIError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class is used to, Send custom error messages when rest API get an error
 * based on the Exception type Error get reference & it will also log into the
 * application log file as well
 *
 * @author afahumy
 * @since 7/3/2022
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler({LatencyServiceException.class, Throwable.class})
    public ResponseEntity<APIError> latencyServiceException(Exception ex, WebRequest request) {
        APIError error = new APIError(HttpStatus.BAD_REQUEST, "Unexpected Exception", ex.getLocalizedMessage());
        logger.error("Exception trace -> {}, {}", error.getReference(), error, ex);
        return new ResponseEntity<>(error, error.getStatus());
    }
}
