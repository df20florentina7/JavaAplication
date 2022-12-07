package com.mywebapp.patients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query("SELECT s FROM Patient s WHERE s.name=?1")
    Optional<Patient> findPatientByName(String name);
}
