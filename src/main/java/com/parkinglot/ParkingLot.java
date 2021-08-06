package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Car car;
    private Map<ParkingTicket, Car> parkedPosition = new HashMap<>();

    public ParkingTicket park(Car car) {
        if (parkedPosition.size() >= 10) {
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkedPosition.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car fetchedCar = parkedPosition.get(parkingTicket);
        parkedPosition.remove(parkingTicket);
        if (parkedPosition.containsKey(parkingTicket)) {
            return null;
        }
        return fetchedCar;
    }
}
