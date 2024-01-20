package com.healthIQ.healthIQ.Services;

import com.healthIQ.healthIQ.Models.Doctor;
import com.healthIQ.healthIQ.Models.Role;
import com.healthIQ.healthIQ.Repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired(required = false)
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors(){
        return (List<Doctor>) doctorRepository.findAll();
    }
    public Doctor getDoctorById(String id ){
        return doctorRepository.findById(id).orElse(null);
    }
    public Doctor addDoctor(Doctor doctor){
        doctor.setRole(Role.ROLE_DOCTOR);
        return (Doctor) doctorRepository.save(doctor);
    }
    public void deleteDoctor(String id){
        this.doctorRepository.deleteById(id);
    }
    public Doctor updateDoctor(String id, Doctor doctor){
        Doctor updatedDoctor = doctorRepository.findById(id).get();
        updatedDoctor.setFirst_name(doctor.getFirst_name());
        updatedDoctor.setLast_name(doctor.getLast_name());
        updatedDoctor.setEmail(doctor.getEmail());
        updatedDoctor.setDebutTime(doctor.getDebutTime());
        updatedDoctor.setFinTime(doctor.getFinTime());
        return doctorRepository.save(updatedDoctor);
    }
}
