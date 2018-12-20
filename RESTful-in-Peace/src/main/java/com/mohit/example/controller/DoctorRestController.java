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
	ResponseEntity<Object> getDoctors(@RequestParam Optional<Long> id) {
		ResponseEntity<Object> responseEntity;
		Map<String, Object> map = new HashMap<String, Object>();

		try {

			if (id.isPresent()) {
				Long doctorId = id.get();
				DoctorInfoDTO doctorInfoDTO = null;
				doctorInfoDTO = doctorService.getDoctor(doctorId);
				if (doctorInfoDTO != null) {
					responseEntity = new ResponseEntity<>(doctorInfoDTO, HttpStatus.OK);
				} else {
					map.put("status", "No data found");
					responseEntity = new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
				}

			} else {
				List<DoctorInfoDTO> doctorInfoDTOs = new ArrayList();
				doctorInfoDTOs = doctorService.getAllDoctors();
				if (doctorInfoDTOs.size() > 0) {
					responseEntity = new ResponseEntity<>(doctorInfoDTOs, HttpStatus.OK);
				} else {
					map.put("status", "No data found");
					responseEntity = new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
				}
			}

		} catch (Exception e) {
			map.put("status", "error");
			responseEntity = new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	}

	@PostMapping("/insert/doctor/new")
	ResponseEntity<Object> insertDoctor(@RequestParam String json) {
		ResponseEntity<Object> responseEntity;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			DoctorInfoDTO doctorInfoDTO = new ObjectMapper().readValue(json, DoctorInfoDTO.class);
			Doctor doctor = new Doctor();
			doctor = doctorService.createOrUpdateDoctor(doctor, doctorInfoDTO);
			if (doctor.getId() != null) {
				map.put("status", "success");
				responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
			} else {
				map.put("status", "Please try again");
				responseEntity = new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			map.put("status", "error");
			responseEntity = new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	}

	@PutMapping("/update/doctors")
	ResponseEntity<Object> updateDoctor(@RequestParam Long id, @RequestParam String json) {
		ResponseEntity<Object> responseEntity;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			DoctorInfoDTO doctorInfoDTO = new ObjectMapper().readValue(json, DoctorInfoDTO.class);
			Doctor doctor = doctorService.getDoctorById(id);
			doctor = doctorService.createOrUpdateDoctor(doctor, doctorInfoDTO);
			map.put("status", "updated");
			responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			map.put("status", "error");
			responseEntity = new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
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
			} else {
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
