package com.robotposition.exception;

public class IllegalMoveException extends RuntimeException{

    public IllegalMoveException(String directionAttempted){
        super(directionAttempted);
    }
}
