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
    public String nom_Ordo;
    public String ID_Patient;
    public String ID_Doc;
    public String  description;
}
