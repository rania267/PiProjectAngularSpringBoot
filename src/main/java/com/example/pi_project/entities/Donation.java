package com.example.pi_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Donation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private double amount;
    private String currency;
    @Temporal(value =TemporalType.DATE)
    private Date donation_date;
    @Enumerated(EnumType.STRING)
    private PaymentMethod payment_method;
    private String message;
    @OneToMany(mappedBy="donation")
    private Set<Ordeer> orders;




}
