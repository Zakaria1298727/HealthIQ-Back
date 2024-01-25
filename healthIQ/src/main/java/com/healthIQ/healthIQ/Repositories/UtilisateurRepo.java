package com.healthIQ.healthIQ.Repositories;

import com.healthIQ.healthIQ.Models.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UtilisateurRepo extends MongoRepository<Utilisateur,String> {

}
