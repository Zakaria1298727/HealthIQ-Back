package com.healthIQ.healthIQ.Repositories;

import com.healthIQ.healthIQ.Confirmation.ConfirmationToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ConfirmationTokenRepo extends MongoRepository<ConfirmationToken,String> {
    Optional<ConfirmationToken> findByToken(String token);

    void deleteByEmail(String email);

}
