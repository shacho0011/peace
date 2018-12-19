package com.mohit.example.service;

import com.mohit.example.dto.DoctorInfoDTO;
import com.mohit.example.dto.LoginResponseDTO;
import com.mohit.example.dto.PatientDTO;
import com.mohit.example.dto.RegistrationResponseDTO;
import com.mohit.example.model.Doctor;
import com.mohit.example.model.Patient;
import com.mohit.example.model.User;

public interface DtoUtilService {
	
	DoctorInfoDTO convertToDoctorInfoDTOFromModel(Doctor doctor);
	PatientDTO convertToPatientDTOFromModel(Patient patient);
	RegistrationResponseDTO convertToRegistrationResponseDTOFromModel(User user);
	LoginResponseDTO convertToLoginResponseDTOFromModel(User user);

}
