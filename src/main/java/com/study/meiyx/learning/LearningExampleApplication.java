package com.study.meiyx.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class LearningExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningExampleApplication.class, args);
	}

}
