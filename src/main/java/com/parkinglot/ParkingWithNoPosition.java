package com.parkinglot;

public class ParkingWithNoPosition extends RuntimeException{
    @Override
    public String getMessage()
    {
        return "No available position.";
    }
}
