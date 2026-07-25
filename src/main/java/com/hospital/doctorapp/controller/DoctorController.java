package com.hospital.doctorapp.controller;

import com.hospital.doctorapp.entity.Doctor;
import com.hospital.doctorapp.model.DoctorDetails;
import com.hospital.doctorapp.model.Symptomps;
import com.hospital.doctorapp.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors") // API route standard clean rakhar jonno /api prefix dilam
@CrossOrigin(origins = "*") // Cross-Origin Resource Sharing (CORS) allow korlam jate alada UI template call korte pare
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/symptomps")
    public List<Symptomps> getAllSymptoms() {
        return List.of(Symptomps.values());
    }

    // 1. GET: Fetch all doctors OR Filter dynamically by Symptom (The Engine)
    @GetMapping("/search")
    public ResponseEntity<List<DoctorDetails>> getAllDoctors(@RequestParam(value = "symptom", required = false) String symptom) {

        List<Doctor> doctors;
        if (symptom != null && !symptom.isEmpty()) {
            // Switch expression maps the lower-case symptom directly to the specialty
            String specialty = switch (symptom.toLowerCase()) {
                case "fever", "cough" -> "General Physician";
                case "chest pain" -> "Cardiologist";
                case "skin rash" -> "Dermatologist";
                case "sugar" -> "Endocrinologist";
                case "bones" -> "Orthopedic";
                case "gastric" -> "Gastroenterologist";
                default -> "General Physician"; // Your fallback default
            };
            doctors = doctorService.findBySpecialization(specialty);
        } else {
            doctors = doctorService.getAllDoctors();
        }

        // Convert List<Doctor> to List<DoctorDetails>
        // Convert using standard setters
        List<DoctorDetails> listOfDoctors = doctors.stream()
                .map(doctor -> {
                    DoctorDetails details = new DoctorDetails();
                    details.setName(doctor.getName());
                    details.setSpecialization(doctor.getSpecialization());
                    details.setEmail(doctor.getEmail());
                    details.setPhone(doctor.getPhone());
                    details.setExperienceYears(doctor.getExperienceYears());
                    // Set any other fields your DoctorDetails class needs here...
                    return details;
                })
                .toList();

        if (listOfDoctors.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listOfDoctors); // Returns pure JSON Array to Frontend
    }

    // 2. POST: Create/Save a new doctor
    @PostMapping("/save")
    public ResponseEntity<String> saveDoctor(@RequestBody DoctorDetails doctorDetails) {
        doctorService.saveDoctor(doctorDetails);
        return ResponseEntity.ok("Doctor saved successfully!");
    }

    // 3. DELETE: Remove doctor by ID
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor deleted successfully!");
    }
}