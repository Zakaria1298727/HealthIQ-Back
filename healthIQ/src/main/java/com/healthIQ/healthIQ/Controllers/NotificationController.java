package com.healthIQ.healthIQ.Controllers;

import com.healthIQ.healthIQ.Models.Notification;
import com.healthIQ.healthIQ.Services.fasade.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;


    @GetMapping("/")
    public List<Notification> findAll(){
        return notificationService.findAll();
    }

    @GetMapping("/nameSender/{name}")
    public List<Notification>finndeByNameSender(@PathVariable String name){
        return  notificationService.findByNameSender(name);
    }
    @GetMapping("/emailSender/{email}")
    public List<Notification>finndeByEmailSender(@PathVariable String emailSender){
        return  notificationService.findByEmailSender(emailSender);
    }
    @Transactional
    @DeleteMapping("/delete")
    public void delete(Notification notification){
        notificationService.delete(notification);
    }

    @Transactional
    @DeleteMapping("delete/{name}")
    public  void deleteByName(@PathVariable String nameSender){
        notificationService.deleteByName(nameSender);
    }



}
