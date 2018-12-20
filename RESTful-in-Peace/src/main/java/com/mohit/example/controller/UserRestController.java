package com.mohit.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.example.dto.LoginDTO;
import com.mohit.example.dto.LoginResponseDTO;
import com.mohit.example.dto.RegistrationResponseDTO;
import com.mohit.example.dto.UserInfoDTO;
import com.mohit.example.model.User;
import com.mohit.example.model.security.JwtUser;
import com.mohit.example.security.JwtGenerator;
import com.mohit.example.service.DtoUtilService;
import com.mohit.example.service.UserService;

@RestController
@RequestMapping("")
public class UserRestController {

	@Autowired
	UserService userService;
	@Autowired
	DtoUtilService dtoUtilService;
	@Autowired
	JwtGenerator jwtGenerator;
	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<Object> userRegistration(@RequestBody String requestData) {
		ResponseEntity<Object> responseEntity;

		try {

			UserInfoDTO userInfoDTO = new ObjectMapper().readValue(requestData, UserInfoDTO.class);
			if (userInfoDTO != null) {
				User user = null;
				user = userService.getUserByEmail(userInfoDTO.getEmail());
				if (user == null) {

					user = new User();
					user = userService.createOrUpdateUser(user, userInfoDTO);
					if (user.getId() != null) {
						RegistrationResponseDTO registrationResponseDTO = dtoUtilService
								.convertToRegistrationResponseDTOFromModel(user);
						responseEntity = new ResponseEntity<>(registrationResponseDTO, HttpStatus.OK);
					} else {
						responseEntity = new ResponseEntity<>("Internal server error. Please try again",
								HttpStatus.INTERNAL_SERVER_ERROR);
					}

				} else {
					responseEntity = new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
				}

			} else {
				responseEntity = new ResponseEntity<>("Data error. Please try again", HttpStatus.NOT_ACCEPTABLE);
			}

		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	}

	@PostMapping("/login")
	public ResponseEntity<Object> userLogin(@RequestBody String requestData) {
		ResponseEntity<Object> responseEntity;
		HttpHeaders headers = new HttpHeaders();
		try {

			LoginDTO loginDTO = new ObjectMapper().readValue(requestData, LoginDTO.class);
			User user = null;
			JwtUser jwtUser = new JwtUser();
			if (loginDTO.getEmail() != null && loginDTO.getPassword() != null) {
				user = userService.getUserByEmail(loginDTO.getEmail());
				if (user != null) {
					if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
						LoginResponseDTO loginResponseDTO = dtoUtilService.convertToLoginResponseDTOFromModel(user);
						jwtUser.setId(user.getId());
						jwtUser.setUserName(user.getEmail());
						jwtUser.setRole("user");
						headers.add("jwt_token", jwtGenerator.generate(jwtUser));
						responseEntity = new ResponseEntity<>(loginResponseDTO, headers, HttpStatus.OK);
					} else {
						responseEntity = new ResponseEntity<>("Password mismatch.", HttpStatus.NOT_FOUND);
					}
				} else {
					responseEntity = new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
				}
			} else {
				responseEntity = new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	}

}
