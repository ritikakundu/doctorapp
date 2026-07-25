package com.hospital.doctorapp.service;

import com.hospital.doctorapp.entity.Appointment;
import com.hospital.doctorapp.entity.Patient;
import com.hospital.doctorapp.model.PatientDetails;
import com.hospital.doctorapp.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    // Get all patients
    public List<PatientDetails> getAllPatients() {
        List<PatientDetails> patientDetails = new ArrayList<>();
        List<Patient> pt = patientRepository.findAll();

        for (Patient p : pt) {
            PatientDetails patients = new PatientDetails();
            patients.setName(p.getName());
            patients.setAge(p.getAge());
            patients.setPhone(p.getPhone());
            patients.setEmail(p.getEmail());
            patientDetails.add(patients);
        }
        return patientDetails;
    }

    // Save a new patient
    public void savePatient(PatientDetails patientDetails) {
        Patient patient = new Patient();
        String ptNm = patientDetails.getName();
        patient.setName(ptNm);
        patient.setAge(patientDetails.getAge());
        patient.setEmail(patientDetails.getEmail());
        patient.setPhone(patientDetails.getPhone());
        patient.setPassword(patientDetails.getPassword());
        patientRepository.save(patient);
    }

    // Find patient by email
    public List<PatientDetails> getPatientByEmail(String email) {
        List<Patient> patientList = patientRepository.findByEmail(email);
        List<PatientDetails> patientDetails = new ArrayList<>();
        for (Patient patient : patientList) {
            PatientDetails patients = new PatientDetails();
            patients.setName(patient.getName());
            patients.setAge(patient.getAge());
            patients.setEmail(patient.getEmail());
            patients.setPhone(patient.getPhone());
            patientDetails.add(patients);
        }
        return patientDetails;
    }

    // Delete patient by name, email, phone
    public List<PatientDetails> deletePatient(String name, String email, String phone) {

        //Search for patient in DB by email
        List<Patient> patientList = patientRepository.findByEmail(email);

        List<PatientDetails> ptDetails = new ArrayList<>();
        List<Long> ptIds = new ArrayList<>();

        for (Patient patient : patientList) {
            //Match with name and phone
            if (patient.getName().equals(name) && patient.getPhone().equals(phone)) {

                PatientDetails details = new PatientDetails();
                details.setName(patient.getName());
                details.setEmail(patient.getEmail());
                details.setPhone(patient.getPhone());
                details.setAge(patient.getAge());

                ptDetails.add(details);
                ptIds.add(patient.getId());
            }
        }

        if (ptDetails.isEmpty()) {
            throw new RuntimeException("Patient not found");
        }

        if (ptDetails.size() == 1) {
            patientRepository.deleteById(ptIds.get(0));
        }
        return ptDetails;
    }
}