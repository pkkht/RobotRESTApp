package com.robotposition.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@RestControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleException(Exception exception) {

        ExceptionResponse exceptionResponse = ExceptionResponse.builder().build();

        if (exception instanceof DataIntegrityViolationException) {
            exceptionResponse.setErrorMessage(messageSource.getMessage("error.robotpositionalreadyexists", null, Locale.getDefault()));
            exceptionResponse.setErrorResolution(messageSource.getMessage("error.robotpositionalreadyexists.fix", null, Locale.getDefault()));
            return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
        } else if (exception instanceof IllegalMoveException){
            String[] direction = {exception.getMessage()};
            exceptionResponse.setErrorMessage(messageSource.getMessage("error.illegalmove", direction, Locale.getDefault()));
            exceptionResponse.setErrorResolution(messageSource.getMessage("error.illegalmove.fix", null, Locale.getDefault()));
            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
