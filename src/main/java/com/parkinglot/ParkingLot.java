package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Car car;
    private Map<ParkingTicket, Car> parkedPosition = new HashMap<>();

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkedPosition.put(parkingTicket, car);
        //this.car =car;
        return  parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkedPosition.get(parkingTicket);
    }
}
