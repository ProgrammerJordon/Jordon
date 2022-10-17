package com.project.jordon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JordonApplication {

	public static void main(String[] args) {
		SpringApplication.run(JordonApplication.class, args);
	}

}
