package com.gosiaProject.GosiaProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GosiaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GosiaProjectApplication.class, args);
	}

}
