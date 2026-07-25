package com.hospital.doctorapp.controller;

import com.hospital.doctorapp.entity.Appointment;
import com.hospital.doctorapp.model.AppointmentForm;
import com.hospital.doctorapp.model.DeleteAppointmentResponse;
import com.hospital.doctorapp.model.DeletePatientResponse;
import com.hospital.doctorapp.model.PatientDetails;
import com.hospital.doctorapp.service.AppointmentService;
import com.hospital.doctorapp.service.DoctorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    DoctorService doctorService;

    // Show all appointments
    @GetMapping("/search-all-appointments")
    public List<AppointmentForm> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // Search appointments by patient details
    @GetMapping("/search-by-patient")
    public ResponseEntity<List<AppointmentForm>> getAppointmentsByPatient(@RequestParam String patientName, @RequestParam String patientEmail) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatientNameAndEmail(patientName, patientEmail));
    }

    // Search appointments by doctor name
    @GetMapping("/search-by-doctor")
    public ResponseEntity<List<AppointmentForm>>getAppointmentsByPatient(@RequestParam String doctorName) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorName(doctorName));
    }


    // Save new appointment
    @PostMapping("/book-appointment")
    public ResponseEntity<String> saveAppointment(@RequestBody AppointmentForm appointmentForm) {
        String response = appointmentService.saveAppointment(appointmentForm);
        if (StringUtils.isNotEmpty(response) && response.equals("Saved successfully!")){
            //ToDo: Send email
        }
        return ResponseEntity.ok(response);
    }

    // Cancel appointment
    @DeleteMapping("/delete-appointment")
    public ResponseEntity<DeleteAppointmentResponse> cancelAppointment(@RequestParam String patientName, @RequestParam String patientEmail) {
        List<AppointmentForm> allAppointments = appointmentService.deleteAppointment(patientName, patientEmail);

        DeleteAppointmentResponse res = new DeleteAppointmentResponse();
        if (allAppointments.size() > 1) {
            res.setMessage("Multiple appointments available with same info");
            res.setAppointmentForms(allAppointments);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
        }

        res.setMessage("Patient is deleted successfully");
        return ResponseEntity.ok(res);
    }
}