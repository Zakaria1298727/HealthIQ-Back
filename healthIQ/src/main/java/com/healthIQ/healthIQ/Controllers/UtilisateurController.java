package com.healthIQ.healthIQ.Controllers;

import com.healthIQ.healthIQ.Models.Utilisateur;
import com.healthIQ.healthIQ.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/utilisateur")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/list")
    public List<Utilisateur> getAllUtilisateur(){
        return utilisateurService.getAllUtilisateur();
    }
    @PostMapping("/add")
    public Utilisateur addUtilisateur(@RequestBody Utilisateur utilisateur){
        return utilisateurService.addUtilisateur(utilisateur);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUtilisateur(@PathVariable String id){
        this.utilisateurService.deleteUtilisateur(id);
    }
    @PutMapping("/update/{id}")
    public Utilisateur updateUtilisateur(@PathVariable String id, @RequestBody Utilisateur utilisateur){
        return utilisateurService.updateUtilisateur(id,utilisateur);
    }
}
