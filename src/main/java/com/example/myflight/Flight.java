package com.example.myflight;

public class Flight {

    private String time_depart, time_return, flight_depart, flight_return;
    private Double price_depart, price_return, totalprice;

    public Flight (){

    }
    public Flight(String time_depart, String time_return, String flight_depart, String flight_return, Double price_depart, Double price_return, Double totalprice) {
        this.time_depart = time_depart;
        this.time_return = time_return;
        this.flight_depart = flight_depart;
        this.flight_return = flight_return;
        this.price_depart = price_depart;
        this.price_return = price_return;
        this.totalprice = totalprice;
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

    public Double getPrice_depart() {
        return price_depart;
    }

    public void setPrice_depart(Double price_depart) {
        this.price_depart = price_depart;
    }

    public Double getPrice_return() {
        return price_return;
    }

    public void setPrice_return(Double price_return) {
        this.price_return = price_return;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }
}