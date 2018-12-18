package com.mohit.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.example.model.Doctor;
import com.mohit.example.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService{
	
	@Autowired
	DoctorRepository doctorRepository;

	@Override
	public List<Doctor> getAllDoctors() {
		List<Doctor> doctors = null;
		
		try {
			doctors = doctorRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return doctors;
	}

}
