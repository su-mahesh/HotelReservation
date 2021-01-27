package com.bidgelabz;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class HotelReservationSystem {

    private HashMap<String, Hotel> hotels = new HashMap<>();

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


    public Hotel findCheapestHotel(LocalDate fromDate, LocalDate toDate) {
        int duration = (int) ChronoUnit.DAYS.between( fromDate, toDate);

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
}
