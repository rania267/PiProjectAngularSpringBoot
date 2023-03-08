package com.example.pi_project.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class ChatMessage {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    String sender;
    private String content;

}
