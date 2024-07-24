package com.interview.orders.orders.service;

import com.interview.orders.orders.core.offer.Offer;
import com.interview.orders.orders.data.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service public class OfferService {
    OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public void createOffer(List<Offer> offer) {
        // optimization validate item is available in inventory or not before adding offer
        offerRepository.save(offer);
    }

    public Map<String, Offer> getOfferByInventoryNames(List<String> inventoryNames) {
        return offerRepository.getOfferByInventoryNames(inventoryNames);
    }
}
