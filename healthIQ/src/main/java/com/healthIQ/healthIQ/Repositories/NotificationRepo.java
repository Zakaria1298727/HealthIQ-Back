package com.healthIQ.healthIQ.Repositories;

import com.healthIQ.healthIQ.Models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends MongoRepository<Notification, String> {



}
