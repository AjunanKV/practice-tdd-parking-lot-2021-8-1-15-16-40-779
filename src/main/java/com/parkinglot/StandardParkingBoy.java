package com.parkinglot;

import java.util.Map;


public class StandardParkingBoy {
    private Car car;
    ParkingLot parkingLots = new ParkingLot();

    public StandardParkingBoy(ParkingLot parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        this.car = car;
        ParkingTicket parkingTicket = new ParkingTicket();
        return parkingTicket;
}

}
