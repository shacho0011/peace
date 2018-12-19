package com.mohit.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.example.dto.LoginDTO;
import com.mohit.example.dto.LoginResponseDTO;
import com.mohit.example.dto.RegistrationResponseDTO;
import com.mohit.example.dto.UserInfoDTO;
import com.mohit.example.model.User;
import com.mohit.example.service.DtoUtilService;
import com.mohit.example.service.UserService;

@RestController
@RequestMapping("")
public class UserRestController {

	@Autowired
	UserService userService;
	@Autowired
	DtoUtilService dtoUtilService;

	@PostMapping("/register")
	public Map<String, Object> userRegistration(@RequestParam String json) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			UserInfoDTO userInfoDTO = new ObjectMapper().readValue(json, UserInfoDTO.class);
			User user = new User();
			user = userService.createOrUpdateUser(user, userInfoDTO);
			if (user.getId() != null) {
				RegistrationResponseDTO registrationResponseDTO = dtoUtilService
						.convertToRegistrationResponseDTOFromModel(user);
				map.put("data", registrationResponseDTO);
			}

		} catch (Exception e) {
			map.put("status", "error");
			e.printStackTrace();
		}

		return map;
	}

	@PostMapping("/login")
	public Map<String, Object> userLogin(@RequestParam String json) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			LoginDTO loginDTO = new ObjectMapper().readValue(json, LoginDTO.class);
			User user = null;
			if (loginDTO.getEmail() != null && loginDTO.getPassword() != null) {
				user = userService.getUserByEmail(loginDTO.getEmail());

				if (user != null) {
					if (loginDTO.getPassword().equals(user.getPassword())) {
						LoginResponseDTO loginResponseDTO = dtoUtilService.convertToLoginResponseDTOFromModel(user);
						map.put("data", loginResponseDTO);
					} else {
						map.put("status", "error");
					}
				} else {
					map.put("status", "error");
				}
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
