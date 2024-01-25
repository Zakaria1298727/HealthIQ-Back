package com.healthIQ.healthIQ.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor(force = true)
@Document(collection = "Event")
public class Event {

    @Id

    public String id;
    public String ID_doc;
    public String title;
    public User Id_patient;
    public String description;
    public String date;
    public String hour;
    public String color;
    public String status;


}
