package com.healthIQ.healthIQ.Controllers;

import com.healthIQ.healthIQ.Models.Notification;
import com.healthIQ.healthIQ.Services.NotificationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {
    @Autowired
    private NotificationImpl notificationService;

    @PostMapping("/save")
    public int save(@RequestBody Notification notification){
        notificationService.save(notification);
        return 1;
    }

    @GetMapping("/")
    public List<Notification> findAll(){
        return notificationService.findAll();
    }


    @DeleteMapping("/delete")
    public void delete(@RequestBody Notification notification){
        notificationService.delete(notification);
    }

    @DeleteMapping("/delete/{id}")

    public void deleteById(@PathVariable String id){
        notificationService.deletById(id);
    }

}
