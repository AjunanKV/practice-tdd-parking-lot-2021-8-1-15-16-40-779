package com.parkinglot;

import java.util.List;
import java.util.Map;


public class StandardParkingBoy {
    private Car car;
    ParkingLot parkingLot = new ParkingLot();
    private List<ParkingLot> parkingLots;
    private boolean isParkingBoyManageMorethanOneParkingLot=false;

    public StandardParkingBoy(ParkingLot parkingLots) {
        this.parkingLot = parkingLots;
    }

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        this.isParkingBoyManageMorethanOneParkingLot = true;
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {

        if (isParkingBoyManageMorethanOneParkingLot) {
            ParkingLot currentParkingLot = getAvailableParkingLot(parkingLots);
            if (currentParkingLot != null) {
                return currentParkingLot.park(car);
            } else {
                return null;
            }
        } else {
            ParkingTicket parkingTicket = new ParkingTicket();
            getMap().put(parkingTicket, car);
            return parkingTicket;
        }

    }

    private ParkingLot getAvailableParkingLot(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        for (ParkingLot currentParkingLot : parkingLots) {
            if (!currentParkingLot.ifFull()) {
                System.out.print("Car is parked in Parking Lot " + (parkingLots.indexOf(currentParkingLot) + 1));
                return this.parkingLot = currentParkingLot;
            }
        }
        return null;
    }

    private Map<ParkingTicket, Car> getMap() {
        return parkingLot.getParkedPosition();
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
