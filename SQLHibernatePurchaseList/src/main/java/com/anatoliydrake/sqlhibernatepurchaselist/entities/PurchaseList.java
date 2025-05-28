package com.anatoliydrake.sqlhibernatepurchaselist.entities;

import com.anatoliydrake.sqlhibernatepurchaselist.PurchaseListKey;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class PurchaseList {
    @EmbeddedId
    private PurchaseListKey id;
    private int price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public PurchaseListKey getId() {
        return id;
    }

    public void setId(PurchaseListKey id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
