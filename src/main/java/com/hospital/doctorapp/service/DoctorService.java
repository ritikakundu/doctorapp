package com.hospital.doctorapp.service;

import com.hospital.doctorapp.entity.Doctor;
import com.hospital.doctorapp.model.DoctorDetails;
import com.hospital.doctorapp.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Save a new doctor
    public void saveDoctor(DoctorDetails doctorDetails) {
        Doctor doctor = new ModelMapper().map(doctorDetails, Doctor.class);
        doctorRepository.save(doctor);
    }

    // Find doctors by specialization
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }

    // Find doctor by id
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    // Delete doctor by id
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    // Controller er shathe mapping thik rakhar jonno alias method
    public List<Doctor> findBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }
}