package com.mohit.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohit.example.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);

}
