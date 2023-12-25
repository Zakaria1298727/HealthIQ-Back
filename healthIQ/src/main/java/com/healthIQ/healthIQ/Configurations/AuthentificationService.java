package com.healthIQ.healthIQ.Configurations;

import com.healthIQ.healthIQ.Confirmation.ConfirmationToken;
import com.healthIQ.healthIQ.Controllers.*;
import com.healthIQ.healthIQ.Models.*;
import com.healthIQ.healthIQ.Services.*;
import com.healthIQ.healthIQ.Confirmation.Email.*;
import com.healthIQ.healthIQ.Repositories.UserRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthentificationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ConfirmationTokenService tokenService;
    private final EmailSender emailSender;
    private final UserService userService;


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

        var jwtToken = jwtService.generateToken(user);
        userRepo.save(user);

        String confToken = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                confToken,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                user.getEmail()
        );

        tokenService.saveConfirmationToken(confirmationToken);

// Construire le lien en utilisant localhost:8080
        String localhostLink = "http://localhost:8080/registrationConfirm?confirmToken=" + confToken;
        String purpose = "Thank you for registering. Please click on the below link to activate your account:";

// Envoyer l'email en utilisant le lien localhost
        emailSender.send(user.getEmail(), buildEmail(user.getFirstName(), localhostLink, purpose, "Activate Now", "Confirm Your Email"));
        log.info("test111111111");

// Retourner la réponse appropriée
        return AuthentificationResponse.builder().token(jwtToken).confirmationToken(confToken).build();

    }

    public AuthentificationResponse authenticate(AuthentificationRequest request) {

        var user = userRepo.findByEmail(request.getEmail().toLowerCase())
                .orElseThrow(() -> new IllegalStateException("Invalid email or password!"));

        if(!user.getEnabled()){
            throw  new IllegalStateException("Email is not validated!");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail().toLowerCase(),
                            request.getPassword()
                    )
            );
        }catch (AuthenticationException e){
            throw new IllegalStateException("Invalid email or password!");
        }



        var jwtToken = jwtService.generateToken(user);

        return AuthentificationResponse.builder().token(jwtToken).build();
    }

    public boolean emailExists(String email){
        return userRepo.findByEmail(email).isPresent();
    }

    private String buildEmail(String name, String link,String purpose, String linkMessage , String title) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">"+ title +"</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">" + purpose + "</p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">" + linkMessage + "</a> </p></blockquote>\n Link will expire in 30 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
    public void confirmToken(String token) {
        ConfirmationToken confirmationToken = tokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        tokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getEmail().toLowerCase());
        log.info("email confirmed");
    }

    public void confirmPassToken(String token, HttpSession session) {
        ConfirmationToken confirmationToken = tokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Invalid Token pass!");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            session.removeAttribute("emailPassRecover");
            throw new IllegalStateException("token expired pass!");
        }
        tokenService.setConfirmedAt(token);
    }

    public void regenerateToken(String email){

        var user = userRepo.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new IllegalStateException("Email does not exist!"));

        tokenService.deleteTokenByEmail(email.toLowerCase());

        String confToken = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                confToken,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                email.toLowerCase()
        );

        tokenService.saveConfirmationToken(confirmationToken);


// Construire le lien en utilisant localhost:8080
        String localhostLink = "http://localhost:8080/registrationConfirm?confirmToken=" + confToken;
        String purpose = "Thank you for registering. Please click on the below link to activate your account:";

// Envoyer l'email en utilisant le lien localhost
        emailSender.send(user.getEmail(), buildEmail(user.getFirstName(), localhostLink, purpose, "Activate Now", "Confirm Your Email"));

    }
    public void recoverPassword(String email){

        var user = userRepo.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new IllegalStateException("Email recover does not exist!"));//il faut supprimer session apres cela(cas d'echoue)

        tokenService.deleteTokenByEmail(email.toLowerCase());

        String confToken = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                confToken,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                email.toLowerCase()
        );

        tokenService.saveConfirmationToken(confirmationToken);

        String link = "http://localhost:8080/forgotPasswordRecovery?confirmToken="+confToken;
        String purpose = " Please click on the link below to renew your password: ";

        emailSender.send(user.getEmail().toLowerCase(),buildEmail(user.getFirstName().concat(" ").concat(user.getLastName()),link, purpose, "Renew Password", "Renew Your Password"));
    }
    public void changePassword(String email, String newPassword) {
        userService.changePassword(email.toLowerCase(),newPassword);
    }
}
