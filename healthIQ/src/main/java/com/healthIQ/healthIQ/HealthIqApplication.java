package com.healthIQ.healthIQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class HealthIqApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthIqApplication.class, args);
	}

}
