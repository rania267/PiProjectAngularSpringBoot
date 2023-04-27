package com.example.pi_project.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLocation;
    private String name;
    double Latitude;
    double Longitude;
    double distance;
    private String city;
    private String state;
    private String country;
    @OneToMany(mappedBy = "location")
    private Set<Departement> departements;

    public Location(double latitude, double longitude) {
        latitude = latitude;
        longitude = longitude;
    }

    public Location(String name, double latitude, double longitude) {
        name = name;
        latitude = latitude;
        longitude = longitude;
    }
}