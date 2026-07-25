package com.hospital.doctorapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentForm {

    private String patientName;

    private String patientEmail;

    private String doctorName;

    private String specialization;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private String symptoms;
}
