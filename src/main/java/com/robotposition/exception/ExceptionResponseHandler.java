package com.robotposition.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
@Component
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleException(Exception exception) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), messageSource.getMessage("error.genericmessage", null, Locale.getDefault()) ,
                messageSource.getMessage("error.generic.fix", null, Locale.getDefault()));

        if (exception instanceof DuplicateRobotPositionException) {
            exceptionResponse.setErrorMessage(messageSource.getMessage("error.robotpositionalreadyexists", null, Locale.getDefault()));
            exceptionResponse.setErrorResolution(messageSource.getMessage("error.robotpositionalreadyexists.fix", null, Locale.getDefault()));
            return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
        } else if (exception instanceof IllegalMoveException){
            String[] direction = {exception.getMessage()};
            exceptionResponse.setErrorMessage(messageSource.getMessage("error.illegalmove", direction, Locale.getDefault()));
            exceptionResponse.setErrorResolution(messageSource.getMessage("error.illegalmove.fix", null, Locale.getDefault()));
            return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError->fieldError.getDefaultMessage()).collect(Collectors.toList());
        ApiRequestDataValidationError apiInputDataErrors = new ApiRequestDataValidationError(messageSource.getMessage("error.validationerrors.present", null, Locale.getDefault()));
        apiInputDataErrors.setValidationErrorList(validationList);
        return new ResponseEntity<>(apiInputDataErrors, status);
    }

    @Override
    public final ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String errorMessage = ex.getMessage();
        List<String> validationList = new ArrayList<>(Collections.singleton(errorMessage));
        ApiRequestDataValidationError apiInputDataErrors = new ApiRequestDataValidationError(messageSource.getMessage("error.validationerrors.present", null, Locale.getDefault()));
        apiInputDataErrors.setValidationErrorList(validationList);
        return new ResponseEntity<>(apiInputDataErrors, status);
    }



}
