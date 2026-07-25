package com.hospital.doctorapp.controller;

import com.hospital.doctorapp.entity.Patient;
import com.hospital.doctorapp.model.DeletePatientResponse;
import com.hospital.doctorapp.model.PatientDetails;
import com.hospital.doctorapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientService patientService;

    // Show all patients
    @GetMapping("/search-all")
    public ResponseEntity<List<PatientDetails>> getAllPatients() {
        List<PatientDetails> patients = patientService.getAllPatients();
        return ResponseEntity.ok().body(patients);
    }

    @GetMapping("/search-by-email")
    public ResponseEntity<List<PatientDetails>> getPatientByEmail(@RequestParam String email) {
        List<PatientDetails> patients = patientService.getPatientByEmail(email);
        return ResponseEntity.ok().body(patients);
    }

    // Save new patient
    @PostMapping("/save")
    public ResponseEntity<String> savePatient(@RequestBody PatientDetails patientDetails) {
        patientService.savePatient(patientDetails);
        return ResponseEntity.ok("Patient's details is saved successfully");
    }

    // Delete patient
    @DeleteMapping("/delete")
    public ResponseEntity<DeletePatientResponse> deletePatient(@RequestParam String name, @RequestParam String email, @RequestParam String phone) {
        List<PatientDetails> patientDetailsList = patientService.deletePatient(name, email, phone);

        DeletePatientResponse res = new DeletePatientResponse();
        if (patientDetailsList.size() > 1) {
            res.setMessage("Multiple patient available with same info");
            res.setPatientDetails(patientDetailsList);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
        }

        res.setMessage("Patient is deleted successfully");
        return ResponseEntity.ok(res);
    }
}