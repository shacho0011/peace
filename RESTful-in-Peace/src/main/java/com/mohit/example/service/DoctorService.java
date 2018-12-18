package com.mohit.example.service;

import java.util.List;

import com.mohit.example.dto.DoctorInfoDTO;
import com.mohit.example.model.Doctor;

public interface DoctorService {
	
	List<DoctorInfoDTO> getAllDoctors();
	DoctorInfoDTO getDoctor(Long id);
	Doctor getDoctorById(Long id);
	Doctor createOrUpdateDoctor(Doctor doctor, DoctorInfoDTO doctorInfoDTO);
	Boolean deleteDoctor(Doctor doctor);
	
}
