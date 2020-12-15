package com.example.myflight;

public class DebitCard {

    private String card_id, card_number, card_month, card_year, card_ccv, card_company, uid;

    public DebitCard(String card_id, String card_number, String card_month, String card_year, String card_ccv, String card_company, String uid) {
        this.card_id = card_id;
        this.card_number = card_number;
        this.card_month = card_month;
        this.card_year = card_year;
        this.card_ccv = card_ccv;
        this.card_company = card_company;
        this.uid = uid;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCard_month() {
        return card_month;
    }

    public void setCard_month(String card_month) {
        this.card_month = card_month;
    }

    public String getCard_year() {
        return card_year;
    }

    public void setCard_year(String card_year) {
        this.card_year = card_year;
    }

    public String getCard_ccv() {
        return card_ccv;
    }

    public void setCard_ccv(String card_ccv) {
        this.card_ccv = card_ccv;
    }

    public String getCard_company() {
        return card_company;
    }

    public void setCard_company(String card_company) {
        this.card_company = card_company;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public DebitCard() {

    }
}
