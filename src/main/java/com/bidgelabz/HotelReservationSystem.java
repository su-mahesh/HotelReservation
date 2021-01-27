package com.bidgelabz;

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
}
