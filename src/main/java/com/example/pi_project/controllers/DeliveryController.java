package com.example.pi_project.controllers;


import com.example.pi_project.entities.Delivery;
import com.example.pi_project.entities.DeliveryPerson;
import com.example.pi_project.repositories.DeliveryRepository;
import com.example.pi_project.services.IDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
    @RequestMapping("/deliveries")
    public class DeliveryController {

        // Inject the DeliveryRepository to access and manage delivery data
        @Autowired
        IDeliveryService deliveryService ;


    @Autowired
    private DeliveryRepository deliveryRepository;

// calculate the on-time rating and overall rating using separate methods + return the average of the rating
    //http://localhost:8081/deliveries/rating/average/complex
    @GetMapping("/rating/average/complex")
    public double getComplexRatingAverage() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        double sum = 0;
        int count = 0;
        for (Delivery delivery : deliveries) {
            if (delivery.getRating() > 0 ) {
                double onTimeRating = calculateOnTimeRating(delivery);
                double overallRating = calculateOverallRating(delivery);
                double complexRating = (onTimeRating + overallRating) / 2.0;
                sum += complexRating;
                count++;
            }
        }
        return (count > 0) ? sum / count : 0;
    }

    private double calculateOnTimeRating(Delivery delivery) {
        double onTimeRating = 0;
        if (delivery.getScheduledDeliveryTime() != null ) {
            long scheduledTime = delivery.getScheduledDeliveryTime().getTime();
            long deliveryTime = delivery.getDeliveryTime();
            int minutesLate = (int) ((deliveryTime - scheduledTime) / (1000 * 60));
            if (minutesLate <= 0) {
                onTimeRating = 1.0;
            } else if (minutesLate <= 30) {
                onTimeRating = 0.75;
            } else if (minutesLate <= 60) {
                onTimeRating = 0.5;
            } else {
                onTimeRating = 0.25;
            }
        }
        return onTimeRating;
    }

    private double calculateOverallRating(Delivery delivery) {

        return delivery.getRating();
    }

    // Endpoint to calculate delivery cost for a given delivery
    //http://localhost:8081/deliveries/2/cost
    @GetMapping("/{id}/cost")
    public double calculateDeliveryCost(@PathVariable("id") int id) {
        return deliveryService.calculateDeliveryCost(id);
    }

    //http://localhost:8081/deliveries/deliverypersons/top2ranked
    @GetMapping("/deliverypersons/top2ranked")
    public List<DeliveryPerson> getTop2RankedDeliveryPersons() {
        return deliveryService.getTop2RankedDeliveryPersons();
    }

    //deliveryDate
    //http://localhost:8081/deliveries/deliveryDate?product=lunettes&quantity=50
    @GetMapping("/deliveryDate")
    public ResponseEntity<LocalDate> calculateDeliveryDate(
            @RequestParam("product") String productName,
            @RequestParam("quantity") int quantity) {

        // Suppose que les produits sont disponibles immédiatement
        LocalDate deliveryDate = LocalDate.now();

        // Ajoute des jours supplémentaires de traitement et de livraison en fonction de la quantité
        if (quantity <= 10) {
            deliveryDate = deliveryDate.plusDays(1);
        } else if (quantity <= 50) {
            deliveryDate = deliveryDate.plusDays(3);
        } else {
            deliveryDate = deliveryDate.plusDays(7);
        }

        // Exclut les week-ends et les jours fériés
        while (deliveryDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
                deliveryDate.getDayOfWeek() == DayOfWeek.SUNDAY ||
                isHoliday(deliveryDate)) {
            deliveryDate = deliveryDate.plusDays(1);
        }

        return ResponseEntity.ok(deliveryDate);
    }

    private boolean isHoliday(LocalDate date) {
        // Liste des jours fériés (exemple)
        List<LocalDate> holidays = Arrays.asList(
                LocalDate.of(date.getYear(), Month.JANUARY, 1), // Nouvel An
                LocalDate.of(date.getYear(), Month.MAY, 1), // Fête du Travail
                LocalDate.of(date.getYear(), Month.MARCH, 20) // Fête de l'Indépendance
        );

        // Vérifie si la date correspond à un jour férié
        return holidays.contains(date);
    }
    @PersistenceContext
    private EntityManager entityManager;
    @GetMapping("/search")
    public List<Delivery> searchDeliveries(
            @RequestParam(value = "minRating", required = false) Integer minRating,

            @RequestParam(value = "scheduledDeliveryTime", required = false) Instant scheduledDeliveryTime,
            @RequestParam(value = "actualDeliveryTime", required = false) Instant actualDeliveryTime,
            @RequestParam(value = "signatureConfirmation", required = false) Boolean signatureConfirmation,
            @RequestParam(value = "insurance", required = false) Boolean insurance) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Delivery> query = builder.createQuery(Delivery.class);
        Root<Delivery> root = query.from(Delivery.class);

        List<Predicate> predicates = new ArrayList<>();

        if (minRating != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("rating"), minRating));
        }


        if (scheduledDeliveryTime != null) {
            predicates.add(builder.equal(root.get("scheduledDeliveryTime"), scheduledDeliveryTime));
        }

        if (actualDeliveryTime != null) {
            predicates.add(builder.equal(root.get("actualDeliveryTime"), actualDeliveryTime));
        }

        if (signatureConfirmation != null) {
            predicates.add(builder.equal(root.get("signatureConfirmation"), signatureConfirmation));
        }

        if (insurance != null) {
            predicates.add(builder.equal(root.get("insurance"), insurance));
        }

        query.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Delivery> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }


}



