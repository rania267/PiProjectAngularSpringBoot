package com.example.pi_project.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @Column(name = "CUSTOMER_ID")
    private int id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Transient
    @Column(name = "LAST_NAME")
    private String lastName;
    @Transient
    @Column(name = "EMAIL")
    private String email;
    @Transient
    @Column(name = "GENDER")
    private String gender;
    @Transient
    @Column(name = "CIN")
    private int CIN;
    @Column(name = "Age")
    private int Age;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;
    @Column(name = "nombre_enfance")
    private String nombre_enfance;
    @Column(name = "Etat_civil")
    private String Etat_civil;
    @Column(name = "Bourse")
    private String Bourse;
    @Column(name = "Score")
    private double Score;
    @Column(name="type")
    private String type;
    @OneToOne(mappedBy = "customer")
    private Donation donation;




}
