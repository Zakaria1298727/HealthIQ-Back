package com.healthIQ.healthIQ.Repositories;

import com.healthIQ.healthIQ.Models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends MongoRepository<Notification,Long> {

    List<Notification> findByNameSender(String name);

    List<Notification> findByEmailSender(String emailSender);

    void deleteByName(String nameSender);
}
