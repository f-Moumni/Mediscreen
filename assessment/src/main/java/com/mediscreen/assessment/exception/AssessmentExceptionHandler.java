package com.mediscreen.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class AssessmentExceptionHandler {

    @ExceptionHandler(Ressource4XXException.class)
    public ResponseEntity<Object> handleRessourceNotFoundException(
            Ressource4XXException e, WebRequest request) {

        ExceptionDetails exception = new ExceptionDetails(LocalDateTime.now(),
                e.getMessage(), HttpStatus.NOT_FOUND,
                request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);

    }
}
