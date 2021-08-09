package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends StandardParkingBoy {


    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }


    public ParkingTicket park(Car car) {
        return getAvailableSmartParkingLot().park(car);
    }

    private ParkingLot getAvailableSmartParkingLot() {
        return getParkingLots()
                .stream()
                .filter(parkingLot -> !parkingLot.ifFull())
                .max(Comparator.comparing(ParkingLot::getCurrentParkedCarsCount))
                .orElseThrow(ParkingWithNoPosition::new);
    }
}
