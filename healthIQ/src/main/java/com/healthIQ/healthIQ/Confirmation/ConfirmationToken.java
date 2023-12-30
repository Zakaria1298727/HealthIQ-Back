package com.healthIQ.healthIQ.Confirmation;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document(collection = "ConfirmationTokens")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationToken {

    @Id
    private String id;
    @NotBlank
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime confirmedAt;
    private String email;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, String email){
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.email = email;
    }

}
