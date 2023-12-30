package com.healthIQ.healthIQ.Repositories;

import com.healthIQ.healthIQ.Models.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor,String> {
}
