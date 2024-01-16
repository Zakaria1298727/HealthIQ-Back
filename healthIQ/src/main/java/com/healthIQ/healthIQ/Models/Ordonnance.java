package com.healthIQ.healthIQ.Models;

import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Ordonnance {
    @Id
    public String id;
    public String Nom_medicament;
    public String ID_patient;
    public String Prenom_patient;
    public String  Nom_patient;
    public String nom_Doc;
    public String  Prenom_Doc;
    public String ID_Doc;
    public String  description;
}
