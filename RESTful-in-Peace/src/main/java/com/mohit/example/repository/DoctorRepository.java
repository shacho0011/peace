package com.mohit.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.example.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
