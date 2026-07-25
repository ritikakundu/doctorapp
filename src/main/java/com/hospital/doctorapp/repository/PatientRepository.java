package com.hospital.doctorapp.repository;

import com.hospital.doctorapp.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {


    List<Patient> findByEmail(String email);
}