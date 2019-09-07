package com.sapient.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sapient.assessment"})
public class AssessmentApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AssessmentApplication.class, args);
	}

}
