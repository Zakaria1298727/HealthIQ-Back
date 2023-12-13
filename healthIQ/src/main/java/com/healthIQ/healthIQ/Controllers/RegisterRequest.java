package com.healthIQ.healthIQ.Controllers;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    private String Nom;
    private String prenom;
    private String email;
    private String password;
    private String ville;
    private String adresse;
    private Double poid;
    private String dateNaissance;
    private String CodeMedical;
    private Integer phoneNumber;
    private String sex;
    private boolean sportActif;
    private Double taille;
    private String typeSport;
    private Integer nb_foisSport;

}