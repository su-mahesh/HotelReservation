package com.bidgelabz;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Scanner;

public class HotelReservationSystem {

    private HashMap<String, Hotel> hotels = new HashMap<>();
    private final float minRateDifference = 10;
    private final int minRatingDifference = 1;

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
            if(cheapestRate > currentRate ) {
                bestRating = hotel.getRating();
                cheapestRate = currentRate;
                cheapestHotel = hotel;
            }


            currentRate = 0;
        }
        if(cheapestHotel != null)
        System.out.println("cheapest hotel: " +cheapestHotel.getHotelName()+ " rating: " +bestRating+ " total price: "+ cheapestRate);
        return cheapestHotel;

    }

    public Hotel getBestRatedHotel(LocalDate fromDate, LocalDate toDate) {

        Hotel bestRatedHotel = null;
        float hotelRate = 0;
        float currentRate = 0;
        int bestRating = 0;

        for (Hotel hotel: hotels.values()){

            for (LocalDate date = fromDate; date.isBefore(toDate) || date.equals(toDate); date = date.plusDays(1)) {
                currentRate += (date.getDayOfWeek().getValue() != 6 && date.getDayOfWeek().getValue() != 7 ) ?
                        hotel.getWeekdayRate() : hotel.getWeekendRate();
            }

            if(bestRating < hotel.getRating()){
                hotelRate = currentRate;
                bestRatedHotel = hotel;
                bestRating = hotel.getRating();
            }


            currentRate = 0;
        }
        if(bestRatedHotel != null)
        System.out.println("cheapest hotel: " +bestRatedHotel.getHotelName()+ " rating: " +bestRating+ " total price: "+ hotelRate);
        return bestRatedHotel;

    }

    public Hotel getCheapestBestHotel(LocalDate fromDate, LocalDate toDate) {

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
        if(cheapestHotel != null)
            System.out.println("cheapest hotel: " +cheapestHotel.getHotelName()+ " rating: " +bestRating+ " total price: "+ cheapestRate);
        return cheapestHotel;
    }


    public Hotel getCheapestBestHotelForRewardCustomer(LocalDate fromDate, LocalDate toDate) {

        Hotel cheapestHotel = null;
        float cheapestRate = 0;
        float currentRate = 0;
        int bestRating = 0;

        for (Hotel hotel: hotels.values()){

            for (LocalDate date = fromDate; date.isBefore(toDate) || date.equals(toDate); date = date.plusDays(1)) {
                currentRate += (date.getDayOfWeek().getValue() != 6 && date.getDayOfWeek().getValue() != 7 ) ?
                        hotel.getWeekdayRateForRewardCustomer() : hotel.getWeekendRateForRewardCustomer();
            }
            if (cheapestRate == 0) {
                bestRating = hotel.getRating();
                cheapestRate = currentRate;
                cheapestHotel = hotel;
            }
            if (Math.abs(cheapestRate - currentRate) <= minRateDifference && Math.abs(bestRating - hotel.getRating()) == minRatingDifference) {
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
        if(cheapestHotel != null)
            System.out.println("cheapest hotel: " +cheapestHotel.getHotelName()+ " rating: " +bestRating+ " total price: "+ cheapestRate);
        return cheapestHotel;
    }

    public Hotel getCheapestBestHotel() {
        boolean flag = true;
        String fromDateString;
        String toDateString;
        LocalDate fromDate = null;
        LocalDate toDate = null;
        while (flag) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter from date(dd/mm/yyyy): ");
                //fromDateString = sc.nextLine();
                fromDateString = "11/9/2020";
                String[] fromDateStringFormat = fromDateString.split("/");

                fromDate = LocalDate.of(Integer.parseInt(fromDateStringFormat[2]), Integer.parseInt(fromDateStringFormat[1]), Integer.parseInt(fromDateStringFormat[0]));

                System.out.println("Enter to date(dd/mm/yyyy): ");
                //toDateString = sc.nextLine();

                toDateString = "12/9/2020";
                String[] toDateStringFormat = toDateString.split("/");
                toDate = LocalDate.of(Integer.parseInt(toDateStringFormat[2]), Integer.parseInt(toDateStringFormat[1]), Integer.parseInt(toDateStringFormat[0]));
                flag = false;

            } catch (Exception e) {
                flag = true;
                System.out.println("wrong input");
            }
        }
        flag = true;
        int customerType;
        while (flag) {
            try {
                System.out.println("enter customer type(1. regular 2. reward): ");
                Scanner sc = new Scanner(System.in);
                customerType = sc.nextInt();
                sc.nextLine();
                switch (customerType){
                    case 1:
                        getCheapestBestHotel(fromDate, toDate);
                        break;
                    case 2:
                        return getCheapestBestHotelForRewardCustomer(fromDate, toDate);
                        default:
                   System.out.println("wrong choice");
                }
                    flag = false;
                } catch (Exception e) {
                    System.out.println("wrong input");
                    flag = true;
                }
            }
        return null;
    }
}
