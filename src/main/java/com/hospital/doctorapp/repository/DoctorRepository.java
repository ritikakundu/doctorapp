package com.hospital.doctorapp.repository;

import com.hospital.doctorapp.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findBySpecialization(String specialization);

    List<Doctor> findByNameContaining(String name);
}