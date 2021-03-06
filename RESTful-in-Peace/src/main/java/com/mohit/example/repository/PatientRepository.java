package com.mohit.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.example.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

}
