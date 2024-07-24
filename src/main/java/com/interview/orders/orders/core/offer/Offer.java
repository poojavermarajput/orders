package com.interview.orders.orders.core.offer;

public class Offer {
    String name;
    String offerType;

    public Offer() {
    }

    public Offer(String name, String offerType) {
        this.name = name;
        this.offerType = offerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }
}
