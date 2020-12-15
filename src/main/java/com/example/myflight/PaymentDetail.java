package com.example.myflight;

public class PaymentDetail {
    private String payment_id, booking_id, user_id, card_number, status, price;

    public PaymentDetail() {
    }

    public PaymentDetail(String payment_number, String booking_id, String user_id, String card_number, String status, String price) {
        this.payment_id = payment_number;
        this.booking_id = booking_id;
        this.user_id = user_id;
        this.card_number = card_number;
        this.status = status;
        this.price = price;
    }

    public String getPayment_number() {
        return payment_id;
    }

    public void setPayment_number(String payment_number) {
        this.payment_id = payment_number;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
