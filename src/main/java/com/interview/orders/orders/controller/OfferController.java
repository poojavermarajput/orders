package com.interview.orders.orders.controller;

import com.interview.orders.orders.core.offer.Offer;
import com.interview.orders.orders.service.OfferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController {

    OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/offer")
    public void createOffer(@RequestBody List<Offer> offers) {
        offerService.createOffer(offers);
    }
}
