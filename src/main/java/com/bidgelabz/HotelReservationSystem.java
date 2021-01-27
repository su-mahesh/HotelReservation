package com.bidgelabz;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class HotelReservationSystem {

    private HashMap<String, Hotel> hotels = new HashMap<>();
    private float minRateDifference = 10;
    private int minRatingDifference = 1;

    public void addHotel(Hotel lakewood) {
        String hotelName = lakewood.getHotelName().toLowerCase();
        if (!hotels.containsValue(lakewood))
            hotels.put(hotelName, lakewood);
        else System.out.println("hotel already exist");
    }


    public boolean checkIfHotelExist(Hotel lakewood) {
        return hotels.containsValue(lakewood);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Hotel reservation program");
    }


    public Hotel getCheapestHotel(LocalDate fromDate, LocalDate toDate) {
        int duration = (int) ChronoUnit.DAYS.between( fromDate, toDate)+1;

        Hotel cheapestHotel = null;
        float cheapestRate = 0;
        for (Hotel hotel: hotels.values()){
            float currentRate = hotel.getRegularRate() * duration;
            if (cheapestRate == 0){
                cheapestRate = currentRate;
                cheapestHotel = hotel;
            }
            if (cheapestRate > currentRate ) {
                cheapestHotel = hotel;
                cheapestRate = currentRate;
            }
        }
        return cheapestHotel;
    }

    public Hotel getCheapestHotelHavingDifferentRate(LocalDate fromDate, LocalDate toDate) {

        Hotel cheapestHotel = null;
        float cheapestRate = 0;
        float currentRate = 0;
        int bestRating = 0;
        int currentRating = 0;

        for (Hotel hotel: hotels.values()){

            for (LocalDate date = fromDate; date.isBefore(toDate) || date.equals(toDate); date = date.plusDays(1)) {
                currentRate += (date.getDayOfWeek().getValue() != 6 && date.getDayOfWeek().getValue() != 7 ) ?
                                hotel.getWeekdayRate() : hotel.getWeekendRate();
            }
            if (cheapestRate == 0) {
                bestRating = hotel.getRating();
                cheapestRate = currentRate;
                cheapestHotel = hotel;
            }
            if (cheapestRate - currentRate <= minRateDifference && Math.abs(bestRating - hotel.getRating()) == minRatingDifference) {
                cheapestHotel = hotel;
                cheapestRate = currentRate;

            }
            else if(cheapestRate > currentRate ) {
                bestRating = hotel.getRating();
                cheapestRate = currentRate;
                cheapestHotel = hotel;
            }


            currentRate = 0;
        }
        System.out.println("cheapest hotel: " +cheapestHotel.getHotelName()+ " rating: " +bestRating+ " total price: "+ cheapestRate);
        return cheapestHotel;

    }
}
