package com.sparta.spring2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Spring2Application {

	public static void main(String[] args) {
		SpringApplication.run(Spring2Application.class, args);
	}

}
