package com.kinetix.surveyengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SurveyBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveyBackendApplication.class, args);
	}

}
