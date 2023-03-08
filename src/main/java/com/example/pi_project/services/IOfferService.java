package com.example.pi_project.services;

import com.example.pi_project.entities.*;

import java.util.List;

public interface IOfferService {


    public List<Request> getAllRequests();
    public List<Offer> getAllOffers();
    public Request addRequest(Request request);


    public Offer addOffer(Offer offer);
    public void deleteRequest(int id);
    public Request updateRequest(Request request);
    public void deleteOffer(int id);
    public Offer updateOffer(Offer offer);
    public void archiveOffer(Offer offer) ;
    public List<Offer> bestOff();
    double cosineSimilarity(String offerDescription, String requestDescription);
    public String statistics();
    public List<Offer> findMatchingOffers(int requestId);


}