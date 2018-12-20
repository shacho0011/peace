package com.mohit.example.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.example.dto.DoctorInfoDTO;
import com.mohit.example.model.Doctor;
import com.mohit.example.model.User;
import com.mohit.example.service.DoctorService;
import com.mohit.example.service.UserService;

@RestController
@RequestMapping("/api")
public class DoctorRestController {

	@Autowired
	DoctorService doctorService;
	@Autowired
	UserService userService;

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

	@PutMapping("/update/doctors")
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

	@DeleteMapping("/delete/doctors")
	ResponseEntity<Object> deleteDoctor(Principal principal, @RequestParam Long id) {
		ResponseEntity<Object> responseEntity;
		Map<String, Object> map = new HashMap<String, Object>();
		User user = null;
		try {
			user = userService.getUserDetails(principal);
			if (user.getRole().getName().toLowerCase().equals("role_admin")) {
				Doctor doctor = doctorService.getDoctorById(id);
				boolean flag = doctorService.deleteDoctor(doctor);
				if (flag) {
					map.put("status", "deleted");
					responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
				} else {
					map.put("status", "Please try again");
					responseEntity = new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}else {
				map.put("status", "Unauthorized access. Please try again");
				responseEntity = new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			map.put("status", "error");
			responseEntity = new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	}

}
