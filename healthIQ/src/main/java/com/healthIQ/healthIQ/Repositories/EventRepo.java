package com.healthIQ.healthIQ.Repositories;

import com.healthIQ.healthIQ.Models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends MongoRepository<Event, String> {

}
