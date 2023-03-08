package com.example.pi_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "donation")
public class Donation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_donation;
    private double montant;
    private String type;
    private Boolean affected;

    @OneToMany(mappedBy="donation",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Affectation> affectation1;

@OneToOne
    private Customer customer;

}
