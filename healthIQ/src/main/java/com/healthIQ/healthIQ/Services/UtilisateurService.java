package com.healthIQ.healthIQ.Services;

import com.healthIQ.healthIQ.Models.Role;
import com.healthIQ.healthIQ.Models.Utilisateur;
import com.healthIQ.healthIQ.Repositories.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UtilisateurService {
    @Autowired(required = false)
    private UtilisateurRepo utilisateurRepo;

    public List<Utilisateur> getAllUtilisateur(){
        return (List<Utilisateur>) utilisateurRepo.findAll();
    }
    public Utilisateur addUtilisateur(Utilisateur utilisateur){
        utilisateur.setRole(Role.ROLE_USER);
        return (Utilisateur) utilisateurRepo.save(utilisateur);
    }
    public void deleteUtilisateur(String id){
        this.utilisateurRepo.deleteById(id);
    }
    public Utilisateur updateUtilisateur(String id, Utilisateur utilisateur){
        Utilisateur updatedUtilisateur = utilisateurRepo.findById(id).get();
        updatedUtilisateur.setFirst_name(utilisateur.getFirst_name());
        updatedUtilisateur.setLast_name(utilisateur.getLast_name());
        updatedUtilisateur.setEmail(utilisateur.getEmail());
        updatedUtilisateur.setPassword((utilisateur.getPassword()));
        updatedUtilisateur.setAdresse(utilisateur.getAdresse());
        updatedUtilisateur.setAge(utilisateur.getAge());
        updatedUtilisateur.setDateNaissance(utilisateur.getDateNaissance());
        return utilisateurRepo.save(updatedUtilisateur);
    }
}
