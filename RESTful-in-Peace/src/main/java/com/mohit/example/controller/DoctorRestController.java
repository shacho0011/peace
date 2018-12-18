package com.mohit.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.example.dto.DoctorInfoDTO;
import com.mohit.example.model.Doctor;
import com.mohit.example.service.DoctorService;

@RestController
@RequestMapping("/api")
public class DoctorRestController {

	@Autowired
	DoctorService doctorService;

	@GetMapping("/doctors")
	Map<String, Object> getDoctors(@RequestParam Optional<Long> id) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {

			if (id.isPresent()) {
				Long doctorId = id.get();
				DoctorInfoDTO doctorInfoDTO = null;
				doctorInfoDTO = doctorService.getDoctor(doctorId);
				map.put("data", doctorInfoDTO);
			} else {
				List<DoctorInfoDTO> doctorInfoDTOs = new ArrayList();
				doctorInfoDTOs = doctorService.getAllDoctors();
				map.put("data", doctorInfoDTOs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	
	@PostMapping("/insert/doctor/new")
	Map<String, Object> insertDoctor(@RequestParam String json) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			DoctorInfoDTO doctorInfoDTO = new ObjectMapper().readValue(json, DoctorInfoDTO.class);
			Doctor doctor = new Doctor();
			doctor = doctorService.createOrUpdateDoctor(doctor, doctorInfoDTO);
			map.put("status", "success");
		} catch (Exception e) {
			map.put("status", "error");
			e.printStackTrace();
		}

		return map;
	}
	
	@PostMapping("/update/doctors")
	Map<String, Object> updateDoctor(@RequestParam Long id, @RequestParam String json) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			DoctorInfoDTO doctorInfoDTO = new ObjectMapper().readValue(json, DoctorInfoDTO.class);
			Doctor doctor = doctorService.getDoctorById(id);
			doctor = doctorService.createOrUpdateDoctor(doctor, doctorInfoDTO);
			map.put("status", "updated");
		} catch (Exception e) {
			map.put("status", "error");
			e.printStackTrace();
		}

		return map;
	}
	
	@PostMapping("/delete/doctors")
	Map<String, Object> deleteDoctor(@RequestParam Long id) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			Doctor doctor = doctorService.getDoctorById(id);
			boolean flag = doctorService.deleteDoctor(doctor);
			if(flag) {
				map.put("status", "deleted");
			}else {
				map.put("status", "error");
			}
		} catch (Exception e) {
			map.put("status", "error");
			e.printStackTrace();
		}

		return map;
	}

}
