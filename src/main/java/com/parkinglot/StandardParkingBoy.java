package com.parkinglot;

import java.util.Map;


public class StandardParkingBoy {
    private Car car;
    ParkingLot parkingLots = new ParkingLot();

    public StandardParkingBoy(ParkingLot parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        if (getMap().size() > 10) {
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        getMap().put(parkingTicket, car);
        return parkingTicket;
    }

    private Map<ParkingTicket, Car> getMap() {
        return parkingLots.getParkedPosition();
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (!getMap().containsKey(parkingTicket)) {
            throw new UnrecognizedParkingTicketsException();
        }
        Car fetchedCar = getMap().get(parkingTicket);
        getMap().remove(parkingTicket);
        if (getMap().containsKey(parkingTicket)) {
            return null;
        } else {
            return fetchedCar;
        }
    }


}
