package com.techBrains.grievance.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex) {
        ErrorMessage message = new ErrorMessage();
        message.setStatusCode(HttpStatus.NOT_FOUND);
        message.setMessage(ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CopyPropertiesException.class})
    public ResponseEntity<ErrorMessage> copyPropertiesException(CopyPropertiesException ex) {
        ErrorMessage message = new ErrorMessage();
        message.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        message.setMessage(ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorMessage> genericException(Exception ex) {
        ErrorMessage message = new ErrorMessage();
        message.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        message.setMessage(ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {MessageTranslationException.class})
    public void genericMessageTranslationException(MessageTranslationException ex) {
        ErrorMessage message = new ErrorMessage();
        message.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        message.setMessage(ex.getMessage());

        log.error("Exception: {}", ex.getMessage());
    }
}
