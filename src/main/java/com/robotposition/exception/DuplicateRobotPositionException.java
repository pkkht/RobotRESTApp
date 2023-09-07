package com.robotposition.exception;

import org.springframework.dao.DataIntegrityViolationException;

/**
 * @author Hari
 * Custom exception class
 */
public class DuplicateRobotPositionException extends RuntimeException{

    public DuplicateRobotPositionException(DataIntegrityViolationException e) {
        super(e);
    }
}
