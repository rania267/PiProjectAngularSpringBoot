package com.example.pi_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class DeliveryExperience implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;
    private String deliverer;
   String service ;
    @Temporal(TemporalType.TIMESTAMP)
    Date  dateEvaluation ;
    private long deliveryTime;

    private LocalDateTime plannedDeliveryTime;

    public DeliveryExperience(LocalDateTime plannedDeliveryTime) {
        this.plannedDeliveryTime = plannedDeliveryTime;
    }

    public LocalDateTime getPlannedDeliveryTime() {
        return plannedDeliveryTime;
    }

    public void setPlannedDeliveryTime( LocalDateTime plannedDeliveryTime) {
        this.plannedDeliveryTime = plannedDeliveryTime;
    }

    private LocalDateTime actualDeliveryTime;

    // other fields and methods omitted for brevity

    public LocalDateTime getActualDeliveryTime() {
        return actualDeliveryTime;
    }
    private double distanceTraveled;

    // other properties and methods

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    private int customerSatisfaction;

    public int getCustomerSatisfaction() {
        return customerSatisfaction;
    }

    public void setCustomerSatisfaction(int customerSatisfaction) {
        this.customerSatisfaction = customerSatisfaction;
    }
    @ManyToOne(fetch = FetchType.LAZY)
@JsonIgnore
    private DeliveryPerson deliveryPerson;
    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

   // public DeliveryExperience( int note) {
    //    this.note = note;

    //}
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore

    private DeliveryPackage deliveryPackage;
}


