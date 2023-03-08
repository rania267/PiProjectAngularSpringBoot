package com.example.pi_project.services;

import com.example.pi_project.entities.*;
import com.example.pi_project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OfferServiceImpl implements IOfferService {
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    OfferRepository offerRepository;
    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }
    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }



    //---------------------------archive-----------------------------------------------
    //@Override
    public void archiveOffer(Offer offer) {
        if (offer.getNb_dislike_offer() > 100) {
            offer.setArchived(true);
        }
    }
    //------------------------bestOff-------------------------------------------------
    @Override
    public List<Offer> bestOff() {
        List<Offer> offers = offerRepository.findTop3ByArchivedOrderByNbLikeOfferDesc(false);
        if (offers.size() == 3) {
            for (Offer offer : offers) {
                if (!offer.getHasDiscount()) { // Vérification de la propriété hasDiscount
                    if (offer == offers.get(0)) {
                        offer.setOrderPrice(offer.getOrderPrice() * 0.8f);
                    } else if (offer == offers.get(1)) {
                        offer.setOrderPrice(offer.getOrderPrice() * 0.85f);
                    } else if (offer == offers.get(2)) {
                        offer.setOrderPrice(offer.getOrderPrice() * 0.9f);
                    }
                    offer.setHasDiscount(true); // Mise à jour de la propriété hasDiscount
                }
            }
            offerRepository.saveAll(offers);
        }
        return offers;
    }


    //---------------------------------------- Similarity -------------------------------------------------
    @Override
    public double cosineSimilarity(String offerDescription, String requestDescription) {
        String[] offerWords = offerDescription.toLowerCase().split("[^a-zA-Z]+");
        String[] requestWords = requestDescription.toLowerCase().split("[^a-zA-Z]+");

        Map<String, Integer> offerFreq = new HashMap<>();
        Map<String, Integer> requestFreq = new HashMap<>();

        // calculer les fréquences de chaque mot dans les deux descriptions
        for (String word : offerWords) {
            offerFreq.put(word, offerFreq.getOrDefault(word, 0) + 1);
        }

        for (String word : requestWords) {
            requestFreq.put(word, requestFreq.getOrDefault(word, 0) + 1);
        }

        // calculer le produit point entre les deux vecteurs
        double dotProduct = 0;
        for (String word : offerFreq.keySet()) {
            if (requestFreq.containsKey(word)) {
                dotProduct += offerFreq.get(word) * requestFreq.get(word);
            }
        }

        // calculer les normes des deux vecteurs
        double offerNorm = 0;
        for (int freq : offerFreq.values()) {
            offerNorm += freq * freq;
        }
        offerNorm = Math.sqrt(offerNorm);

        double requestNorm = 0;
        for (int freq : requestFreq.values()) {
            requestNorm += freq * freq;
        }
        requestNorm = Math.sqrt(requestNorm);

        // calculer le cosinus de l'angle entre les deux vecteurs
        double cosineSimilarity = dotProduct / (offerNorm * requestNorm);

        return cosineSimilarity;
    }
    //-----------------------------------statistics-----------------------------------------------
    @Override
    public String statistics() {
        List<Offer> offers = offerRepository.findAll();
        List<Request> requests = requestRepository.findAll();

        int confirmedRequests = 0;
        for (Request request : requests) {
            if (request.getStateRequest() == StateRequest.confirmed) {
                for (Offer offer : offers) {
                    if (cosineSimilarity(offer.getDescription(), request.getDescription()) >= 0.7
                            && offer.getOrderPrice() <= request.getBudget()) {
                        confirmedRequests++;
                        break;
                    }
                }
            }
        }

        double percentage = ((double) confirmedRequests / requests.size()) * 100;
        return "Le pourcentage des demandes confirmées et dont le budget est Supérieur ou égal au prix de l'offre est de " + percentage + "%";
    }


    //------------------------------------------------------------------
    @Override
    public List<Offer> findMatchingOffers(int requestId) {
        Request request = requestRepository.findById(requestId).orElse(null);
        if (request == null) {
            return Collections.emptyList(); // retourne une liste vide si la requête n'existe pas
        }
        // Parcourir toutes les offres non archivées
        List<Offer> offers = offerRepository.findByArchived(false);
        List<Offer> matchingOffers = new ArrayList<>();
        for (Offer offer : offers) {
            if (!offer.isArchived() && offer.getExpiration_date().compareTo(new Date()) > 0
                    && offer.getOrderPrice() <= request.getBudget()) {
                double similarity = cosineSimilarity(offer.getDescription(), request.getDescription());
                if (similarity >= 0.6) {
                    matchingOffers.add(offer);
                }
            }
        }
        // Trier les offres similaires par ordre décroissant de nombre de likes
        Collections.sort(matchingOffers, Comparator.comparing(Offer::getNbLikeOffer).reversed());

        // Appliquer la méthode BestOff pour les trois premières offres similaires
        if (matchingOffers.size() >= 3) {
            bestOff();
        }
        return matchingOffers;
    }
    //-----------------------------------------------------------------------------------
    @Override
    public Request addRequest(Request request) {
        return  requestRepository.save(request);

    }

    @Override
    public Offer addOffer(Offer contract) {
        return offerRepository.save(contract);
    }
    @Override
    public Offer updateOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public void deleteOffer(int id) {
        offerRepository.deleteById(id);
    }
    @Override
    public Request updateRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public void deleteRequest(int id) {
        requestRepository.deleteById(id);
    }




    //------------------------------------------------------------------------------------------------------
        /*public List<Offer> findSimilarOffers(int requestId) {
        // Récupérer la demande correspondante à l'id donné
        Optional<Request> request = requestRepository.findById(requestId);

        // Créer une liste pour stocker les offres similaires
        List<Offer> similarOffers = new ArrayList<>();

        // Parcourir toutes les offres non archivées
        List<Offer> allOffers = offerRepository.findByArchived(false);
        for (Offer offer : allOffers) {
            // Vérifier la similarité entre la demande et l'offre
            double similarity = cosineSimilarity(request.getDescription(), offer.getDescription());
            if (similarity >= 0.5) { // Exemple de seuil de similarité, à ajuster selon les besoins
                // Ajouter l'offre à la liste des offres similaires
                similarOffers.add(offer);
                // Mettre à jour le nombre de likes de l'offre
                offer.setNbLikeOffer(offer.getNbLikeOffer() + 1);
                offerRepository.save(offer);
            }
        }

        // Trier les offres similaires par ordre décroissant de nombre de likes
        Collections.sort(similarOffers, Comparator.comparing(Offer::getNbLikeOffer).reversed());

        // Appliquer la méthode BestOff pour les trois premières offres similaires
        if (similarOffers.size() >= 3) {
            bestOff(similarOffers.get(0), similarOffers.get(1), similarOffers.get(2));
        }

        return similarOffers;
    }
    */



}
