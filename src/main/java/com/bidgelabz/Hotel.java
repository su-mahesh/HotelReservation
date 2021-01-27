package com.bidgelabz;

public class Hotel {
    private String hotelName;
    private float regularRate;
    private float weekdayRate;
    private float weekendRate;
    private int rating;

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

    public void rateHotel(int rating) {
        if (rating < 6 && rating > 0)
            this.rating = rating;
        else
            System.out.println("wrong input, rating(1 - 5)");
    }

    public int getRating() {
        return rating;
    }
}
