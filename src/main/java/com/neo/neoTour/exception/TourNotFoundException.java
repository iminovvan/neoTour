package com.neo.neoTour.exception;

public class TourNotFoundException extends RuntimeException{
    public TourNotFoundException(String message){
        super(message);
    }
}
