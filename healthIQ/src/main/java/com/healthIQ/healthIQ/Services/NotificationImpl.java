package com.healthIQ.healthIQ.Services;

import com.healthIQ.healthIQ.Models.Notification;
import com.healthIQ.healthIQ.Repositories.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class NotificationImpl  {

    public List<Notification> findAll() {
        return notificationRepo.findAll();
    }

    public int save (@RequestBody Notification notification){
        notificationRepo.save(notification);
        return 1;
    }

    public  void deletById(String id){
        notificationRepo.deleteById(id);

    }


    public void delete(Notification notification) {
        notificationRepo.delete(notification);
    }
    @Autowired
    NotificationRepo notificationRepo;
}
