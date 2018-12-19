package com.mohit.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.example.dto.PatientDTO;
import com.mohit.example.model.Patient;
import com.mohit.example.service.PatientService;

@RestController
@RequestMapping("/api")
public class PatientRestController {

	@Autowired
	PatientService patientService;

	@GetMapping("/patients")
	Map<String, Object> getDoctors(@RequestParam Optional<Long> id) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			if (id.isPresent()) {
				Long patientId = id.get();
				PatientDTO patientDTO = null;
				patientDTO = patientService.getPatientDTO(patientId);
				map.put("data", patientDTO);
			} else {
				List<PatientDTO> patientDTOs = new ArrayList();
				patientDTOs = patientService.getAllPatients();
				map.put("data", patientDTOs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	@PostMapping("/insert/patient/new")
	Map<String, Object> insertDoctor(@RequestParam String json) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			PatientDTO patientDTO = new ObjectMapper().readValue(json, PatientDTO.class);
			Patient patient = new Patient();
			patient = patientService.createOrUpdatePatient(patient, patientDTO);
			map.put("status", "success");
		} catch (Exception e) {
			map.put("status", "error");
			e.printStackTrace();
		}

		return map;
	}

	@PutMapping("/update/patients")
	Map<String, Object> updateDoctor(@RequestParam Long id, @RequestParam String json) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			PatientDTO patientDTO = new ObjectMapper().readValue(json, PatientDTO.class);
			Patient patient = patientService.getPatientById(id);
			patient = patientService.createOrUpdatePatient(patient, patientDTO);
			map.put("status", "updated");
		} catch (Exception e) {
			map.put("status", "error");
			e.printStackTrace();
		}

		return map;
	}

	@DeleteMapping("/delete/patients")
	Map<String, Object> deleteDoctor(@RequestParam Long id) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			Patient patient = patientService.getPatientById(id);
			boolean flag = patientService.deletePatient(patient);
			if (flag) {
				map.put("status", "deleted");
			} else {
				map.put("status", "error");
			}
		} catch (Exception e) {
			map.put("status", "error");
			e.printStackTrace();
		}

		return map;
	}

}
