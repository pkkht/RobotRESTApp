package com.robotposition.exception;


import java.time.LocalDateTime;

public class ExceptionResponse {

    LocalDateTime errorHappenedAt;
    String errorMessage;
    String errorResolution;

    public ExceptionResponse(LocalDateTime errorHappenedAt, String errorMessage, String errorResolution){
        this.errorHappenedAt = errorHappenedAt;
        this.errorMessage = errorMessage;
        this.errorResolution = errorResolution;
    }

    public LocalDateTime getErrorHappenedAt() {
        return errorHappenedAt;
    }

    public void setErrorHappenedAt(LocalDateTime errorHappenedAt) {
        this.errorHappenedAt = errorHappenedAt;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorResolution() {
        return errorResolution;
    }

    public void setErrorResolution(String errorResolution) {
        this.errorResolution = errorResolution;
    }
}
