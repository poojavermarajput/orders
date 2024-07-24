package com.interview.orders.orders.service.offer;

public class BuyOneGetOneOffer implements OfferInterface{
    @Override
    public double calculateOfferPrice(int quantity, double pricePerUnit) {
        return (((double) quantity /2) + (quantity%2))*pricePerUnit;
    }
}
