package com.hospital.doctorapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;

    private String patientEmail;

    private String doctorName;

    private String specialization;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private String status; // PENDING, CONFIRMED, CANCELLED

    private String symptoms;
}
