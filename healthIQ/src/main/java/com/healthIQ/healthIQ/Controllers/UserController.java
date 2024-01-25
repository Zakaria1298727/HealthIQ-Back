package com.healthIQ.healthIQ.Controllers;

import com.healthIQ.healthIQ.Models.Doctor;
import com.healthIQ.healthIQ.Models.User;
import com.healthIQ.healthIQ.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> findAll(){
        return userService.findAll();
    }
    @PostMapping("/save")
    public int save(@RequestBody User user){
        userService.save(user);
        return 1;

    }
    @GetMapping("/userOne/{id}")
    public User getUserById(@PathVariable String id){
        return userService.getOne(id);
    }



}
