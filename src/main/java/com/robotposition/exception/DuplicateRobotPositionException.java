package com.robotposition.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DuplicateRobotPositionException extends RuntimeException{

    public DuplicateRobotPositionException(DataIntegrityViolationException e) {
        super(e);
    }
}
