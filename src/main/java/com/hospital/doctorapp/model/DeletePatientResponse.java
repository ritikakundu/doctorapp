package com.hospital.doctorapp.model;

import lombok.Data;

import java.util.List;

@Data
public class DeletePatientResponse {

    private String message;
    private List<PatientDetails> patientDetails;
}
