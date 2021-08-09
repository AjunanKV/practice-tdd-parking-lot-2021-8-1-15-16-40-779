package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> parkedPosition = new HashMap<>();
    private final int DEFAULT_CAPACITY = 10;
    private int capacity;

    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public ParkingLot(int capacity) {
        parkedPosition = new HashMap<>();
        this.capacity = capacity;
    }


    public ParkingTicket park(Car car) {
        if (ifFull()) {
            throw new ParkingWithNoPosition();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkedPosition.put(parkingTicket, car);
        return parkingTicket;
    }
    public boolean ifFull() {
        return capacity==parkedPosition.size();
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (!parkedPosition.containsKey(parkingTicket)) {
            throw new UnrecognizedParkingTicketsException();
        }
        Car fetchedCar = parkedPosition.get(parkingTicket);
        parkedPosition.remove(parkingTicket);

        return fetchedCar;
    }

    public Map<ParkingTicket, Car> getParkedPosition() {
        return parkedPosition;
    }


    public int getCurrentParkedCarsCount() {
        return parkedPosition.size();
    }
}
