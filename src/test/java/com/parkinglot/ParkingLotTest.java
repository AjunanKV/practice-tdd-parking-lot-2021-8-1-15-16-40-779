package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    //Customer
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

//    @Test
//    public void should_return_nothing_when_fetch_car_given_parking_lot_wrong_ticket_number() {
//        //given
//        ParkingLot parkingLot = new ParkingLot();
//        Car car = new Car();
//        ParkingTicket invalidParkingTicket = new ParkingTicket();
//        //when
//        Car actualCar = parkingLot.fetch(invalidParkingTicket);
//        //then
//        assertNull(actualCar);
//    }

//    @Test
//    public void should_return_nothing_when_fetch_car_given_parking_lot_used_parking_ticket() {
//        //given
//        ParkingLot parkingLot = new ParkingLot();
//        Car car = new Car();
//        ParkingTicket parkingTicket = parkingLot.park(car);
//        //when
//        parkingLot.fetch(parkingTicket);
//        Car resUsedTicket = parkingLot.fetch(parkingTicket);
//        //then
//        assertNull(resUsedTicket);
//    }

//    @Test
//    public void should_return_nothing_when_fetch_car_given_parking_lot_without_any_position() {
//        //given
//        ParkingLot parkingLot = new ParkingLot();
//        Car car = new Car();
//        //when
//        for (int counter = 0; counter <= 10; counter++) {
//            parkingLot.park(car);
//        }
//        ParkingTicket parkWhenFull = parkingLot.park(car);
//        //then
//        assertNull(parkWhenFull);
//    }

    @Test
    public void should_return_error_message_when_fetch_car_given_parking_lot_wrong_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
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
        for (int counter = 0; counter < 10; counter++) {
            parkingLot.park(car);
        }
        String errorMessage = "No available position.";
        Exception exception = assertThrows(ParkingWithNoPosition.class, () -> parkingLot.park(car));
        //then
        assertEquals(errorMessage, exception.getMessage());
    }

    //Standard parking boy
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

//    @Test
//    public void should_return_nothing_when_fetch_car_by_Standard_Parking_boy_given_parking_lot_wrong_ticket_number() {
//        //given
//        ParkingLot parkingLot = new ParkingLot();
//        Car car = new Car();
//        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
//        ParkingTicket invalidParkingTicket = new ParkingTicket();
//        //when
//        Car actualCar = standardParkingBoy.fetch(invalidParkingTicket);
//        //then
//        assertNull(actualCar);
//    }

//    @Test
//    public void should_return_nothing_when_fetch_car_by_Standard_Parking_boy_given_parking_lot_used_parking_ticket() {
//        //given
//        ParkingLot parkingLot = new ParkingLot();
//        Car car = new Car();
//        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
//        ParkingTicket parkingTicket = parkingLot.park(car);
//        //when
//        parkingLot.fetch(parkingTicket);
//        Car resUsedTicket = standardParkingBoy.fetch(parkingTicket);
//        //then
//        assertNull(resUsedTicket);
//    }

