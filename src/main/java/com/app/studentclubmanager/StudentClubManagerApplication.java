package com.app.studentclubmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.app.entity"})
public class StudentClubManagerApplication {

	private static ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) {
		StudentClubManagerApplication.applicationContext = SpringApplication.run(StudentClubManagerApplication.class, args);
	}

}
