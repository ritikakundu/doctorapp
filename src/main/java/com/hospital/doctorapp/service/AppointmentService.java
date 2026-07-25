package com.hospital.doctorapp.service;

import com.hospital.doctorapp.entity.Appointment;
import com.hospital.doctorapp.model.AppointmentForm;
import com.hospital.doctorapp.repository.AppointmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    // Get all appointments
    public List<AppointmentForm> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentForm> allAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            AppointmentForm appointmentForm = new AppointmentForm();
            appointmentForm.setPatientName(appointment.getPatientName());
            appointmentForm.setPatientEmail(appointment.getPatientEmail());
            appointmentForm.setDoctorName(appointment.getDoctorName());
            appointmentForm.setAppointmentDate(appointment.getAppointmentDate());
            appointmentForm.setAppointmentTime(appointment.getAppointmentTime());
            allAppointments.add(appointmentForm);
        }
        return allAppointments;
    }



    // Save a new appointment
    public String saveAppointment(AppointmentForm appointmentForm) {
        Appointment appointment = new ModelMapper().map(appointmentForm, Appointment.class);
        appointment.setStatus("CONFIRMED");
        appointmentRepository.save(appointment);
        return "Saved successfully!";
    }



    // Find appointments by patient name and email
    public List<AppointmentForm> getAppointmentsByPatientNameAndEmail(String name, String email) {
        List<Appointment> appointments = appointmentRepository.findByPatientNameAndEmail(name, email);
        List<AppointmentForm> allAppointmentsByNameAndEmail = new ArrayList<>();
        for (Appointment appointment : appointments) {
            AppointmentForm appointmentForm = new AppointmentForm();
            appointmentForm.setPatientName(appointment.getPatientName());
            appointmentForm.setPatientEmail(appointment.getPatientEmail());
            appointmentForm.setDoctorName(appointment.getDoctorName());
            appointmentForm.setAppointmentDate(appointment.getAppointmentDate());
            appointmentForm.setAppointmentTime(appointment.getAppointmentTime());
            allAppointmentsByNameAndEmail.add(appointmentForm);
        }
        return allAppointmentsByNameAndEmail;
    }



    // Find appointments by doctor name
    public List<AppointmentForm> getAppointmentsByDoctorName(String doctorName) {
        List<Appointment> appointments = appointmentRepository.findByDoctorName(doctorName);
        List<AppointmentForm> allAppointmentsByDoctor = new ArrayList<>();
        for (Appointment appointment : appointments) {
            AppointmentForm appointmentForm = new AppointmentForm();
            appointmentForm.setPatientName(appointment.getPatientName());
            appointmentForm.setPatientEmail(appointment.getPatientEmail());
            appointmentForm.setDoctorName(appointment.getDoctorName());
            appointmentForm.setAppointmentDate(appointment.getAppointmentDate());
            appointmentForm.setAppointmentTime(appointment.getAppointmentTime());
            allAppointmentsByDoctor.add(appointmentForm);
        }
        return allAppointmentsByDoctor;
    }

    

    // Delete appointment by id
    public List<AppointmentForm> deleteAppointment(String patientName, String patientEmail) {

        //Search for patient in DB by email
        List<Appointment> allAppointments = appointmentRepository.findByPatientNameAndEmail(patientName, patientEmail);
        List<AppointmentForm> apDetails = new ArrayList<>();
        List<Long> apIds = new ArrayList<>();

        for (Appointment appointment : allAppointments) {
            //Match with name and phone
            if (appointment.getPatientName().equals(patientName) && appointment.getPatientEmail().equals(patientEmail)) {

                AppointmentForm details = new AppointmentForm();
                details.setPatientName(appointment.getPatientName());
                details.setPatientEmail(appointment.getPatientEmail());
                details.setDoctorName(appointment.getDoctorName());
                details.setAppointmentDate(appointment.getAppointmentDate());
                details.setAppointmentTime(appointment.getAppointmentTime());

                apDetails.add(details);
                apIds.add(appointment.getId());
            }
        }

        if (apDetails.isEmpty()) {
            throw new RuntimeException("Patient not found");
        }

        if (apDetails.size() == 1) {
            appointmentRepository.deleteById(apIds.get(0));
        }
        return apDetails;
    }

}