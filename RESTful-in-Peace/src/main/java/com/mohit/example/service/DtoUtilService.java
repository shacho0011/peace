package com.mohit.example.service;

import com.mohit.example.dto.DoctorInfoDTO;
import com.mohit.example.model.Doctor;

public interface DtoUtilService {
	
	DoctorInfoDTO convertToDoctorInfoDTOFromModel(Doctor doctor);

}
