package com.prashant.domainjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DomainjpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DomainjpaApplication.class, args);
	}

}
