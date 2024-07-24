package com.interview.orders.orders.service.offer;

import org.springframework.stereotype.Service;

@Service
public class OfferFactory {
    public OfferInterface getOffer(String offerType) {
        switch (offerType) {
            case "BUY_ONE_GET_ONE_FREE":
                return new BuyOneGetOneOffer();
            case "THREE_FOR_PRICE_OF_TWO":
                return new ThreeForPriceOfTwoOffer();
            default:
                throw new IllegalArgumentException("Unknown offer type: " + offerType);
        }
    }
}
