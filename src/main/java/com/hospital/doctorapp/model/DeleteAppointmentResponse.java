package com.hospital.doctorapp.model;

import lombok.Data;

import java.util.List;

@Data
public class DeleteAppointmentResponse {

    private String message;
    private List<AppointmentForm> appointmentForms;
}
