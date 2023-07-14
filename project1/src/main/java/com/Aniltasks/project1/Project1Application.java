package com.Aniltasks.project1;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Project1Application {

	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}

	/*
	 * modelMapper is used to convert  the 
	 * DTOclass to entity and
	 * Entity class to DTOclass
	 */
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
