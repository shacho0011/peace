package com.mohit.example.service;

import java.security.Principal;

import com.mohit.example.dto.UserInfoDTO;
import com.mohit.example.model.User;

public interface UserService {
	
	User createOrUpdateUser(User user, UserInfoDTO userInfoDTO);
	User getUserByEmail(String email);
	User getUserDetails(Principal principal);
}
