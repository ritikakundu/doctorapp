package com.hospital.doctorapp.repository;

import com.hospital.doctorapp.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "select * from doctordb.appointments where patient_name = :patientName  and patient_email = :patientEmail", nativeQuery = true)
    List<Appointment> findByPatientNameAndEmail(@Param("patientName") String patientName, @Param("patientEmail")String patientEmail);

    List<Appointment> findByDoctorName(String doctorName);
}