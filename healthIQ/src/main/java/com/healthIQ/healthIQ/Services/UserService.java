package com.healthIQ.healthIQ.Services;

import com.healthIQ.healthIQ.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

}
