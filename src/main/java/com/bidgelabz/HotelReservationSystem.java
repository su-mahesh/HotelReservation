package com.bidgelabz;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.bidgelabz.Customer.*;


public class HotelReservationSystem {

    private HashMap<String, Hotel> hotels = new HashMap<>();
    private final float minRateDifference = 10;
    String datePattern = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)" +
            "(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)" +
            "0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|" +
            "[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|" +
            "(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{4})$";
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

    public int getTotalWeekDays(LocalDate fromDate, LocalDate toDate) {
        int totalWeekDays = 0;
        for (LocalDate date = fromDate; date.isBefore(toDate) || date.equals(toDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek().getValue() != 6 && date.getDayOfWeek().getValue() != 7)
                totalWeekDays++;
        }
        return totalWeekDays;
    }

    public int getTotalWeekendDays(LocalDate fromDate, LocalDate toDate) {
        int totalWeekendDays = 0;
        for (LocalDate date = fromDate; date.isBefore(toDate) || date.equals(toDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7)
                totalWeekendDays++;
        }
        return totalWeekendDays;
    }


    public Hotel getCheapestBestHotelForRewardCustomer(LocalDate fromDate, LocalDate toDate, Customer_Type customerType) {

        Hotel cheapestHotel = null;
        float cheapestRate = 0;
        float currentRate = 0;
        int bestRating = 0;
        Map<Hotel, Float> hotelRateResult;

        if (customerType.equals(Customer_Type.REWARD_CUSTOMER)) {

            hotelRateResult = hotels.values().stream().collect(Collectors.toMap(hotel -> hotel,
                    hotel -> (hotel.getWeekdayRateForRewardCustomer() * getTotalWeekDays(fromDate, toDate)
                            + hotel.getWeekendRateForRewardCustomer() * getTotalWeekendDays(fromDate, toDate))));
        }
        else {

            hotelRateResult = hotels.values().stream().collect(Collectors.toMap(hotel -> hotel,
                    hotel -> (hotel.getWeekdayRate() * getTotalWeekDays(fromDate, toDate)
                            + hotel.getWeekendRate() * getTotalWeekendDays(fromDate, toDate))));
        }

        hotelRateResult = hotelRateResult.entrySet().stream().sorted(Map.Entry.comparingByValue())
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        for (Map.Entry<Hotel, Float> hotel: hotelRateResult.entrySet()){

            currentRate = hotel.getValue();
            if (cheapestRate == 0) {

                bestRating = hotel.getKey().getRating();
                cheapestRate = currentRate;
                cheapestHotel = hotel.getKey();
            }
            if (Math.abs(cheapestRate - currentRate) <= minRateDifference && Math.abs(bestRating - hotel.getKey().getRating()) == minRatingDifference) {
                cheapestHotel = hotel.getKey();
                cheapestRate = currentRate;

            }
            else if(cheapestRate > currentRate) {
                bestRating = hotel.getKey().getRating();
                cheapestRate = currentRate;
                cheapestHotel = hotel.getKey();
            }
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
                fromDateString = sc.nextLine();

                if (!Pattern.matches(datePattern, fromDateString)){
                    throw new InputMismatchException();
                }
                String[] fromDateStringFormat = fromDateString.split("/");

                fromDate = LocalDate.of(Integer.parseInt(fromDateStringFormat[2]), Integer.parseInt(fromDateStringFormat[1]), Integer.parseInt(fromDateStringFormat[0]));

                System.out.println("Enter to date(dd/mm/yyyy): ");
                toDateString = sc.nextLine();
                if (!Pattern.matches(datePattern, toDateString)){
                    throw new InputMismatchException();
                }
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
                switch (customerType){
                    case 1:
                        return getCheapestBestHotelForRewardCustomer(fromDate, toDate, Customer_Type.NORMAL_CUSTOMER);
                    case 2:
                        return getCheapestBestHotelForRewardCustomer(fromDate, toDate, Customer_Type.REWARD_CUSTOMER);
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

    public static void main(String[] args) {
        HotelReservationSystem h =new HotelReservationSystem();
        h.getCheapestBestHotel();

        System.out.println("Welcome to Hotel reservation program");
    }
}
