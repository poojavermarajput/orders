package com.interview.orders.orders.core.offer;

public enum OfferType {
    BUY_ONE_GET_ONE_FREE("BUY_ONE_GET_ONE_FREE"),
    THREE_FOR_PRICE_OF_TWO("THREE_FOR_PRICE_OF_TWO");

    String offerDescription;
    OfferType(String offerDescription) {
        this.offerDescription = offerDescription;
    }
}
