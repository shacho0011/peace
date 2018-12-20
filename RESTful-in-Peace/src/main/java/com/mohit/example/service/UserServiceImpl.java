package com.mohit.example.service;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mohit.example.dto.UserInfoDTO;
import com.mohit.example.model.User;
import com.mohit.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

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
				user.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
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

	@Override
	public User getUserDetails(Principal principal) {
		User user = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof UsernamePasswordAuthenticationToken) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			user = userRepository.findByEmail(userDetails.getUsername());
		}

		return user;
	}

}
