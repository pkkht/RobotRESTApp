package com.robotposition.exception;

/**
 * @author Hari
 * Custom exception class
 */
public class IllegalMoveException extends RuntimeException{

    public IllegalMoveException(String directionAttempted){
        super(directionAttempted);
    }
}
