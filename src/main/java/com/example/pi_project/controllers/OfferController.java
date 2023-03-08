package com.example.pi_project.controllers;

import com.example.pi_project.entities.*;
import com.example.pi_project.repositories.OfferRepository;
import com.example.pi_project.services.IOfferService;
import com.example.pi_project.services.IPiService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class OfferController {
    @Autowired
    IOfferService piService;
    @Autowired
    OfferRepository OfferRepository;











    //---------
    @GetMapping("/AllOffers")
    @ResponseBody
    public List<String> getAllOffers() {
        List<String> offers = new ArrayList<>();
        for (Offer offer : piService.getAllOffers()) {
            if (offer.getNb_dislike_offer() > 100) {
                offers.add("Cette offre est archivée");
            } else {
                offers.add("Numéro de l'offre : " + offer.getNumOffer() + ", Description : " + offer.getDescription() + ", Nombre d'offres : " + offer.getNbr_offer() + ", Prix de l'offre : " + offer.getOrderPrice() + ", Date de création : " + offer.getCreation_date() + ", Date d'expiration : " + offer.getExpiration_date() + ", Nombre de mentions j'aime : " + offer.getNbLikeOffer() + ", Nombre de mentions je n'aime pas : " + offer.getNb_dislike_offer()
                );
            }
        }
        return offers;
    }

    @GetMapping("/AllRequests")
    @ResponseBody
    public List<Request> getAllRequests() {
        return piService.getAllRequests();
    }

    @PostMapping("/addRequest")
    @ResponseBody
    public Request addDelivery(@RequestBody Request request) {
        return piService.addRequest(request);
    }

    @PostMapping("/addOffer")
    public Offer addContract(@RequestBody Offer offer) {
        return piService.addOffer(offer);
    }

    @PutMapping("/updateRequest")
    @ResponseBody
    public Request updateRequest(@RequestBody Request request) {
        return piService.updateRequest(request);
    }

    @PutMapping("/updateOffer")
    @ResponseBody
    public Offer updateOffer(@RequestBody Offer offer) {
        return piService.updateOffer(offer);
    }

    @DeleteMapping("/deleteRequest/{id}")
    @ResponseBody
    public void deleteRequest(@PathVariable("id") int id) {
        piService.deleteRequest(id);
    }


    @DeleteMapping("/deleteOffer/{id}")
    @ResponseBody
    public void deleteOffer(@PathVariable("id") int id) {
        piService.deleteOffer(id);
    }


    //--------------------------------------best-off-----------------------------------------------------
    @GetMapping("/best-off")
    public ResponseEntity<List<Offer>> getBestOff() {
        List<Offer> offers = piService.bestOff();
        if (offers.size() == 3) {
            StringBuilder message = new StringBuilder();
            message.append("Remise de 20% sur l'offre ").append(offers.get(0).getId());
            offers.get(0).setDescription(message.toString());
            message.setLength(0);
            message.append("Remise de 15% sur l'offre ").append(offers.get(1).getId());
            offers.get(1).setDescription(message.toString());
            message.setLength(0);
            message.append("Remise de 10% sur l'offre ").append(offers.get(2).getId());
            offers.get(2).setDescription(message.toString());
            OfferRepository.saveAll(offers);
        }
        return ResponseEntity.ok(offers);
    }
    //----------------------------------------------------------------------------------------------
    @GetMapping("/cosine-similarity")
    public double cosineSimilarity(@RequestParam String offerDescription, @RequestParam String requestDescription) {
        return piService.cosineSimilarity(offerDescription, requestDescription);
    }
    //-------------------------------------------------------------------------------------------
    @GetMapping("/statistics")
    public ResponseEntity<String> getStatistics() {
        String result = piService.statistics();
        return ResponseEntity.ok(result);
    }
    //---------------------------------------------------------------------------------------------------
    /*@GetMapping("/requests/{requestId}/matching-offers")
    public ResponseEntity<List<Offer>> getMatchingOffers(@PathVariable int requestId) {
        List<Offer> matchingOffers = piService.findMatchingOffers(requestId);
        if (matchingOffers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(matchingOffers);
        }
    }*/
    //------------

    @GetMapping("/requests/{id}/matching-offers")
    public ResponseEntity<List<Offer>> getMatchingOffers(@PathVariable("id") int requestId) {
        List<Offer> matchingOffers = piService.findMatchingOffers(requestId);
        if (matchingOffers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(matchingOffers);
        }
    }



}



