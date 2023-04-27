package com.example.pi_project.entities;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@ToString
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private TypeInvoice TypeInvoice;
    private  String desciption;
    private  float total;
    private LocalDateTime deadline;
    @OneToMany (mappedBy = "invoice")
    private Set<Tax > taxes ;


}
