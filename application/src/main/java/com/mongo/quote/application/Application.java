package com.mongo.quote.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mongo.quote")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
