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

        Hotel cheapestHotel = hotelReservation.getCheapestHotel(fromDate, toDate);
        Assert.assertSame(lakewood, cheapestHotel);
    }
    @Test
    public void givenWeekdaysAndWeekendRates_WhenGetRates_ShouldReturnSameRate() {
        HotelReservationSystem hotelReservation = new HotelReservationSystem();
        Hotel lakewood = new Hotel("Lakewood");
        lakewood.setWeekdayRate(100);
        lakewood.setWeekendRate(90);

        Hotel bridgewood = new Hotel("Bridgewood");
        bridgewood.setWeekdayRate(150);
        bridgewood.setWeekendRate(50);

        Hotel ridgewood = new Hotel("Ridgewood");
        ridgewood.setWeekdayRate(220);
        ridgewood.setWeekendRate(150);

        Assert.assertEquals(100, lakewood.getWeekdayRate(),0);
        Assert.assertEquals(90, lakewood.getWeekendRate(), 0);

        Assert.assertEquals(150, bridgewood.getWeekdayRate(),0);
        Assert.assertEquals(50, bridgewood.getWeekendRate(), 0);

        Assert.assertEquals(220, ridgewood.getWeekdayRate(),0);
        Assert.assertEquals(150, ridgewood.getWeekendRate(), 0);
    }

    @Test
    public void givenWeekendWeekdayRatesAndDates_WhenSearchedForCheapestHotel_ShouldReturnCheapestHotel() {
        HotelReservationSystem hotelReservation = new HotelReservationSystem();

        Hotel lakewood = new Hotel("Lakewood");
        lakewood.setWeekdayRate(100);
        lakewood.setWeekendRate(90);
        hotelReservation.addHotel(lakewood);

        Hotel bridgewood = new Hotel("Bridgewood");
        bridgewood.setWeekdayRate(150);
        bridgewood.setWeekendRate(50);
        hotelReservation.addHotel(bridgewood);

        Hotel ridgewood = new Hotel("Ridgewood");
        ridgewood.setWeekdayRate(220);
        ridgewood.setWeekendRate(150);
        hotelReservation.addHotel(ridgewood);

        LocalDate fromDate = LocalDate.of(2020, 9, 11);
        LocalDate toDate = LocalDate.of(2020, 9, 12);

        Hotel cheapestHotel = hotelReservation.getCheapestHotelWithDifferentRate(fromDate, toDate);
        Assert.assertSame(lakewood, cheapestHotel);
    }

    @Test
    public void givenHotels_WhenGivenRatings_ShouldReturnSameRatings() {

        Hotel lakewood = new Hotel("Lakewood");
        Hotel bridgewood = new Hotel("Bridgewood");
        Hotel ridgewood = new Hotel("Ridgewood");

        lakewood.rateHotel(3);
        bridgewood.rateHotel(4);
        ridgewood.rateHotel(5);

        Assert.assertEquals(3,lakewood.getRating());
        Assert.assertEquals(4, bridgewood.getRating());
        Assert.assertEquals(5,ridgewood.getRating());

    }
}
    

