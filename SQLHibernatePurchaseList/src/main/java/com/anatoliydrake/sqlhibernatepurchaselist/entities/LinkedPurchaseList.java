package com.anatoliydrake.sqlhibernatepurchaselist.entities;

import com.anatoliydrake.sqlhibernatepurchaselist.SubscriptionKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class LinkedPurchaseList {
    @EmbeddedId
    private SubscriptionKey id;

    public SubscriptionKey getId() {
        return id;
    }

    public void setId(SubscriptionKey id) {
        this.id = id;
    }
}
