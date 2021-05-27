package com.example.homepage;

public class post_details_format {
   // String title, place, pincode, time, date, details, bid = "0", rid, payment;
    String name , description , payment , bid = "0" , rid , catagory;

    public post_details_format() {
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String place) {
        this.description = place;
    }


    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String details) {
        this.catagory = details;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String book) {
        this.bid = book;
    }
}
