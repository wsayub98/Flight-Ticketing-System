package com.example.myflight;

public class Booking {

    public String booking_id, date_depart, date_return, destination_depart, destination_return, time_depart, time_return, flight_depart, flight_return, type;
    public Float price_depart, price_return, totalprice;
    public int adultQuantity, kidQuantity;
    public Boolean status;

    public Booking() {
    }

    public Booking(String booking_id, String date_depart, String date_return, String destination_depart, String destination_return, String time_depart, String time_return, String flight_depart, String flight_return, String type, Float price_depart, Float price_return, Float totalprice, int adultQuantity, int kidQuantity, Boolean status) {
        this.booking_id = booking_id;
        this.date_depart = date_depart;
        this.date_return = date_return;
        this.destination_depart = destination_depart;
        this.destination_return = destination_return;
        this.time_depart = time_depart;
        this.time_return = time_return;
        this.flight_depart = flight_depart;
        this.flight_return = flight_return;
        this.type = type;
        this.price_depart = price_depart;
        this.price_return = price_return;
        this.totalprice = totalprice;
        this.adultQuantity = adultQuantity;
        this.kidQuantity = kidQuantity;
        this.status = status;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(String date_depart) {
        this.date_depart = date_depart;
    }

    public String getDate_return() {
        return date_return;
    }

    public void setDate_return(String date_return) {
        this.date_return = date_return;
    }

    public String getDestination_depart() {
        return destination_depart;
    }

    public void setDestination_depart(String destination_depart) {
        this.destination_depart = destination_depart;
    }

    public String getDestination_return() {
        return destination_return;
    }

    public void setDestination_return(String destination_return) {
        this.destination_return = destination_return;
    }

    public String getTime_depart() {
        return time_depart;
    }

    public void setTime_depart(String time_depart) {
        this.time_depart = time_depart;
    }

    public String getTime_return() {
        return time_return;
    }

    public void setTime_return(String time_return) {
        this.time_return = time_return;
    }

    public String getFlight_depart() {
        return flight_depart;
    }

    public void setFlight_depart(String flight_depart) {
        this.flight_depart = flight_depart;
    }

    public String getFlight_return() {
        return flight_return;
    }

    public void setFlight_return(String flight_return) {
        this.flight_return = flight_return;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getPrice_depart() {
        return price_depart;
    }

    public void setPrice_depart(Float price_depart) {
        this.price_depart = price_depart;
    }

    public Float getPrice_return() {
        return price_return;
    }

    public void setPrice_return(Float price_return) {
        this.price_return = price_return;
    }

    public Float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Float totalprice) {
        this.totalprice = totalprice;
    }

    public int getAdultQuantity() {
        return adultQuantity;
    }

    public void setAdultQuantity(int adultQuantity) {
        this.adultQuantity = adultQuantity;
    }

    public int getKidQuantity() {
        return kidQuantity;
    }

    public void setKidQuantity(int kidQuantity) {
        this.kidQuantity = kidQuantity;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
