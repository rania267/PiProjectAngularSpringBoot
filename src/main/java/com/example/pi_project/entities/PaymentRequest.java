package com.example.pi_project.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@ToString
@Entity
@Getter
@Setter

public class PaymentRequest {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private Long amount;
    private String currency;
    private String paymentMethodId;
    private String customerName;
    private String customerEmail;
}
