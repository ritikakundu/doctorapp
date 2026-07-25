package com.hospital.doctorapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDetails {

    private String name;

    private String email;

    private String specialization;

    private String phone;

    private int experienceYears;
}
