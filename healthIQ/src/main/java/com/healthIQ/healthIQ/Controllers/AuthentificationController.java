package com.healthIQ.healthIQ.Controllers;

import com.healthIQ.healthIQ.Configurations.AuthentificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthentificationService service;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        boolean emailExists = service.emailExists(request.getEmail());

        if (emailExists) {
            // Si l'email existe déjà, renvoyer une réponse avec le code d'erreur 401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email déjà utilisé");
        } else {
            // Si l'email n'existe pas, procéder à l'enregistrement normal
            return ResponseEntity.ok(service.register(request));
        }
    }

    @PostMapping("/emailexists")
    public ResponseEntity<?> checkEmailExists(@RequestBody RegisterRequest request) {
        boolean emailExists = service.emailExists(request.getEmail());

        if (!emailExists) {
            // Si l'email existe déjà, renvoyer une réponse vide (ne rien faire)
            return ResponseEntity.ok().build();
        } else {
            // Si l'email n'existe pas, renvoyer une réponse avec le code d'erreur 401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email existant");
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthentificationResponse> register(
            @RequestBody AuthentificationRequest request
    ){
        
        return ResponseEntity.ok(service.authenticate(request));
    }

}
