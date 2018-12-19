package com.mohit.example.service;

import java.util.List;

import com.mohit.example.dto.PatientDTO;
import com.mohit.example.model.Patient;

public interface PatientService {
	
	List<PatientDTO> getAllPatients();
	PatientDTO getPatientDTO(Long id);
	Patient getPatientById(Long id);
	Patient createOrUpdatePatient(Patient patient, PatientDTO patientDTO);
	Boolean deletePatient(Patient patient);

}
