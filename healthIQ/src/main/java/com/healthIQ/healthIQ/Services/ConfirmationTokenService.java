package com.healthIQ.healthIQ.Services;

import com.healthIQ.healthIQ.Confirmation.ConfirmationToken;
import com.healthIQ.healthIQ.Repositories.ConfirmationTokenRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepo confirmationTokenRepo;

    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepo.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepo.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        Optional<ConfirmationToken> optionalToken = confirmationTokenRepo.findByToken(token);
        if (optionalToken.isPresent()) {
            ConfirmationToken existingToken = optionalToken.get();
            existingToken.setConfirmedAt(LocalDateTime.now());
            confirmationTokenRepo.save(existingToken);
        }
    }

    public void deleteTokenByEmail(String email) {
        confirmationTokenRepo.deleteByEmail(email.toLowerCase());
    }


}
