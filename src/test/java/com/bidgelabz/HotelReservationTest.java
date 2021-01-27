package com.bidgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;


public class HotelReservationTest {

    @Test
    public void givenHotelNameAndRatesForRegularCustomer_WhenAdded_ShouldReturnTrue() {
        HotelReservationSystem hotelReservation = new HotelReservationSystem();
        Hotel lakewood = new Hotel("Lakewood");
        lakewood.setRegularRate(100);
        hotelReservation.addHotel(lakewood);

        boolean result = hotelReservation.checkIfHotelExist(lakewood);
        Assert.assertTrue(result);
    }

    @Test
    public void givenHotelsAndDates_WhenSearchedForCheapestHotel_ShouldReturnCheapestHotel() {
        HotelReservationSystem hotelReservation = new HotelReservationSystem();
        Hotel lakewood = new Hotel("Lakewood");
        lakewood.setRegularRate(10);
        hotelReservation.addHotel(lakewood);

        Hotel bridgewood = new Hotel("Bridgewood");
        bridgewood.setRegularRate(50);
        hotelReservation.addHotel(bridgewood);

        Hotel ridgewood = new Hotel("Ridgewood");
        ridgewood.setRegularRate(80);
        hotelReservation.addHotel(ridgewood);

        LocalDate fromDate = LocalDate.of(2020, 9, 10);
        LocalDate toDate = LocalDate.of(2020, 9, 11);

        Hotel cheapestHotel = hotelReservation.findCheapestHotel(fromDate, toDate);
        Assert.assertSame(lakewood, cheapestHotel);
    }





}
    

