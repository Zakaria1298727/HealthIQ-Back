package com.healthIQ.healthIQ.Controllers;

import com.healthIQ.healthIQ.Configurations.AuthentificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthentificationService service;
    @PostMapping("/register")
    public ResponseEntity<AuthentificationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthentificationResponse> register(
            @RequestBody AuthentificationRequest request
    ){
        
        return ResponseEntity.ok(service.authenticate(request));
    }

}
