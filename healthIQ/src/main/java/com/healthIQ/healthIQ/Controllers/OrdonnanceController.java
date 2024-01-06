package com.healthIQ.healthIQ.Controllers;

import com.healthIQ.healthIQ.Models.Ordonnance;
import com.healthIQ.healthIQ.Services.OrdonnanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/doctor/Ordonnance")
public class OrdonnanceController {
    @Autowired
    private OrdonnanceService OrdonnanceService;

    @PostMapping("/add")
    public ResponseEntity<?> addOrdonnance(@RequestBody Ordonnance Or){
       try{
            this.OrdonnanceService.addOrdonnance(Or);
            return new ResponseEntity<>("Ordonnance ajouter "+Or, HttpStatus.OK);

    }catch (Exception e){
           return new ResponseEntity<>("Failed ro add Ordonnance",HttpStatus.INTERNAL_SERVER_ERROR);
       }
}

    @GetMapping("/getAll")
    public List<Ordonnance> getAll(){
       return this.OrdonnanceService.getAll();
    }
    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") String id){
       try{
      Optional<Ordonnance> Or= this.OrdonnanceService.getOne(id);
           return new ResponseEntity<>(Or,HttpStatus.OK);

       }catch (Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}
