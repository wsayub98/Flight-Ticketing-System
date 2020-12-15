package com.example.myflight;

public class BookingFlightInfo {

    private String depart_destination, depart_flight, return_destination, return_flight;
    private Float depart_price, return_price;
    private String depart_date, return_date, depart_time, return_time;

    public BookingFlightInfo(String depart_destination, String depart_flight, String return_destination, String return_flight, Float depart_price, Float return_price, String depart_date, String return_date, String depart_time, String return_time) {
        this.depart_destination = depart_destination;
        this.depart_flight = depart_flight;
        this.return_destination = return_destination;
        this.return_flight = return_flight;
        this.depart_price = depart_price;
        this.return_price = return_price;
        this.depart_date = depart_date;
        this.return_date = return_date;
        this.depart_time = depart_time;
        this.return_time = return_time;
    }

    public String getDepart_destination() {
        return depart_destination;
    }

    public String getDepart_flight() {
        return depart_flight;
    }

    public String getReturn_destination() {
        return return_destination;
    }

    public String getReturn_flight() {
        return return_flight;
    }

    public Float getDepart_price() {
        return depart_price;
    }

    public Float getReturn_price() {
        return return_price;
    }

    public String getDepart_date() {
        return depart_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public String getDepart_time() {
        return depart_time;
    }

    public String getReturn_time() {
        return return_time;
    }
}