//    @Test
//    public void should_return_nothing_when_fetch_car_by_Standard_Parking_boy_given_parking_lot_without_any_position() {
//        //given
//        ParkingLot parkingLot = new ParkingLot();
//        Car car = new Car();
//        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
//        //when
//        for (int counter = 0; counter <= 10; counter++) {
//            parkingLot.park(car);
//        }
//
//        ParkingTicket parkWhenFull = standardParkingBoy.park(car);
//        //then
//        assertNull(parkWhenFull);
//    }

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
        for (int counter = 0; counter < 10; counter++) {
            standardParkingBoy.park(car);
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

    @Test
    public void should_return_car_parked_in_second_parking_lot_when_park_given_a_standard_parking_boy_manage_first_full_parking_lot_and_second_with_position_available_and_a_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLots.add(parkingLot1);
        Car car = new Car();
        for (int i = 0; i < 10; i++) {
            parkingLot1.park(car);
        }
        parkingLots.add(parkingLot2);
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);

        //when
        parkingBoy.park(car);

        //then
        assertEquals("Car is parked in Parking Lot 2", systemOut());

    }


    @Test
    public void should_return_correct_car_when_fetch_twice_given_a_standard_parking_boy_manage_two_parking_lots_with_parked_cars_and_two_parking_tickets() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        Car aliceCar = new Car();
        Car bobCar = new Car();
        ParkingTicket aliceTicket = parkingLot1.park(aliceCar);
        ParkingTicket bobTicket = parkingLot2.park(bobCar);
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);

        //when
        Car actualAliceCar = parkingBoy.fetch(aliceTicket);
        Car actualBobCar = parkingBoy.fetch(bobTicket);

        //then
        assertEquals(aliceCar, actualAliceCar);
        assertEquals(bobCar, actualBobCar);

    }

    @Test
    public void should_return_error_message__when_fetch_by_parking_boy_given_a_standard_parking_boy_managing_two_parking_lots_and_an_invalid_Parking_ticket() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
        ParkingTicket invalidParkingTicket = new ParkingTicket();

        //when & then
        Exception exception = assertThrows(UnrecognizedParkingTicketsException.class, () -> parkingBoy.fetch(invalidParkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_error_message_when_fetch_given_a_standard_parking_boy_managing_two_full_parking_lots_and_a_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        Car car = new Car();
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
        //when

        for (int i = 0; i < 20; i++) {
            parkingBoy.park(car);
        }

        //then
        Exception exception = assertThrows(ParkingWithNoPosition.class, () -> parkingBoy.park(car));
        assertEquals("No available position.", exception.getMessage());
    }

    @Test
    public void should_return_Parking_ticket_when_park_car_by_Smart_parking_boy_given_parking_lot_and_car() {
        //given
        StandardParkingBoy smartParkingBoy = new StandardParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        List<ParkingTicket> ParkingLot1 = new LinkedList<>();
        List<ParkingTicket> ParkingLot2 = new LinkedList<>();
        Car car = new Car();

        for (int i = 0; i < 7; i++) {
            ParkingLot1.add(smartParkingBoy.park(car));
        }
        for (int i = 0; i <= 6; i++) {
            ParkingLot2.add(smartParkingBoy.park(car));
        }

        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //then
        assertEquals(5, smartParkingBoy.getParkingLots().get(1).getCurrentParkedCarsCount());
        assertTrue(smartParkingBoy.getParkingLots().get(0).getCurrentParkedCarsCount() > smartParkingBoy.getParkingLots().get(1).getCurrentParkedCarsCount());
    }

    @Test
    void should_return_car_when_fetch_given_a_smart_parking_boy_and_parking_lot_and_a_car() {
        //given
        StandardParkingBoy smartParkingBoy = new StandardParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        Car car = new Car();

        for (int i = 0; i < 9; i++) {
            parkingLot1.park(car);
        }
        for (int i = 0; i < 5; i++) {
            parkingLot2.park(car);
        }
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        //when
        Car actualCar = smartParkingBoy.fetch(parkingTicket);
        //then
        assertEquals(car, actualCar);
    }

    @Test
    void should_return_error_message_when_given_a_smart_parking_boy_two_parking_lots_unrecognized_ticket() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketsException.class, () -> smartParkingBoy.fetch(unrecognizedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_error_message_when_given_a_smart_parking_boy_two_parking_lots_used_ticket() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        ParkingTicket parkingTicket = smartParkingBoy.park(new Car());
        smartParkingBoy.fetch(parkingTicket);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketsException.class, () -> smartParkingBoy.fetch(parkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void return_error_message_when_park_given_a_smart_parking_boy_two_parking_lots_no_available_parking_slot() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        Car car = new Car();
        List<ParkingTicket> parkingLotOneTicket = new LinkedList<>();
        List<ParkingTicket> parkingLotTwoTicket = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            parkingLotOneTicket.add(smartParkingBoy.park(car));
        }
        for (int i = 0; i < 10; i++) {
            parkingLotTwoTicket.add(smartParkingBoy.park(car));
        }

        //when
        Exception exception = assertThrows(ParkingWithNoPosition.class, () -> smartParkingBoy.park(car));

        //then
        assertEquals("No available position.", exception.getMessage());
    }

    @Test
    void should_return_car_given_super_smart_parking_boy_a_parking_lot_and_car() {
        //given
        SmartParkingBoy superSmartParkingBoy = new SmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        List<ParkingTicket> ParkingLot1 = new ArrayList<>(10);
        List<ParkingTicket> ParkingLot2 = new ArrayList<>(15);
        Car car = new Car();

        for (int i = 0; i < 7; i++) {
            ParkingLot1.add(superSmartParkingBoy.park(car));
        }
        for (int i = 0; i <= 6; i++) {
            ParkingLot2.add(superSmartParkingBoy.park(car));
        }

        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);

        //then
        assertEquals(7, superSmartParkingBoy.getParkingLots().get(1).getCurrentParkedCarsCount());
        assertTrue(superSmartParkingBoy.getParkingLots().get(0).getCurrentParkedCarsCount() > superSmartParkingBoy.getParkingLots().get(1).getCurrentParkedCarsCount());

    }

    @Test
    void should_return_car_when_fetch_given_super_smart_parking_boy_and_parking_lot_and_car() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        Car car = new Car();
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLots);
        for (int i = 0; i < 9; i++) {
            parkingLot1.park(car);
        }
        for (int i = 0; i < 5; i++) {
            parkingLot2.park(car);
        }
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //when
        Car actualCar = smartParkingBoy.fetch(parkingTicket);

        //then
        assertEquals(car, actualCar);
    }

    @Test
    void should_return_car_when_fetch_given_super_smart_parking_boy_and_two_parked_cars_and_parking_lot() {
        //given
        Car aliceCar = new Car();
        Car bobCar = new Car();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        ParkingTicket aliceTicket = superSmartParkingBoy.park(aliceCar);
        ParkingTicket bobTicket = superSmartParkingBoy.park(bobCar);
        //when
        Car actualAliceCar = superSmartParkingBoy.fetch(aliceTicket);
        Car actualBobCar = superSmartParkingBoy.fetch(bobTicket);
        //then
        assertEquals(aliceCar, actualAliceCar);
        assertEquals(bobCar, actualBobCar);
    }

    @Test
    void should_return_error_message_when_given_a_super_smart_parking_boy_two_parking_lots_unrecognized_ticket() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketsException.class, () -> superSmartParkingBoy.fetch(unrecognizedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_error_message_when_given_a_super_smart_parking_boy_two_parking_lots_used_ticket() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        ParkingTicket parkingTicket = superSmartParkingBoy.park(new Car());
        superSmartParkingBoy.fetch(parkingTicket);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketsException.class, () -> superSmartParkingBoy.fetch(parkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void return_error_message_when_park_given_a_super_smart_parking_boy_two_parking_lots_no_available_parking_slot() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        Car car = new Car();
        List<ParkingTicket> parkingLotOneTicket = new LinkedList<>();
        List<ParkingTicket> parkingLotTwoTicket = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            parkingLotOneTicket.add(superSmartParkingBoy.park(car));
        }
        for (int i = 0; i < 10; i++) {
            parkingLotTwoTicket.add(superSmartParkingBoy.park(car));
        }

        //when
        Exception exception = assertThrows(ParkingWithNoPosition.class, () -> superSmartParkingBoy.park(car));

        //then
        assertEquals("No available position.", exception.getMessage());
    }

    @Test
    void return_ticket_when_park_given_super_smart_parking_boy_2_parking_slot_with_10_car_but_not_higher_than_20() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        Car car = new Car();
        List<ParkingTicket> parkingTicket = new LinkedList<>();

        //when
        for (int i = 0; i < 20; i++) {
            parkingTicket.add(superSmartParkingBoy.park(car));
        }
        Exception exception = assertThrows(ParkingWithNoPosition.class, () -> superSmartParkingBoy.park(car));

        //then
        assertEquals("No available position.", exception.getMessage());
    }

}


