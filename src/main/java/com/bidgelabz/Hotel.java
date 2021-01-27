package com.bidgelabz;

public class Hotel {
    private String hotelName;
    private float regularRate;

    public Hotel(String hotelName) {
        this.hotelName = hotelName.substring(0, 1).toUpperCase() + hotelName.substring(1).toUpperCase();
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setRegularRate(float regularRate) {

        this.regularRate = regularRate;
    }
}
