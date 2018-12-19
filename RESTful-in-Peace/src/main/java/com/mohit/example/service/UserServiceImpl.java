package com.mohit.example.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.example.dto.UserInfoDTO;
import com.mohit.example.model.User;
import com.mohit.example.repository.UserRepository;
import com.mohit.example.utill.DbUtills;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User createOrUpdateUser(User user, UserInfoDTO userInfoDTO) {
		try {

			if (userInfoDTO.getFirstName() != null) {
				user.setFirstName(userInfoDTO.getFirstName());
			}
			if (userInfoDTO.getLastName() != null) {
				user.setLastName(userInfoDTO.getLastName());
			}
			if (userInfoDTO.getEmail() != null) {
				user.setEmail(userInfoDTO.getEmail());
			}
			if (userInfoDTO.getMobile() != null) {
				user.setMobile(userInfoDTO.getMobile());
			}
			if (userInfoDTO.getPassword() != null) {
				user.setPassword(userInfoDTO.getPassword());
			}

			user = userRepository.save(user);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = null;
		try {
			user = userRepository.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
