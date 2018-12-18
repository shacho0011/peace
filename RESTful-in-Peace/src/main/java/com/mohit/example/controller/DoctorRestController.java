package com.mohit.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.example.model.Doctor;
import com.mohit.example.service.DoctorService;

@RestController
@RequestMapping("/api")
public class DoctorRestController {
	
	@Autowired
	DoctorService doctorService;
	
	@GetMapping("/doctors")
	List<Doctor> getAllDoctors() {
		
		List<Doctor> doctors = null;
		
		try {
			doctors = doctorService.getAllDoctors();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return doctors;
	}

}
