package com.neo.neoTour.exception;

public class InvalidCredentialException extends RuntimeException {
    public InvalidCredentialException(String message){
        super(message);
    }
}
