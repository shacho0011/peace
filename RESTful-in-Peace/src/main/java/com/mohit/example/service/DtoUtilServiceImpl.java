package com.mohit.example.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.example.dto.DoctorInfoDTO;
import com.mohit.example.dto.LoginResponseDTO;
import com.mohit.example.dto.PatientDTO;
import com.mohit.example.dto.RegistrationResponseDTO;
import com.mohit.example.model.Doctor;
import com.mohit.example.model.Patient;
import com.mohit.example.model.User;

@Service
public class DtoUtilServiceImpl implements DtoUtilService{
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public DoctorInfoDTO convertToDoctorInfoDTOFromModel(Doctor doctor) {
		
		DoctorInfoDTO doctorInfoDTO = null;
		try {
			doctorInfoDTO = modelMapper.map(doctor, DoctorInfoDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return doctorInfoDTO;
	}

	@Override
	public PatientDTO convertToPatientDTOFromModel(Patient patient) {
		
		PatientDTO patientDTO = null;
		try {
			patientDTO = modelMapper.map(patient, PatientDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return patientDTO;
	}

	@Override
	public RegistrationResponseDTO convertToRegistrationResponseDTOFromModel(User user) {
		
		RegistrationResponseDTO registrationResponseDTO = null;
		try {
			registrationResponseDTO = modelMapper.map(user, RegistrationResponseDTO.class);
			registrationResponseDTO.setStatus("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return registrationResponseDTO;
	}

	@Override
	public LoginResponseDTO convertToLoginResponseDTOFromModel(User user) {
		
		LoginResponseDTO loginResponseDTO = null;
		try {
			loginResponseDTO = modelMapper.map(user, LoginResponseDTO.class);
			loginResponseDTO.setStatus("logged_in");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loginResponseDTO;
	}

}
