package com.mohit.example.dto;

public class PatientDTO {
	
	Long id;
	String name;
	String mobile;
	Integer age;
	String gender;
	String occupation;
	String symptom_summary;
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
	public String getSymptom_summary() {
		return symptom_summary;
	}
	public void setSymptom_summary(String symptom_summary) {
		this.symptom_summary = symptom_summary;
	}

}
