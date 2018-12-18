package com.mohit.example.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.example.dto.DoctorInfoDTO;
import com.mohit.example.model.Doctor;

@Service
public class DtoUtilServiceImpl implements DtoUtilService{
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public DoctorInfoDTO convertToDoctorInfoDTOFromModel(Doctor doctor) {
		DoctorInfoDTO doctorInfoDTO = null;
		doctorInfoDTO = modelMapper.map(doctor, DoctorInfoDTO.class);
		return doctorInfoDTO;
	}

}
