package com.example.homepage;

public class post_job_format {
    //String title, place, pincode, time, date, details, bid = "0", rid, payment;
    String name , description , bid = "0" , rid ,  payment , catagory , price;
    public post_job_format() {
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

    public void setName(String title) {
        this.name = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String place) {
        this.description = place;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String place) {
        this.catagory = place;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String book) {
        this.bid = book;
    }
}
