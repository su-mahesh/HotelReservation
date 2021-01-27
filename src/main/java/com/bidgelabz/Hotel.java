package com.bidgelabz;

public class Hotel {
    private String hotelName;
    private float regularRate;
    private float weekdayRate;
    private float weekendRate;

    public Hotel(String hotelName) {
        this.hotelName = hotelName.substring(0, 1).toUpperCase() + hotelName.substring(1).toLowerCase();
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setRegularRate(float regularRate) {

        this.regularRate = regularRate;
    }

    public float getRegularRate() {
        return regularRate;
    }

    public void setWeekdayRate(float weekdayRate) {
        this.weekdayRate = weekdayRate;
    }

    public void setWeekendRate(float weekendRate) {
        this.weekendRate = weekendRate;
    }

    public float getWeekdayRate() {
        return weekdayRate;
    }

    public float getWeekendRate() {
        return weekendRate;
    }
}
