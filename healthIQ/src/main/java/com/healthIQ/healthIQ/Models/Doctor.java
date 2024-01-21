package com.healthIQ.healthIQ.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "doctors")
public class Doctor {
    @Id
    private String id;

    private String first_name;
    private String last_name;
    private LocalTime debutTime;
    private LocalTime finTime;
    private Role role;

    @Column(unique = true)
    private String email;

    private String image;
    private String specilite;
    private Long PrixConsultation;
    private String  naissance;
    private String adresse;
    private String finTime;
    private String debutTime;



    @Column(unique = true)
    private String password;

}
