package com.healthIQ.healthIQ.Controllers;

import com.healthIQ.healthIQ.Models.Doctor;
import com.healthIQ.healthIQ.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/list")
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }
    @GetMapping("/List/{id}")
    public Doctor getDoctorById(@PathVariable String id){
        return doctorService.getDoctorById(id);
    }
    @PostMapping("/add")
    public Doctor addDoctor(@RequestBody Doctor doctor){
        return doctorService.addDoctor(doctor);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteDoctor(@PathVariable String id){
        this.doctorService.deleteDoctor(id);
    }
    @PutMapping("/update/{id}")
    public Doctor updateDoctor(@PathVariable String id, @RequestBody Doctor doctor){
        return doctorService.updateDoctor(id,doctor);
    }
}
