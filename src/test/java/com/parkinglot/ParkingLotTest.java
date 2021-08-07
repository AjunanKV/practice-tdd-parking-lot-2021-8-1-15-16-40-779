package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

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
        ParkingTicket parkingTicket = parkingLot.park(car);
        //when
        parkingLot.fetch(parkingTicket);
        Car resUsedTicket = parkingLot.fetch(parkingTicket);
        //then
        assertNull(resUsedTicket);
    }

    @Test
    public void should_return_nothing_when_fetch_car_given_parking_lot_without_any_position() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //when
        for (int counter = 0; counter <= 10; counter++) {
            parkingLot.park(car);
        }
        ParkingTicket parkWhenFull = parkingLot.park(car);
        //then
        assertNull(parkWhenFull);
    }

    @Test
    public void should_return_error_message_when_fetch_car_given_parking_lot_wrong_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket invalidParkingTicket = new ParkingTicket();
        String errorMessage = "Unrecognized parking ticket.";
        //when
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        Exception exception = assertThrows(UnrecognizedParkingTicketsException.class, () -> parkingLot.fetch(unrecognizedParkingTicket));
        //then
        assertEquals(errorMessage, exception.getMessage());

    }

    @Test
    public void should_return_No_available_position_message_when_fetch_car_given_parking_lot_without_any_position() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //when
        for (int counter = 0; counter <= 10; counter++) {
            parkingLot.park(car);
        }
        String errorMessage = "No available position.";
        Exception exception = assertThrows(ParkingWithNoPosition.class, () -> parkingLot.park(car));
        //then
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void should_return_Parking_ticket_when_park_car_by_Standard_parking_boy_given_parking_lot_and_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //when
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_fetch_by_Standard_parking_boy_given_parking_lot_and_parked_car_and_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        //when
        Car actualCar = standardParkingBoy.fetch(parkingTicket);
        //then
        assertEquals(car, actualCar);
    }

    @Test
    public void should_return_right_car_when_fetch_twice_by_standark_ParkingBoy_given_a_parking_lot_with_two_parked_cars_and_two_parking_tickets() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car aliceCar = new Car();
        Car bobCar = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        ParkingTicket parkingTicket = standardParkingBoy.park(bobCar);
        ParkingTicket aliceParkingTicket = parkingLot.park(aliceCar);

        //when
        Car actualAliceCar = parkingLot.fetch(aliceParkingTicket);
        Car actualBobCar = standardParkingBoy.fetch(parkingTicket);

        //then
        assertEquals(aliceCar, actualAliceCar);
        assertEquals(bobCar, actualBobCar);
    }

    @Test
    public void should_return_nothing_when_fetch_car_by_Standard_Parking_boy_given_parking_lot_wrong_ticket_number() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        ParkingTicket invalidParkingTicket = new ParkingTicket();
        //when
        Car actualCar = standardParkingBoy.fetch(invalidParkingTicket);
        //then
        assertNull(actualCar);
    }

    @Test
    public void should_return_nothing_when_fetch_car_by_Standard_Parking_boy_given_parking_lot_used_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        ParkingTicket parkingTicket = parkingLot.park(car);
        //when
        parkingLot.fetch(parkingTicket);
        Car resUsedTicket = standardParkingBoy.fetch(parkingTicket);
        //then
        assertNull(resUsedTicket);
    }

    @Test
    public void should_return_nothing_when_fetch_car_by_Standard_Parking_boy_given_parking_lot_without_any_position() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        //when
        for (int counter = 0; counter <= 10; counter++) {
            parkingLot.park(car);
        }

        ParkingTicket parkWhenFull = standardParkingBoy.park(car);
        //then
        assertNull(parkWhenFull);
    }

    @Test
    public void should_return_error_message_when_fetch_car_by_Standard_Parking_boy_given_parking_lot_wrong_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket invalidParkingTicket = new ParkingTicket();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        String errorMessage = "Unrecognized parking ticket.";
        //when
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();
        Exception exception = assertThrows(UnrecognizedParkingTicketsException.class, () -> standardParkingBoy.fetch(unrecognizedParkingTicket));
        //then
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void should_return_No_available_position_message_when_fetch_car_by_Standard_Parking_boy_given_parking_lot_without_any_position() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        //when
        for (int counter = 0; counter <= 10; counter++) {
            parkingLot.park(car);
        }
        String errorMessage = "No available position.";
        Exception exception = assertThrows(ParkingWithNoPosition.class, () -> standardParkingBoy.park(car));
        //then
        assertEquals(errorMessage, exception.getMessage());
    }


    @Test
    public void should_return_car_parked_in_first_parking_lot_when_park_given_a_standard_parking_boy_manage_two_not_full_parking_lots_and_a_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        assertEquals("Car is parked in Parking Lot 1", systemOut());
        assertNotNull(parkingTicket);

    }
    private String systemOut() {
        return outContent.toString();
    }


}

