package com.interview.orders.orders.data;

import com.interview.orders.orders.core.offer.Offer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class OfferRepository {
    Map<String, Offer> offers = new HashMap<>();
    public void save(List<Offer> offersList) {
        offersList.stream().forEach(offer->offers.put(offer.getName(), offer));
    }

    public Map<String, Offer> getOfferByInventoryNames(List<String> inventoryNames) {
        return inventoryNames.stream().map(inventoryName-> offers.get(inventoryName))
                .filter(Objects::nonNull)
                .collect(
                Collectors
                        .toMap(
                                Offer::getName,
                                Function.identity()));
    }
}
