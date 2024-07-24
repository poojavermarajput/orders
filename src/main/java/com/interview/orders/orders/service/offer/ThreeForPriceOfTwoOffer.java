package com.interview.orders.orders.service.offer;

public class ThreeForPriceOfTwoOffer implements OfferInterface{

    @Override
    public double calculateOfferPrice(int quantity, double pricePerUnit) {
        return ((((double) quantity /3)*2) + (quantity%3))*pricePerUnit;
    }
}
