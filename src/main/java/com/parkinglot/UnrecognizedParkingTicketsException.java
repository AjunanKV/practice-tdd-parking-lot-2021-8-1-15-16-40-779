package com.parkinglot;

public class UnrecognizedParkingTicketsException extends RuntimeException {
    @Override
    public String getMessage()
    {
        return "Unrecognized parking ticket.";
    }
}
