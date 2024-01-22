package com.healthIQ.healthIQ.Models;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Document("Notification")

public class Notification {
    @Id
    private String id;

    private String namesender;


    private String emailsender;
    private String phone;
    private String content;


}
