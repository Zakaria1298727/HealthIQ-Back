package com.healthIQ.healthIQ.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Users")
public class Utilisateur {
    @Id
    private String id;

    private String first_name;
    private String last_name;
    private Role role;

    @Column(unique = true)
    private String email;



    @Column(unique = true)
    private String password;
    private String adresse;
    private int age;
    private String dateNaissance;
}
