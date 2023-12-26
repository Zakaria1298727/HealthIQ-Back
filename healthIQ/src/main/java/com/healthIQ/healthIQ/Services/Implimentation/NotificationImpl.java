package com.healthIQ.healthIQ.Services.Implimentation;

import com.healthIQ.healthIQ.Models.Notification;
import com.healthIQ.healthIQ.Repositories.NotificationRepo;
import com.healthIQ.healthIQ.Services.fasade.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationImpl implements NotificationService {
    @Override
    public List<Notification> findAll() {
        return notificationRepo.findAll();
    }

    @Override
    public List<Notification> findByNameSender(String nameSender) {
        return notificationRepo.findByNameSender(nameSender);
    }

    @Override
    public List<Notification> findByEmailSender(String emailSender) {
        return notificationRepo.findByEmailSender(emailSender);
    }

    @Override
    @Transactional
    public void delete(Notification notification) {
        notificationRepo.delete(notification);
    }

    @Override
    @Transactional
    public void deleteByName(String nameSender){

        notificationRepo.deleteByName(nameSender);
    }

    @Autowired
    NotificationRepo notificationRepo;
}
