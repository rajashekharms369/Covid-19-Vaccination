package com.masai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Covid19VaccinationApplication {

	public static void main(String[] args) {
		SpringApplication.run(Covid19VaccinationApplication.class, args);
	}

}
