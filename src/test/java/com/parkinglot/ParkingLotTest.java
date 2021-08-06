package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    public void should_return_parking_ticken_when_park_given_parking_lot_and_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingLot.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_fetch_given_parking_lot_and_parked_car_and_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);

        //when
        Car actualCar = parkingLot.fetch(parkingTicket);

        //then
        assertEquals(car, actualCar);
    }

    @Test
    public void should_return_right_car_when_fetch_twice_given_a_parking_lot_with_two_parked_cars_and_two_parking_tickets() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car aliceCar = new Car();
        Car bobCar = new Car();
        ParkingTicket aliceParkingTicket = parkingLot.park(aliceCar);
        ParkingTicket bobParkingTicket = parkingLot.park(bobCar);

        //when
        Car actualAliceCar = parkingLot.fetch(aliceParkingTicket);
        Car actualBobCar = parkingLot.fetch(bobParkingTicket);

        //then
        assertEquals(aliceCar, actualAliceCar);
        assertEquals(bobCar, actualBobCar);
    }

    @Test
    public void should_return_nothing_when_fetch_car_given_parking_lot_wrong_ticket_number() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket invalidParkingTicket = new ParkingTicket();

        //when
        Car actualCar = parkingLot.fetch(invalidParkingTicket);
        //then
        assertNull(actualCar);
    }

    @Test
    public void should_return_nothing_when_fetch_car_given_parking_lot_used_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = new ParkingTicket();

        //when
        Car firstActualCar = parkingLot.fetch(parkingTicket);
        Car secondActualCar = parkingLot.fetch(parkingTicket);

        //then
        assertEquals(car,firstActualCar);
        assertNull(secondActualCar);
    }

}
