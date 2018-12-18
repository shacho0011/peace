package com.mohit.example;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class RESTfulInPeaceApplication extends SpringBootServletInitializer{

	String contextPath = "";
	
	public static void main(String[] args) {
		SpringApplication.run(RESTfulInPeaceApplication.class, args);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		contextPath = servletContext.getContextPath();
		super.onStartup(servletContext);
	}
}

