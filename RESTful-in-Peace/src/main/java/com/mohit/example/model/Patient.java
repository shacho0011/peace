package com.mohit.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String name;
	String mobile;
	Integer age;
	String gender;
	String occupation;
	String symptomSummary;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getSymptomSummary() {
		return symptomSummary;
	}
	public void setSymptomSummary(String symptomSummary) {
		this.symptomSummary = symptomSummary;
	}

}
