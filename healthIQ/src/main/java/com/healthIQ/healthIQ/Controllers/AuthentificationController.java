package com.healthIQ.healthIQ.Controllers;

import com.healthIQ.healthIQ.Configurations.AuthentificationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthentificationService service;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        boolean emailExists = service.emailExists(request.getEmail());
        if (emailExists) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email déjà utilisé");
        } else {
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
    public ResponseEntity<AuthentificationResponse> register ( @RequestBody AuthentificationRequest request,HttpSession httpSession){
       try{
        AuthentificationResponse authenticationResponse = service.authenticate(request);
        httpSession.setAttribute("Token", authenticationResponse.getToken());
        return ResponseEntity.ok(service.authenticate(request));
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);

       }
    }

    @GetMapping("/registrationConfirm")
    public RedirectView confirm(@RequestParam("confirmToken") String token) {
        service.confirmToken(token);
        log.info("email validated");
        // Rediriger vers la page d'accueil Angular après validation de l'email
        return new RedirectView("http://localhost:4200/Home"); // Assurez-vous de mettre le bon chemin d'accès à votre page d'accueil Angular
    }
    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        // Invalidate the session
        SecurityContextHolder.clearContext();

        // Return a response indicating successful logout
        return ResponseEntity.ok("Logout successful");
    }
    @GetMapping("/forgotPasswordRecovery")
    public ResponseEntity<String> confirmPass(@RequestParam("confirmToken") String token, HttpSession session) {
        service.confirmPassToken(token, session);
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/ChangePassword").build();
    }
    @PostMapping("/newPassword")
    public ResponseEntity<String> newPassword(@RequestParam("email") String email, HttpSession session) {
        session.setAttribute("emailPassRecover", email.toLowerCase());
        service.recoverPassword(email.toLowerCase());
        return ResponseEntity.ok("Password recovery initiated");
    }

    @PostMapping("/changePass")
    public ResponseEntity<String> changePassword(@ModelAttribute("newPassword") String newPassword, HttpSession session) {
        String email = (String) session.getAttribute("emailPassRecover");
        if (email == null) {
            return ResponseEntity.badRequest().body("Email not found in the session");
        }

        service.changePassword(email.toLowerCase(), newPassword);
        session.removeAttribute("emailPassRecover");
        return ResponseEntity.ok("Password changed successfully");
    }
}
