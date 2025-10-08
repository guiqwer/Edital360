package com.ifce.edital360;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Edital360Application {

	public static void main(String[] args) {
		SpringApplication.run(Edital360Application.class, args);
	}

}
