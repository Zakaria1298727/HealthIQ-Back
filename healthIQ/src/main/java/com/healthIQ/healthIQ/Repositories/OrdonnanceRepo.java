package com.healthIQ.healthIQ.Repositories;

import com.healthIQ.healthIQ.Models.Ordonnance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdonnanceRepo extends MongoRepository<Ordonnance,String> {
}
