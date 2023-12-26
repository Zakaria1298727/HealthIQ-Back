package com.healthIQ.healthIQ.Services.fasade;

import com.healthIQ.healthIQ.Models.Notification;
import jakarta.transaction.Transactional;

import java.util.List;

public interface NotificationService  {
    List<Notification> findAll();

    List<Notification> findByNameSender(String nameSender);

    List<Notification> findByEmailSender(String emailSender);

    void delete(Notification notification);


    void deleteByName(String nameSender);
}
