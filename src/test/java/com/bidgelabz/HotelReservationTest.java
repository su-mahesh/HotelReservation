package com.bidgelabz;

import org.junit.Assert;
import org.junit.Test;

public class HotelReservationTest {

    @Test
    public void givenHotelNameAndRatesForRegularCustomer_WhenAdded_ShouldReturnTrue() {
        HotelReservationSystem hotelReservation = new HotelReservationSystem();
        Hotel Lakewood = new Hotel("Lakewood");
        Lakewood.setRegularRate(100);
        hotelReservation.addHotel(Lakewood);

        boolean result = hotelReservation.checkIfHotelExist(Lakewood);
        Assert.assertTrue(result);
    }



}
    

