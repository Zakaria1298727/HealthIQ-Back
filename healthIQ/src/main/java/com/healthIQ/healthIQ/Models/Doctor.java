package com.healthIQ.healthIQ.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Users")
public class Doctor {
    @Id
    private String id;

    private String first_name;
    private String last_name;
    private String debutTime;
    private String finTime;
    private Role role;

    @Column(unique = true)
    private String email;



    @Column(unique = true)
    private String password;


    private String adresse;
    private int age;
    private Double prixConsultation;
    private String operation;
    private String specialite;
    private String dateNaissance;

}
