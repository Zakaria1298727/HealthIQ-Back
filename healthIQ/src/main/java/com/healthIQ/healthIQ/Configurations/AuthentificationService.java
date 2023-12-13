package com.healthIQ.healthIQ.Configurations;

import com.healthIQ.healthIQ.Controllers.*;
import com.healthIQ.healthIQ.Models.*;
import com.healthIQ.healthIQ.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthentificationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthentificationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getNom())
                .lastName(request.getPrenom())
                .email(request.getEmail())
                .ville(request.getVille())
                .adresse(request.getAdresse())
                .poid(request.getPoid())
                .sex(request.getSex())
                .phoneNumber(request.getPhoneNumber())
                .taille(request.getTaille())
                .CodeMedical(request.getCodeMedical())
                .dateNaissance(request.getDateNaissance())
                .sportActif(request.isSportActif())
                .nb_foisSport(request.getNb_foisSport())
                .typeSport(request.getTypeSport())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthentificationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthentificationResponse authenticate(AuthentificationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepo.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthentificationResponse.builder()
                .token(jwtToken)

                .build();
    }
}
