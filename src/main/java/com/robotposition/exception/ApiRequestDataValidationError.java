package com.robotposition.exception;

import java.util.List;

public class ApiRequestDataValidationError {

    String errorMessage;

    List<String> validationErrorList;
    public ApiRequestDataValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getValidationErrorList() {
        return validationErrorList;
    }

    public void setValidationErrorList(List<String> validationErrorList) {
        this.validationErrorList = validationErrorList;
    }
}
