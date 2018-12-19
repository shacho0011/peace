package com.mohit.example.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.example.dto.DoctorInfoDTO;
import com.mohit.example.model.Doctor;
import com.mohit.example.repository.DoctorRepository;
import com.mohit.example.utill.DbUtills;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	DtoUtilService dtoUtilService;

	@Override
	public List<DoctorInfoDTO> getAllDoctors() {
		List<Doctor> doctors = null;
		List<DoctorInfoDTO> doctorInfoDTOs = new ArrayList();
		try {
			doctors = doctorRepository.findAll();
			if (doctors.size() > 0) {
				doctors.forEach(doctor -> {
					DoctorInfoDTO doctorInfoDTO = dtoUtilService.convertToDoctorInfoDTOFromModel(doctor);
					if (doctorInfoDTO.getJoining() != null) {
						doctorInfoDTO.setJoining(DbUtills.formatDateForDisplay(doctorInfoDTO.getJoining()));
					}
					doctorInfoDTOs.add(doctorInfoDTO);
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return doctorInfoDTOs;
	}

	@Override
	public DoctorInfoDTO getDoctor(Long id) {
		Doctor doctor = null;
		DoctorInfoDTO doctorInfoDTO = null;

		try {
			doctor = doctorRepository.findOne(id);
			if (doctor != null) {
				doctorInfoDTO = dtoUtilService.convertToDoctorInfoDTOFromModel(doctor);
				if (doctorInfoDTO.getJoining() != null) {
					doctorInfoDTO.setJoining(DbUtills.formatDateForDisplay(doctorInfoDTO.getJoining()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return doctorInfoDTO;
	}

	@Override
	public Doctor createOrUpdateDoctor(Doctor doctor, DoctorInfoDTO doctorInfoDTO) {

		try {

			if (doctorInfoDTO.getName() != null) {
				doctor.setName(doctorInfoDTO.getName());
			}

			if (doctorInfoDTO.getDept() != null) {
				doctor.setDept(doctorInfoDTO.getDept());
			}

			if (doctorInfoDTO.getJoining() != null) {
				String date = DbUtills.formatDateForSave(doctorInfoDTO.getJoining());
				doctor.setJoining(new SimpleDateFormat("yyyy-MM-dd").parse(date));
			}

			doctor = doctorRepository.save(doctor);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return doctor;
	}

	@Override
	public Doctor getDoctorById(Long id) {
		Doctor doctor = null;
		try {
			doctor = doctorRepository.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctor;
	}

	@Override
	public Boolean deleteDoctor(Doctor doctor) {
		
		try {
			doctorRepository.delete(doctor);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
