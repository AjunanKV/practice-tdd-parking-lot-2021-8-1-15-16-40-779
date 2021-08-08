package com.parkinglot;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends StandardParkingBoy{
    private List<ParkingLot> parkingLots;

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SmartParkingBoy(List<ParkingLot> parkingLots1) {
        super(parkingLots1);
    }


    public ParkingTicket park(Car car) {
        return getAvailableSmartParkingLot().park(car);
    }

    private ParkingLot getAvailableSmartParkingLot(){
        return getParkingLots()
                .stream()
                .filter(parkingLot -> !parkingLot.ifFull())
                .min(Comparator.comparing(ParkingLot::getCurrentParkedCarsCount))
                .orElseThrow(ParkingWithNoPosition::new);
    }


    }

