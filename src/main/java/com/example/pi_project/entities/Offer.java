package com.example.pi_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Long numOffer;
    private String description;
    private Long nbr_offer;
    private float OrderPrice;
    @Temporal(TemporalType.DATE)
    private Date creation_date;
    @Temporal(TemporalType.DATE)
    private Date expiration_date;
    @OneToMany(mappedBy = "offer")
    @JsonIgnore
    private Set<Product> products;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Provider> providers;
}
