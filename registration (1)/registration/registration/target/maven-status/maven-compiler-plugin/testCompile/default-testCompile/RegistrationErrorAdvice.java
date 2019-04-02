package com.magento.registration.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * This class handles the exceptions
 * and returns appropriate
 * message to the client
 */

@ControllerAdvice
@Slf4j
public class RegistrationErrorAdvice {

    /**
     * This method handle the run time exceptions
     * @param e
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return error(INTERNAL_SERVER_ERROR, e);
    }

    /**
     * This method returns the appropriate
     * response and loags error message
     * @param status
     * @param e
     * @return
     */
    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        log.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
