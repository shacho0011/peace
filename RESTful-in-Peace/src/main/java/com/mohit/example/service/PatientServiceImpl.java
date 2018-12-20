package com.mohit.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.example.dto.PatientDTO;
import com.mohit.example.model.Patient;
import com.mohit.example.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepository patientRepository;
	@Autowired
	DtoUtilService dtoUtilService;

	@Override
	public List<PatientDTO> getAllPatients() {
		List<Patient> patients = null;
		List<PatientDTO> patientDTOs = new ArrayList();
		try {
			patients = patientRepository.findAll();
			if (patients.size() > 0) {
				patients.forEach(patient -> {
					PatientDTO patientDTO = dtoUtilService.convertToPatientDTOFromModel(patient);
					patientDTOs.add(patientDTO);
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return patientDTOs;
	}

	@Override
	public PatientDTO getPatientDTO(Long id) {
		Patient patient = null;
		PatientDTO patientDTO = null;
		try {

			patient = patientRepository.findOne(id);
			if (patient != null) {
				patientDTO = dtoUtilService.convertToPatientDTOFromModel(patient);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return patientDTO;
	}

	@Override
	public Patient getPatientById(Long id) {
		Patient patient = null;
		try {
			patient = patientRepository.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patient;
	}

	@Override
	public Patient createOrUpdatePatient(Patient patient, PatientDTO patientDTO) {

		try {

			if (patientDTO.getName() != null) {
				patient.setName(patientDTO.getName());
			}
			if (patientDTO.getMobile() != null) {
				patient.setMobile(patientDTO.getMobile());
			}
			if (patientDTO.getAge() != null) {
				patient.setAge(patientDTO.getAge());
			}
			if (patientDTO.getGender() != null) {
				patient.setGender(patientDTO.getGender());
			}
			if (patientDTO.getOccupation() != null) {
				patient.setOccupation(patientDTO.getOccupation());
			}
			if (patientDTO.getSymptom_summary() != null) {
				patient.setSymptom_summary(patientDTO.getSymptom_summary());
			}

			patient = patientRepository.save(patient);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return patient;
	}

	@Override
	public Boolean deletePatient(Patient patient) {

		try {
			patientRepository.delete(patient);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
