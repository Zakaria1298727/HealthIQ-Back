package com.healthIQ.healthIQ.Services;

import com.healthIQ.healthIQ.Models.Ordonnance;
import com.healthIQ.healthIQ.Repositories.OrdonnanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdonnanceService  {
    @Autowired
    private OrdonnanceRepo OrdonnanceRepo;

    public Ordonnance addOrdonnance(Ordonnance Or){
       return this.OrdonnanceRepo.save(Or);

    }
    public List<Ordonnance> getAll(){
        return (List<Ordonnance>) this.OrdonnanceRepo.findAll();
    }
    public Optional<Ordonnance> getOne(String id){
        return this.OrdonnanceRepo.findById(id);
    }
}
